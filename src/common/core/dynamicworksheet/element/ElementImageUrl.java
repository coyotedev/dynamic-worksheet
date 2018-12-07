package core.dynamicworksheet.element;

import core.dynamicworksheet.value.IValue;
import core.dynamicworksheet.type.UIType;
import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Consumer;

/**
 * Элемент - ссылка на картинку.
 * Представляет картинку, источник которой является ссылкой.
 * Базовое значение - url, параметры см. {@link Size}
 */
public class ElementImageUrl extends core.dynamicworksheet.element.ElementBase<String> {
    /**
     * Основные параметры элемента {@link ElementImageUrl} - ширина и высота отображаемой картинки
     */
    public static class Size {
        private int mWidth;
        private int mHeight;

        public Size(int width, int height) {
            mWidth = width;
            mHeight = height;
        }

        public int getWidth() {
            return mWidth;
        }

        public int getHeight() {
            return mHeight;
        }
    }
    private Size mSize;

    public ElementImageUrl(@Nullable core.dynamicworksheet.element.IElement root, IValue<String> value) {
        super(root);
        mType = UIType.ImageView;
        setValue(value);
    }

    public void setSize(Size size) {
        mSize = size;
    }

    public Size getSize() {
        return mSize;
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        mAdapterSubscribes.add(getValue().getObservable().subscribe(new Consumer<String>() {
            @Override
            public void accept(String url) throws Exception {
                getAdapter().onInteract(new core.dynamicworksheet.message.interact.MessageInteractTextChanged(url));
            }
        }));
    }
}
