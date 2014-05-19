package com.gumtree.challenge.dto;

import com.gumtree.challenge.utils.CSVUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.joda.time.Days;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang.StringUtils.isBlank;

/**
 * This class represents an Address Book and handles queries related to the list of people stored withn
 */
public class AddressBook {


    private static final String FILE = "src/main/resources/AddressBook";

    private List<Person> persons;

    /**
     * Default constructor which loads the list of people in the address book
     */
    public AddressBook() throws IOException {
        this(FILE);
    }

    /**
     * Constructor to be used in tests. This allows overriding the default csv file
     */
    protected AddressBook(String filePath) throws IOException {
        persons = CSVUtils.loadPersonsFromCSV(filePath, true);
    }


    /**
     * This method calculates the amount of persons by gender we have in the address book by applying filtering.
     * Filtering is applied on a clone of the original list so that the original is not affected.
     */
    public int findCountByGender(final Person.Gender gender) {
        if (CollectionUtils.isEmpty(persons) || gender == null) return 0;
        //clone the list so we do not affect the original by filtering
        List<Person> personsByGender = cloneList(persons);

        CollectionUtils.filter(personsByGender, new Predicate<Person>() {
            @Override
            public boolean evaluate(Person person) {
                return person.getGender().equalsIgnoreCase(gender.toString());
            }
        });
        return personsByGender.size();
    }

    /**
     * This method returns the eldest person in the address book. Since the list was already ordered by birth date
     * on load, we only need to return the {@link Person} at position 0.
     */
    public Person findEldestPerson() {
        if (CollectionUtils.isEmpty(persons)) return null;
        return persons.get(0);
    }

    /**
     * This method calculates the age difference in days between two people based on the first name
     * If any of the two people are not found in the list an exception is thrown
     * It is assumed that ony one person with the name matching is found, ideally we use the
     * full name to search
     *
     * @return the difference in days or -1 if any of the parameters is missing, more than one person matches
     * the a name or no person matches a name
     */
    public int findAgeDifferenceInDays(final String person1Name,
                                       final String person2Name) {
        if (CollectionUtils.isEmpty(persons) || isBlank(person1Name) || isBlank(person2Name)) return -1;

        List<Person> matchedPersons = findPersonsByName(person1Name, person2Name);

        if (matchedPersons.size() != 2) return -1;

        //since persons were ordered by date on file loading we can assume the first person is the oldest
        return Days.daysBetween(matchedPersons.get(0).getBirthDate(), matchedPersons.get(1).getBirthDate()).getDays();
    }


    /**
     * This method checks the list and returns persons whose name starts with the person names passed
     * as parameters (if any) or an empty list
     */
    protected List<Person> findPersonsByName(final String person1Name, final String person2Name) {
        List<Person> matchedPersons = cloneList(persons);

        CollectionUtils.filter(matchedPersons, new Predicate<Person>() {
            @Override
            public boolean evaluate(Person person) {
                return person.getFullName().startsWith(person1Name) ||
                        person.getFullName().startsWith(person2Name);
            }
        });
        return matchedPersons;
    }

    /**
     *Create a copy of the list passed as parameter so that there is no reference to the original. The copy can
     * than be manipulated without affecting the original.
     */
    private List<Person> cloneList(final List<Person> persons) {
        return new ArrayList<>(persons);
    }

}
