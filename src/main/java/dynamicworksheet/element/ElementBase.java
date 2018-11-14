package dynamicworksheet.element;

import com.sun.istack.internal.NotNull;
import dynamicworksheet.bundles.IBundle;
import dynamicworksheet.jsondummy.validation.JsonDummyValidation;
import dynamicworksheet.jsondummy.validation.validationcase.IJsonDummyValidationCase;
import dynamicworksheet.jsondummy.validation.validationcase.JsonDummyValidationCaseMinLength;
import dynamicworksheet.jsondummy.validation.validationcase.JsonDummyValidationCaseRequired;
import dynamicworksheet.jsondummy.validation.validationcase.JsonDummyValidationCaseUpload;
import dynamicworksheet.jsondummy.value.IJsonDummyValue;
import dynamicworksheet.jsondummy.value.JsonDummyValueConst;
import dynamicworksheet.jsondummy.value.JsonDummyValuei18n;
import dynamicworksheet.type.UIType;
import dynamicworksheet.util.mutablevalue.MutableFileParams;
import dynamicworksheet.util.mutablevalue.MutableString;
import dynamicworksheet.validation.IValidation;
import dynamicworksheet.validation.ValidationMinLength;
import dynamicworksheet.validation.ValidationRequired;
import dynamicworksheet.validation.ValidationUpload;
import io.reactivex.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public abstract class ElementBase implements IElement {

    protected String mId;
    protected UIType mType;
    protected List<IValidation> mValidations;

    private final IElement mRoot;
    private List<IElement> mChildren;

    private Adapter mRUIAdapter;
    private IValidation.ValidationHandler mValidationHandler;

    public ElementBase(@Nullable IElement root) {
        mRoot = root;
    }

    @Override
    public void setId(String id) {
        mId = id;
    }

    @Override
    public String getid() {
        return mId;
    }

    @Override
    public UIType getType() {
        return mType;
    }

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public void onInteract(IBundle bundle) {

    }

    @Override
    public IElement getRoot() {
        return mRoot;
    }

    @Override
    public void addChild(IElement child) {
        mChildren.add(child);
    }

    @Override
    public void setChildren(List<IElement> children) {
        mChildren = children;
    }

    @Override
    public List<IElement> getChildren() {
        return mChildren;
    }

    @Override
    public void setValidations(List<JsonDummyValidation> validations) {
        if (validations == null) {
            return;
        }
        mValidations = new ArrayList<>();
        for (JsonDummyValidation it : validations) {
            Class<? extends IJsonDummyValidationCase> clazzCase = it.mValid.getClass();
            Class<? extends IJsonDummyValue> clazzError = it.mError.getClass();

            String error = null;
            if (clazzError.isAssignableFrom(JsonDummyValueConst.class)) {
                error = ((JsonDummyValueConst) it.mError).mValue;
            } else if (clazzError.isAssignableFrom(JsonDummyValuei18n.class)) {
                error = ((JsonDummyValuei18n) it.mError).mValue.getMappedValue().get(Locale.getDefault().getLanguage());
            }

            if (clazzCase.isAssignableFrom(JsonDummyValidationCaseRequired.class)) {
                mValidations.add(new ValidationRequired(getValue(), error));
            } else if (clazzCase.isAssignableFrom(JsonDummyValidationCaseMinLength.class)) {
                if (getValue().getClass().isAssignableFrom(MutableString.class)) {
                    mValidations.add(new ValidationMinLength((MutableString) getValue(), ((JsonDummyValidationCaseMinLength) it.mValid).mMinLength, error));
                }
            } else if (clazzCase.isAssignableFrom(JsonDummyValidationCaseUpload.class)) {
                JsonDummyValidationCaseUpload jsonDummy = (JsonDummyValidationCaseUpload) it.mValid;
                if (getValue().getClass().isAssignableFrom(MutableFileParams.class)) {
                    MutableFileParams refParams = new MutableFileParams(new ValidationUpload.FileParams.Builder()
                            .setFileSize(jsonDummy.mMaxSize)
                            .setExtensions(jsonDummy.mExtensions)
                            .setMinWidth(jsonDummy.mMinWidth)
                            .setMaxWidth(jsonDummy.mMaxWidth)
                            .setMinHeight(jsonDummy.mMinHeight)
                            .setMaxHeight(jsonDummy.mMaxHeight)
                            .build());

                    mValidations.add(new ValidationUpload((MutableFileParams) getValue(), refParams, error));
                }
            }
        }
    }

    @Override
    public List<IValidation> getValidations() {
        return mValidations;
    }
}