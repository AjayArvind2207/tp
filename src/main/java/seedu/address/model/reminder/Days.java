package seedu.address.model.reminder;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the number of days the users want to set reminder for the end application date.
 */
public class Days {
    public static final String MESSAGE_CONSTRAINTS =
            "Number of days should only numbers";

    public static final String VALIDATION_REGEX = "\\d{1,}";
    public final long numOfDays;

    /**
     * Constructs a {@code Days}
     *
     * @param days A valid number of day.
     */
    public Days(String days) {
        requireNonNull(days);
        checkArgument(isValidDays(days), MESSAGE_CONSTRAINTS);
        this.numOfDays = Long.parseLong(days);
    }

    public Days() {
        this.numOfDays = 0;
    }

    public long getNumOfDays() {
        return this.numOfDays;
    }

    /**
     * Returns true if a given string is a valid number of days
     */
    public static boolean isValidDays(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return String.valueOf(numOfDays);
    }
}
