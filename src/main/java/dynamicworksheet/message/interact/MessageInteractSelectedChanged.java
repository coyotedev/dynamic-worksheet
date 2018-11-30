package dynamicworksheet.message.interact;

/**
 * Выбранный элемент изменился (для Selectable {@link dynamicworksheet.element.ElementSelectable}
 */
public class MessageInteractSelectedChanged extends MessageInteract {
    private String mTag;

    public MessageInteractSelectedChanged(String tag) {
        mTag = tag;
    }

    public String getTag() {
        return mTag;
    }
}
