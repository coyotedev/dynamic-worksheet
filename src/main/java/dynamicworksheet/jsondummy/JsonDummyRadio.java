package dynamicworksheet.jsondummy;

import com.google.gson.annotations.SerializedName;

import dynamicworksheet.Value.ValueSimple;
import dynamicworksheet.element.ElementBase;
import dynamicworksheet.element.ElementSelectable.ElementRadioGroup;
import dynamicworksheet.element.ElementSelectable.ElementSelectableBase;
import dynamicworksheet.element.IElement;
import dynamicworksheet.jsondummy.option.JsonDummyOption;
import dynamicworksheet.jsondummy.value.IJsonDummyValue;
import dynamicworksheet.option.Option;
import dynamicworksheet.type.RadioSubType;
import dynamicworksheet.type.RadioType;

import java.util.ArrayList;
import java.util.List;

public class JsonDummyRadio extends JsonDummyBase {
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
    public IJsonDummyValue mLabel;
    @SerializedName("options")
    public List<JsonDummyOption> mOptions;

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
            for (JsonDummyOption it : mOptions) {
                options.add(it.get());
            }
            casted.setOptions(options);
        }
        if (mLabel != null) {
            casted.setLabel(mLabel.getValue(casted));
        }
    }
}
