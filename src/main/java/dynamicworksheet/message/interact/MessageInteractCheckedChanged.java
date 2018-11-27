package dynamicworksheet.message.interact;

/**
 * Состояние выбранности изменилось
 */
public class MessageInteractCheckedChanged extends MessageInteract {
    public boolean mIsChecked;

    public MessageInteractCheckedChanged(boolean isChecked) {
        mIsChecked = isChecked;
    }
}
