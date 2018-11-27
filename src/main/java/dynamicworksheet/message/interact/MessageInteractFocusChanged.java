package dynamicworksheet.message.interact;

/**
 * Состояние фокуса изменилось
 */
public class MessageInteractFocusChanged extends MessageInteract {
    public boolean mIsFocused;

    public MessageInteractFocusChanged(boolean isFocused) {
        mIsFocused = isFocused;
    }
}
