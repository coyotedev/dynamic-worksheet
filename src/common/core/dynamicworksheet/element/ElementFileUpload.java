package core.dynamicworksheet.element;

import core.dynamicworksheet.type.UIType;
import core.dynamicworksheet.validation.ValidationUpload;
import core.dynamicworksheet.value.IValue;
import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Consumer;

/**
 * Элемент - загрузка файла.
 * Представляет область, взаимодействуя с которой пользователь может указать файл на устройстве,
 * необходимый для аплоада в данном участке анкеты.
 * Базовый тип - {@link FileParams}
 * Может иметь текстовый плейсхолдер.
 * Передаваемые сигналы:
 * - сигнал о изменении параметров файла {@link core.dynamicworksheet.message.interact.MessageInteractFileChanged}
 * Получаемые сигналы:
 * - сигнал о изменении параметров файла {@link core.dynamicworksheet.message.interact.MessageInteractFileChanged}
 */
public class ElementFileUpload extends core.dynamicworksheet.element.ElementBase<ElementFileUpload.FileParams> {
    public static class FileParams {
        private String mPath;
        private String mSize;
        private String mExtensions;
        private Integer mWidth;
        private Integer mHeight;

        public FileParams(String path, String size, String ext, int width, int height) {
            mPath = path;
            mSize = size;
            mExtensions = ext;
            mWidth = width;
            mHeight = height;
        }

        public String getPath() {
            return mPath;
        }

        public String getSize() {
            return mSize;
        }

        public String getExtensions() {
            return mExtensions;
        }

        public Integer getWidth() {
            return mWidth;
        }

        public Integer getHeight() {
            return mHeight;
        }
    }

    private String mPlaceholder;

    public ElementFileUpload(@Nullable core.dynamicworksheet.element.IElement root, IValue<FileParams> value) {
        super(root);
        mType = UIType.FileUpload;
        setValue(value);
    }

    public void setPlaceholder(String placeholder) {
        mPlaceholder = placeholder;
    }

    public String getPlaceholder() {
        return mPlaceholder;
    }

    @Override
    public void onInteract(core.dynamicworksheet.message.interact.MessageInteract message) {
        super.onInteract(message);
        if (message.getClass().isAssignableFrom(core.dynamicworksheet.message.interact.MessageInteractFileChanged.class)) {
            core.dynamicworksheet.message.interact.MessageInteractFileChanged msg = (core.dynamicworksheet.message.interact.MessageInteractFileChanged) message;
            getValue().setValue(msg.getParams());
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        mAdapterSubscribes.add(getValue().getObservable().subscribe(new Consumer<FileParams>() {
            @Override
            public void accept(FileParams fileParams) throws Exception {
                getAdapter().onInteract(new core.dynamicworksheet.message.interact.MessageInteractFileChanged(fileParams));
            }
        }));
    }
}
