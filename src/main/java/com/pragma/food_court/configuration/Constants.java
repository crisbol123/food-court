package com.pragma.food_court.configuration;

public class Constants {
    private Constants() {
        throw new IllegalStateException("Utility class");
    }
 public enum entities {
       ARTICLE, CATEGORY, MARK
        }

    // General Exception Messages
    public static final String NO_DATA_FOUND_EXCEPTION_MESSAGE = "No data was found in the database";
    public static final String ELEMENT_NOT_FOUND_EXCEPTION_MESSAGE = "The specified element does not exist";
    public static final String ELEMENT_ALREADY_EXISTS_EXCEPTION_MESSAGE = "The %s already exists";
  public static final String EMAIL = "email";
  public static final String INVALID_OWNER_EXCEPTION_MESSAGE = "The owner does not exist";


}
