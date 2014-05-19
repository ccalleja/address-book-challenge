package com.gumtree.challenge.dto;

import junit.framework.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class AddressBookTest {

    public static final String FILE_AS_EXPECTED = "src/test/resources/AddressBook";
    public static final String FILE_AS_EXPECTED_WITH_DIFFERENT_ORDER = "src/test/resources/AddressBookWithDifferentOrder";
    public static final String FILE_AS_EXPECTED_WITH_SIMILAR_NAMES = "src/test/resources/AddressBookWithSimilarNames";


    private AddressBook addressBook;

    @Test
    public void testFindCountByGender_Male() throws IOException {
        addressBook = new AddressBook();
        int countByGender = addressBook.findCountByGender(Person.Gender.MALE);
        Assert.assertEquals(3, countByGender);

    }

    @Test
    public void testFindCountByGender_Female() throws IOException {
        addressBook = new AddressBook();
        int countByGender = addressBook.findCountByGender(Person.Gender.FEMALE);
        Assert.assertEquals(2, countByGender);

    }

    @Test
    public void testFindCountByGender_Null() throws IOException {
        addressBook = new AddressBook();
        int countByGender = addressBook.findCountByGender(null);
        Assert.assertEquals(0, countByGender);

    }

    @Test
    public void testFindEldestPerson() throws IOException {
        addressBook = new AddressBook(FILE_AS_EXPECTED);
        Person eldest = addressBook.findEldestPerson();
        Assert.assertNotNull(eldest);
        Assert.assertEquals("Person[fullName=Wes Jackson,gender=Male,birthDate=1974-08-14]", eldest.toString());

    }

    @Test
    public void testFindPersonsByName() throws IOException {
        addressBook = new AddressBook(FILE_AS_EXPECTED);
        List<Person> matched = addressBook.findPersonsByName("Bill", "Paul");
        Assert.assertNotNull(matched);
        Assert.assertEquals(2, matched.size());

        addressBook = new AddressBook(FILE_AS_EXPECTED_WITH_DIFFERENT_ORDER);
        matched = addressBook.findPersonsByName("Bill", "Paul");
        Assert.assertNotNull(matched);
        Assert.assertEquals(2, matched.size());

        matched = addressBook.findPersonsByName("Bill", "Charles");
        Assert.assertNotNull(matched);
        Assert.assertEquals(1, matched.size());

        addressBook = new AddressBook(FILE_AS_EXPECTED_WITH_SIMILAR_NAMES);
        matched = addressBook.findPersonsByName("Bill", "Sarah");
        Assert.assertNotNull(matched);
        Assert.assertEquals(4, matched.size());

        matched = addressBook.findPersonsByName("Bill Jones", "Sarah Evans");
        Assert.assertNotNull(matched);
        Assert.assertEquals(2, matched.size());
    }

    @Test
    public void testFindAgeDifferenceInDays_WrongParameters() throws IOException {
        addressBook = new AddressBook(FILE_AS_EXPECTED);
        int days = addressBook.findAgeDifferenceInDays("", "");
        Assert.assertEquals("Since person names were not defined, -1 should have been returned", -1, days);

        days = addressBook.findAgeDifferenceInDays("test", "test2");
        Assert.assertEquals("Since persons were not found by their name, -1 should have been returned", -1, days);
    }

    @Test
    public void testFindAgeDifferenceInDays() throws IOException {
        addressBook = new AddressBook(FILE_AS_EXPECTED);
        int days = addressBook.findAgeDifferenceInDays("Bill", "Paul");
        Assert.assertEquals(2862, days);

        days = addressBook.findAgeDifferenceInDays("Bill McKnight", "Paul Robinson");
        Assert.assertEquals(2862, days);

    }
}
