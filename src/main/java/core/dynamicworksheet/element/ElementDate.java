package core.dynamicworksheet.element;

import core.dynamicworksheet.type.UIType;
import core.dynamicworksheet.value.IValue;
import io.reactivex.annotations.Nullable;

/**
 * Элемент - текстовый ввод даты.
 * Отличается от обычного ввода наличием обязательной маски, определяющей формат ввода даты.
 */
public class ElementDate extends core.dynamicworksheet.element.ElementInput {
    private String mDateFormat;

    public ElementDate(@Nullable IElement root, IValue<String> value) {
        super(root, value);
        mType = UIType.Date;
    }

    public void setDateFormat(String format) {
        mDateFormat = format;
    }

    public String getDateFormat() {
        return mDateFormat;
    }
}
