package core.dynamicworksheet.element;

import core.dynamicworksheet.value.IValue;
import core.dynamicworksheet.type.UIType;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Базовый класс наследования для любого элемента.
 * Передаваемые сигналы:
 * - сигнал об изменении состояния видимости {@link core.dynamicworksheet.message.interact.MessageInteractHiddenChanged}
 * Получаемые сигналы: отсутствуют
 * @param <T>{@inheritDoc}
 */
public abstract class ElementBase<T> implements IElement<T> {

    protected String mId;
    protected UIType mType;
    protected IValue<T> mValue;
    protected IValue<Boolean> mHidden;
    protected List<core.dynamicworksheet.validation.IValidation> mValidations;

    private final IElement mRoot;
    private Adapter mRUIAdapter;
    private core.dynamicworksheet.validation.IValidation.ValidationHandler mValidationHandler;
    private List<IElement> mChildren = new ArrayList<>();
    /**
     * Список актуальных подписок для адаптера RUI любого из вложенных ValueSource. Необходимо не
     * забывать очищать от неактуальных подписок при каждой переустановке адаптера, иначе количество
     * действий на onInteract() будет расти без всякого смысла.
     */
    protected List<Disposable> mAdapterSubscribes = new ArrayList<>();

    /**
     * Если указан корень, то добавление элемента в список вложенных для корня будет осуществлено
     * автоматически.
     * @param root
     */
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
    public void onInteract(core.dynamicworksheet.message.interact.MessageInteract message) {}

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
                    adapter.onInteract(new core.dynamicworksheet.message.interact.MessageInteractHiddenChanged(hidden));
                }
            }));
        }
    }

    @Override
    public Adapter getAdapter() {
        return mRUIAdapter;
    }

    @Override
    public void setValidationHandler(core.dynamicworksheet.validation.IValidation.ValidationHandler handler) {
        mValidationHandler = handler;
    }

    @Override
    public core.dynamicworksheet.validation.IValidation.ValidationHandler getValidationHandler() {
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
    public void setValidations(List<core.dynamicworksheet.jsondummy.validation.JsonDummyValidation> validations) {
        if (validations == null) {
            return;
        }
        mValidations = new ArrayList<>();
        for (core.dynamicworksheet.jsondummy.validation.JsonDummyValidation it : validations) {
            Class<? extends core.dynamicworksheet.jsondummy.validation.validationcase.IJsonDummyValidationCase> clazzCase = it.mValid.getClass();

            String error = "";
            if (it.mError != null) {
                Class<? extends core.dynamicworksheet.jsondummy.value.IJsonDummyValue> clazzError = it.mError.getClass();
                if (clazzError.isAssignableFrom(core.dynamicworksheet.jsondummy.value.JsonDummyValueConst.class)) {
                    error = ((core.dynamicworksheet.jsondummy.value.JsonDummyValueConst) it.mError).mValue;
                } else if (clazzError.isAssignableFrom(core.dynamicworksheet.jsondummy.value.JsonDummyValuei18n.class)) {
                    error = ((core.dynamicworksheet.jsondummy.value.JsonDummyValuei18n) it.mError).mValue.getMappedValue().get(Locale.getDefault().getLanguage());
                } // TODO: add others
            }

            if (clazzCase.isAssignableFrom(core.dynamicworksheet.jsondummy.validation.validationcase.JsonDummyValidationCaseRequired.class)) {
                mValidations.add(new core.dynamicworksheet.validation.ValidationRequired(getValue(), error));
            } else if (clazzCase.isAssignableFrom(core.dynamicworksheet.jsondummy.validation.validationcase.JsonDummyValidationCaseMinLength.class)) {
                if (getValue().getValue().getClass().isAssignableFrom(String.class)) {
                    mValidations.add(new core.dynamicworksheet.validation.ValidationMinLength((IValue<String>) getValue(), ((core.dynamicworksheet.jsondummy.validation.validationcase.JsonDummyValidationCaseMinLength) it.mValid).mMinLength, error));
                }
            } else if (clazzCase.isAssignableFrom(core.dynamicworksheet.jsondummy.validation.validationcase.JsonDummyValidationCaseUpload.class)) {
                if (getValue().getValue() == null) {
                    return;
                }
                core.dynamicworksheet.jsondummy.validation.validationcase.JsonDummyValidationCaseUpload jsonDummy = (core.dynamicworksheet.jsondummy.validation.validationcase.JsonDummyValidationCaseUpload) it.mValid;
                if (getValue().getValue().getClass().isAssignableFrom(core.dynamicworksheet.validation.ValidationUpload.FileRefParams.class)) {
                    core.dynamicworksheet.validation.ValidationUpload.FileRefParams refParams = new core.dynamicworksheet.validation.ValidationUpload.FileRefParams.Builder()
                            .setFileSize(jsonDummy.mMaxSize)
                            .setExtensions(jsonDummy.mExtensions)
                            .setMinWidth(jsonDummy.mMinWidth)
                            .setMaxWidth(jsonDummy.mMaxWidth)
                            .setMinHeight(jsonDummy.mMinHeight)
                            .setMaxHeight(jsonDummy.mMaxHeight)
                            .build();

                    mValidations.add(new core.dynamicworksheet.validation.ValidationUpload((IValue<core.dynamicworksheet.validation.ValidationUpload.FileParams>) getValue(), refParams, error));
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
        for (core.dynamicworksheet.validation.IValidation it : mValidations) {
            ret &= it.check(mValidationHandler);
        }
        return ret;
    }
}