package core.dynamicworksheet.message.interact;

/**
 * Файл в аттаче на аплоад изменился
 */
public class MessageInteractFileChanged extends MessageInteract {
    private core.dynamicworksheet.validation.ValidationUpload.FileParams mParams;

    public MessageInteractFileChanged(core.dynamicworksheet.validation.ValidationUpload.FileParams params) {
        mParams = params;
    }

    public core.dynamicworksheet.validation.ValidationUpload.FileParams getParams() {
        return mParams;
    }
}
