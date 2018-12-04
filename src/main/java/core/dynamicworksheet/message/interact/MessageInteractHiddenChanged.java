package core.dynamicworksheet.message.interact;

/**
 * Состояние видимости элемента изменилось
 */
public class MessageInteractHiddenChanged extends MessageInteract {
    private boolean mHidden;

    public MessageInteractHiddenChanged(boolean hidden) {
        mHidden = hidden;
    }

    public boolean isHidden() {
        return mHidden;
    }
}
