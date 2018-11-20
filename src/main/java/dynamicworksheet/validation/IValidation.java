package dynamicworksheet.validation;

public interface IValidation {
    abstract class ValidationHandler {
        public abstract void onPassed();
        public abstract void onError(String error);
    }

    void check(ValidationHandler handler);
    boolean isPassed();
    String getError();
}
