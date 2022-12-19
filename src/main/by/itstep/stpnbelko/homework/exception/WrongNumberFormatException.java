package by.itstep.stpnbelko.homework.exception;

public class WrongNumberFormatException extends NumberFormatException {

    public WrongNumberFormatException() {
        super();
    }

    public WrongNumberFormatException(String s) {
        super(s);
    }

    @Override
    public String getMessage() {
        return "Wrong number format";
    }

    @Override
    public synchronized Throwable getCause() {
        return super.getCause();
    }

    @Override
    public String toString() {
        return super.toString();
    }


}
