package textkedit;

/**
 * Created by Flo on 25.04.16.
 */
public class IOResult<T> {

    private T data;
    private boolean error;

    public IOResult(boolean _error, T data) {
        this.error = _error;
        this.data = data;
    }

    public boolean hasFailed() {
        return this.error;
    }

    public boolean hasData() {
        return this.data != null;
    }

    public T getData() {
        return data;
    }
}
