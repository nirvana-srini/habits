package com.nirvana.habits.journal;

public class UserDTO {
    // Presence Check
    private String firstName; // Format Check - There should not be any numbers or special chars
    private String lastName; // Format Check -There should not be any numbers or special chars
    private String countryCode;// Length check - Three letter code
    private String age; // Range Check - can not be below 0
    private String gender; // Lookup table - Input table should support only 3 values Male Female and Others.
    private char[] password; // No %, Remove harmful characters
}
