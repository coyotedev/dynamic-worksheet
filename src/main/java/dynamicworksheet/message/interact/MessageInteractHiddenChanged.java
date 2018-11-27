package dynamicworksheet.message.interact;

/**
 * Состояние видимости элемента изменилось
 */
public class MessageInteractHiddenChanged extends MessageInteract {
    public boolean mHidden;

    public MessageInteractHiddenChanged(boolean hidden) {
        mHidden = hidden;
    }
}
