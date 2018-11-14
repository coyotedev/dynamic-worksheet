package dynamicworksheet.element;

import dynamicworksheet.type.UIType;
import dynamicworksheet.util.mutablevalue.MutableString;

public class ElementInput extends ElementBase {

    private MutableString mText;

    public ElementInput(IElement root) {
        super(root);
        mType = UIType.Input;
        mText = new MutableString("");
    }

    public String getText() {
        return mText.getValue();
    }

    public void setText(String text) {
        mText.setValue(text);
    }

    @Override
    public Object getValue() {
        return mText;
    }
}
