package org.milan.banking;

import java.util.function.Supplier;

/**
 * Custom exception thrown when requested record doesn't exist
 *
 * @author Milan Rathod
 */
public class NoSuchRecordExistsException extends Exception implements Supplier<NoSuchRecordExistsException> {

    public NoSuchRecordExistsException(String message) {
        super(message);
    }

    @Override
    public NoSuchRecordExistsException get() {
        return this;
    }
}
