package dynamicworksheet.message.interact;

/**
 * Состояние фокуса изменилось
 */
public class MessageInteractFocusChanged extends MessageInteract {
    private boolean mIsFocused;

    public MessageInteractFocusChanged(boolean isFocused) {
        mIsFocused = isFocused;
    }

    public boolean isFocused() {
        return mIsFocused;
    }
}
