package dynamicworksheet.element;

import dynamicworksheet.value.IValue;
import dynamicworksheet.type.UIType;
import io.reactivex.annotations.Nullable;

public class ElementImageUrl extends ElementBase<String> {
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
