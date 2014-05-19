package com.gumtree.challenge.utils;

import au.com.bytecode.opencsv.CSVReader;
import com.gumtree.challenge.dto.Person;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A utility class with helper methods to load the list of {@link com.gumtree.challenge.dto.Person} from a CSV file
 */
public class CSVUtils {


    /**
     * Load the list of persons from a csv file
     * @param file the path to the csv file
     * @param sortByDate if set to true the list or {@link com.gumtree.challenge.dto.Person} will be sorted by birth date
     */
    public static List<Person> loadPersonsFromCSV(String file, boolean sortByDate) throws IOException {
        List<Person> persons = parseCSVFile(file);
        if(sortByDate) sortPersonsByBirthDate(persons);
        return persons;
    }

    /**
     * Open the csv file and build a list of {@link com.gumtree.challenge.dto.Person} for each row.
     */
    protected static List<Person> parseCSVFile(String file) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(file), ',');

        List<Person> persons = new ArrayList<>();
        String[] record;
        while ((record = reader.readNext()) != null) {
            Person person = Person.fromCSVRecord(record);
            if (person != null)
                persons.add(person);
        }
        reader.close();

        return persons;
    }


    /**
     * Sort a list of {@link com.gumtree.challenge.dto.Person} by birth date in ascending order
     */
    private static void sortPersonsByBirthDate(List<Person> persons) {
        Collections.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                //sort the list by birth date in ascending order
                return o1.getBirthDate().compareTo(o2.getBirthDate());
            }
        });
    }
}
