package dynamicworksheet.element;

import dynamicworksheet.Value.IValue;
import dynamicworksheet.jsondummy.validation.JsonDummyValidation;
import dynamicworksheet.jsondummy.validation.validationcase.IJsonDummyValidationCase;
import dynamicworksheet.jsondummy.validation.validationcase.JsonDummyValidationCaseMinLength;
import dynamicworksheet.jsondummy.validation.validationcase.JsonDummyValidationCaseRequired;
import dynamicworksheet.jsondummy.validation.validationcase.JsonDummyValidationCaseUpload;
import dynamicworksheet.jsondummy.value.IJsonDummyValue;
import dynamicworksheet.jsondummy.value.JsonDummyValueConst;
import dynamicworksheet.jsondummy.value.JsonDummyValuei18n;
import dynamicworksheet.message.interact.MessageInteract;
import dynamicworksheet.message.interact.MessageInteractHiddenChanged;
import dynamicworksheet.type.UIType;
import dynamicworksheet.validation.IValidation;
import dynamicworksheet.validation.ValidationMinLength;
import dynamicworksheet.validation.ValidationRequired;
import dynamicworksheet.validation.ValidationUpload;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public abstract class ElementBase<T> implements IElement<T> {

    protected String mId;
    protected UIType mType;
    protected IValue<T> mValue;
    protected IValue<Boolean> mHidden;
    protected List<IValidation> mValidations;

    private final IElement mRoot;
    private Adapter mRUIAdapter;
    private IValidation.ValidationHandler mValidationHandler;
    private List<IElement> mChildren = new ArrayList<>();
    protected List<Disposable> mAdapterSubscribes = new ArrayList<>();

    protected ElementBase(@Nullable IElement root) {
        mRoot = root;
        if (mRoot != null) {
            mRoot.addChild(this);
        }
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
    public void setHidden(IValue<Boolean> value) {
        mHidden = value;
    }

    @Override
    public boolean getHidden() {
        return mHidden.getValue();
    }

    @Override
    public IValue<T> getValue() {
        return mValue;
    }

    @Override
    public void setValue(IValue<T> value) {
        mValue = value;
    }

    @Override
    public void onInteract(MessageInteract message) {

    }

    @Override
    public IElement getRoot() {
        return mRoot;
    }

    @Override
    public void setAdapter(Adapter adapter) {
        // отписываем предыдущих подписантов, чтобы они больше не задерживали предыдущий адаптер в памяти
        for (Disposable it : mAdapterSubscribes) {
            it.dispose();
        }
        mAdapterSubscribes.clear();
        mRUIAdapter = adapter;
        if (mHidden != null) {
            mAdapterSubscribes.add(mHidden.getObservable().subscribe(new Consumer<Boolean>() {
                @Override
                public void accept(Boolean hidden) throws Exception {
                    adapter.onInteract(new MessageInteractHiddenChanged(hidden));
                }
            }));
        }
    }

    @Override
    public Adapter getAdapter() {
        return mRUIAdapter;
    }

    @Override
    public void setValidationHandler(IValidation.ValidationHandler handler) {
        mValidationHandler = handler;
    }

    @Override
    public IValidation.ValidationHandler getValidationHandler() {
        return mValidationHandler;
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

            String error = "";
            if (it.mError != null) {
                Class<? extends IJsonDummyValue> clazzError = it.mError.getClass();
                if (clazzError.isAssignableFrom(JsonDummyValueConst.class)) {
                    error = ((JsonDummyValueConst) it.mError).mValue;
                } else if (clazzError.isAssignableFrom(JsonDummyValuei18n.class)) {
                    error = ((JsonDummyValuei18n) it.mError).mValue.getMappedValue().get(Locale.getDefault().getLanguage());
                } // TODO: add others
            }

            if (clazzCase.isAssignableFrom(JsonDummyValidationCaseRequired.class)) {
                mValidations.add(new ValidationRequired(getValue(), error));
            } else if (clazzCase.isAssignableFrom(JsonDummyValidationCaseMinLength.class)) {
                if (getValue().getValue().getClass().isAssignableFrom(String.class)) {
                    mValidations.add(new ValidationMinLength((IValue<String>) getValue(), ((JsonDummyValidationCaseMinLength) it.mValid).mMinLength, error));
                }
            } else if (clazzCase.isAssignableFrom(JsonDummyValidationCaseUpload.class)) {
                JsonDummyValidationCaseUpload jsonDummy = (JsonDummyValidationCaseUpload) it.mValid;
                if (getValue().getValue().getClass().isAssignableFrom(ValidationUpload.FileParams.class)) {
                    ValidationUpload.FileParams refParams = new ValidationUpload.FileParams.Builder()
                            .setFileSize(jsonDummy.mMaxSize)
                            .setExtensions(jsonDummy.mExtensions)
                            .setMinWidth(jsonDummy.mMinWidth)
                            .setMaxWidth(jsonDummy.mMaxWidth)
                            .setMinHeight(jsonDummy.mMinHeight)
                            .setMaxHeight(jsonDummy.mMaxHeight)
                            .build();

                    mValidations.add(new ValidationUpload((IValue<ValidationUpload.FileParams>) getValue(), refParams, error));
                }
            }
        }
    }

    @Override
    public boolean checkValid() {
        if (mValidations == null) {
            return true;
        }
        boolean ret = true;
        for (IValidation it : mValidations) {
            ret &= it.check(mValidationHandler);
        }
        return ret;
    }
}