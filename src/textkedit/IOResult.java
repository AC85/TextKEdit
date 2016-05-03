package textkedit;

import java.io.IOException;

/**
 * Created by Alex on 25.04.16.
 */
public class IOResult<T> {


    // variablen
    private T data;
    private IOException exception;

    public IOResult(T data, IOException exception) {
        this.exception = exception;
        this.data = data;
    }

    public boolean wasSuccessful() {
        return this.exception == null;
    }

    public boolean hasData() {
        return this.data != null;
    }

    public T getData() {
        return data;
    }
}
