package dynamicworksheet.element;

import dynamicworksheet.value.IValue;
import dynamicworksheet.type.UIType;
import io.reactivex.annotations.Nullable;

/**
 * Элемент - ссылка на картинку.
 * Представляет картинку, источник которой является ссылкой.
 * Базовое значение - url, параметры см. {@link Size}
 */
public class ElementImageUrl extends ElementBase<String> {
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

    public ElementImageUrl(@Nullable IElement root, IValue<String> value) {
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
}
