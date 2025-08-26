package training.utils;

import training.entities.Course;

import java.util.ArrayList;

public class Validator {
    public static boolean validateCode(String code) {
        return code.matches("^RA\\d{3}$");
    }

    public static boolean isDulicateCode(String code, ArrayList<Course> courses) {
       return courses.stream().anyMatch(course->course.getCode().equalsIgnoreCase(code));

    }

    public static boolean validateStatus(String status) {
        return status.equalsIgnoreCase("true") || status.equalsIgnoreCase("false");
    }

    public static boolean validateFlag(String flag) {
        return "optional".equalsIgnoreCase(flag) ||
                "prerequisite".equalsIgnoreCase(flag) ||
                "N/A".equalsIgnoreCase(flag);
    }

    public static boolean validateDuration(short duration) {
        return duration >0;
    }
}
