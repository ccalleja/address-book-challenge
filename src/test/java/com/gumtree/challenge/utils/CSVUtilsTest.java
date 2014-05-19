package com.gumtree.challenge.utils;

import com.gumtree.challenge.dto.AddressBookTest;
import com.gumtree.challenge.dto.Person;
import junit.framework.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;

public class CSVUtilsTest {

    public static final String FILE_WITH_BLANKS = "src/test/resources/AddressBookWithBlanks";

    @Test
    public void testParseCSVFile() throws IOException {
        List<Person> persons = CSVUtils.parseCSVFile(AddressBookTest.FILE_AS_EXPECTED);
        Assert.assertFalse("The list should not be null or empty", isEmpty(persons));
        Assert.assertEquals("The list should contain 5 items", 5, persons.size());

    }

    @Test
    public void testParseCSVFile_WithMissingFields() throws IOException {
        List<Person> persons = CSVUtils.parseCSVFile(FILE_WITH_BLANKS);
        Assert.assertFalse("The list should not be null or empty", isEmpty(persons));
        Assert.assertEquals("The list should contain 5 items", 5, persons.size());

        Assert.assertNull("2nd csv record had gender field empty", persons.get(1).getGender());
        Assert.assertNull("4th csv record had birth date field empty", persons.get(3).getBirthDate());
        Assert.assertNull("5th csv record had name field empty", persons.get(4).getFullName());

    }
}
