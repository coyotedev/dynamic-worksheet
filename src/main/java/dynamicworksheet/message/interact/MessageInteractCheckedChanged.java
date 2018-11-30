package dynamicworksheet.message.interact;

/**
 * Состояние выбранности изменилось
 */
public class MessageInteractCheckedChanged extends MessageInteract {
    private boolean mIsChecked;

    public MessageInteractCheckedChanged(boolean isChecked) {
        mIsChecked = isChecked;
    }

    public boolean isChecked() {
        return mIsChecked;
    }
}
