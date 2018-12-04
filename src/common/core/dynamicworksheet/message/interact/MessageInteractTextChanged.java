package core.dynamicworksheet.message.interact;

import core.dynamicworksheet.element.ElementInput;

/**
 * Текущий текст изменился (для текстовых элементов типа {@link ElementInput})
 */
public class MessageInteractTextChanged extends core.dynamicworksheet.message.interact.MessageInteract {
    private String mText;

    public MessageInteractTextChanged(String text) {
        mText = text;
    }

    public String getText() {
        return mText;
    }
}
