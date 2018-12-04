package core.dynamicworksheet.element;

import core.dynamicworksheet.value.IValue;
import core.dynamicworksheet.type.UIType;
import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Consumer;

/**
 * Элемент - чекбокс с лейблом
 * Передаваемые сигналы:
 * - Сигнал об изменении выбранности {@link core.dynamicworksheet.message.interact.MessageInteractCheckedChanged}
 * Получаемые сигналы:
 * - Сигнал об изменении выбранности {@link core.dynamicworksheet.message.interact.MessageInteractCheckedChanged}
 */
public class ElementCheckbox extends core.dynamicworksheet.element.ElementBase<Boolean> {

    private String mLabel;

    public ElementCheckbox(@Nullable IElement root, IValue<Boolean> value) {
        super(root);
        mType = UIType.CheckBox;
        setValue(value);
    }

    public void setLabel(String label) {
        mLabel = label;
    }

    public String getLabel() {
        return mLabel;
    }

    @Override
    public void onInteract(core.dynamicworksheet.message.interact.MessageInteract message) {
        super.onInteract(message);
        Class<? extends core.dynamicworksheet.message.interact.MessageInteract> clazz = message.getClass();
        if (clazz.isAssignableFrom(core.dynamicworksheet.message.interact.MessageInteractCheckedChanged.class)) {
            core.dynamicworksheet.message.interact.MessageInteractCheckedChanged mess = (core.dynamicworksheet.message.interact.MessageInteractCheckedChanged) message;
            getValue().setValue(mess.isChecked());
        }
    }

    @Override
    public void setAdapter(IElement.Adapter adapter) {
        super.setAdapter(adapter);
        mAdapterSubscribes.add(getValue().getObservable().subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean value) throws Exception {
                adapter.onInteract(new core.dynamicworksheet.message.interact.MessageInteractCheckedChanged(value));
            }
        }));
    }
}
