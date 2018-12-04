package core.dynamicworksheet.jsondummy;

import com.google.gson.annotations.SerializedName;

import core.dynamicworksheet.value.ValueSimple;
import core.dynamicworksheet.element.ElementBase;
import core.dynamicworksheet.element.ElementSelectable.ElementRadioGroup;
import core.dynamicworksheet.element.ElementSelectable.ElementSelectableBase;
import core.dynamicworksheet.element.IElement;
import core.dynamicworksheet.option.Option;
import core.dynamicworksheet.type.RadioSubType;
import core.dynamicworksheet.type.RadioType;

import java.util.ArrayList;
import java.util.List;

public class JsonDummyRadio extends core.dynamicworksheet.jsondummy.JsonDummyBase {
    @SerializedName("bindPath")
    public String mBindPath;
    @SerializedName("kind")
    public RadioType mType;
    @SerializedName("subType")
    public RadioSubType mSubType;
    @SerializedName("about")
    public String mAbout;
    @SerializedName("defaultValue")
    public String mDefaultValue;
    @SerializedName("mask")
    public String mMask;
    @SerializedName("prefix")
    public String mPrefix;
    @SerializedName("suffix")
    public String mSuffix;
    @SerializedName("label")
    public core.dynamicworksheet.jsondummy.value.IJsonDummyValue mLabel;
    @SerializedName("options")
    public List<core.dynamicworksheet.jsondummy.option.JsonDummyOption> mOptions;

    @Override
    public IElement getElement(IElement root) {
        ElementRadioGroup ret = new ElementRadioGroup(root, new ValueSimple<>(""));
        set(ret);
        return ret;
    }

    @Override
    protected void set(ElementBase element) {
        super.set(element);
        ElementSelectableBase casted = (ElementSelectableBase) element;
        if (mOptions != null && !mOptions.isEmpty()) {
            List<Option> options = new ArrayList<>();
            for (core.dynamicworksheet.jsondummy.option.JsonDummyOption it : mOptions) {
                options.add(it.get());
            }
            casted.setOptions(options);
        }
        if (mLabel != null) {
            casted.setLabel(mLabel.getValue(casted));
        }
    }
}
