package core.dynamicworksheet.message.interact;

import core.dynamicworksheet.element.ElementFileUpload;

/**
 * Файл в аттаче на аплоад изменился
 */
public class MessageInteractFileChanged extends MessageInteract {
    private ElementFileUpload.FileParams mParams;

    public MessageInteractFileChanged(ElementFileUpload.FileParams params) {
        mParams = params;
    }

    public ElementFileUpload.FileParams getParams() {
        return mParams;
    }
}
