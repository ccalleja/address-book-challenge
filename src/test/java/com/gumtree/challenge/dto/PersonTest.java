package com.gumtree.challenge.dto;

import junit.framework.Assert;
import org.joda.time.LocalDate;
import org.junit.Test;

public class PersonTest {

    @Test
    public void testPopulatePerson() {
        String[] record = {"Jimmy White", "Male", "20/11/91"};
        Person person = Person.fromCSVRecord(record);
        Assert.assertNotNull(person);
        Assert.assertEquals("Jimmy White", person.getFullName());
        Assert.assertEquals("Male", person.getGender());
        Assert.assertEquals(new LocalDate(1991, 11, 20), person.getBirthDate());
    }

    @Test
    public void testPopulatePerson_NullChecks() {
        Assert.assertNull("Null input should return null output", Person.fromCSVRecord(null));
        Assert.assertNull("Empty array input should return null output", Person.fromCSVRecord(new String[]{}));


        String[] record = {"Jimmy White", null, "20/11/91"};
        Person person = Person.fromCSVRecord(record);
        Assert.assertNotNull(person);
        Assert.assertNull(person.getGender());

        record = new String[]{null, "Male", "20/11/91"};
        person = Person.fromCSVRecord(record);
        Assert.assertNotNull(person);
        Assert.assertNull(person.getFullName());

        record = new String[]{"Jimmy White", "Male", null};
        person = Person.fromCSVRecord(record);
        Assert.assertNotNull(person);
        Assert.assertNull(person.getBirthDate());

    }
}
