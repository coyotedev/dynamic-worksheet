package dynamicworksheet.message.interact;

/**
 * Текущий текст изменился (для текстовых элементов типа {@link dynamicworksheet.element.ElementInput})
 */
public class MessageInteractTextChanged extends MessageInteract {

    public String mText;

    public MessageInteractTextChanged(String text) {
        mText = text;
    }
}
