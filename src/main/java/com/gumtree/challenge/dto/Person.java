package com.gumtree.challenge.dto;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import static org.apache.commons.lang.StringUtils.*;

/**
 * This data transfer object represents a person and it's details
 */
public class Person {

    private static final String DATE_FORMAT = "dd/MM/yy";

    public enum Gender{
        MALE,
        FEMALE
    }

    private String fullName;

    private String gender;

    private LocalDate birthDate;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    /**
     * Build a {@link Person} object from a csv record.
     * This method also does not assume all fields are populated however
     * there is an assumption that each record will hold 3 fields.
     */
    public static Person fromCSVRecord(String[] record) {
        if (ArrayUtils.isEmpty(record)) return null;

        Person person = new Person();
        person.setFullName(isBlank(record[0]) ? null : trim(record[0]));
        person.setGender(isBlank(record[1]) ? null : trim(record[1]));
        if (isNotBlank(record[2])) {
            DateTimeFormatter dtf = DateTimeFormat.forPattern(DATE_FORMAT);
            DateTime birthDate = dtf.parseDateTime(trim(record[2]));
            person.setBirthDate(birthDate.toLocalDate());
        }
        return person;
    }
}
