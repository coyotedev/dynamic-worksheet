package dynamicworksheet.element;

import dynamicworksheet.type.UIType;
import dynamicworksheet.value.IValue;
import io.reactivex.annotations.Nullable;

public class ElementDate extends ElementInput {
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
