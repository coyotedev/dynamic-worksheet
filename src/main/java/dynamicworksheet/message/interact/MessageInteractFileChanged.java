package dynamicworksheet.message.interact;

import dynamicworksheet.validation.ValidationUpload;

/**
 * Файл в аттаче на аплоад изменился
 */
public class MessageInteractFileChanged extends MessageInteract {
    private ValidationUpload.FileParams mParams;

    public MessageInteractFileChanged(ValidationUpload.FileParams params) {
        mParams = params;
    }

    public ValidationUpload.FileParams getParams() {
        return mParams;
    }
}
