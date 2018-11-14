package dynamicworksheet.validation;

public interface IValidation {
    abstract class ValidationHandler {
        abstract void onPassed();
        abstract void onError(String error);
    }

    void check(ValidationHandler handler);
    boolean isPassed();
    String getError();
}
