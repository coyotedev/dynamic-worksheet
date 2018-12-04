package core.dynamicworksheet.element;

import core.dynamicworksheet.value.IValue;
import core.dynamicworksheet.type.UIType;
import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Consumer;

/**
 * Элемент - текстовый ввод
 * Передаваемые сигналы:
 * - сигнал об изменении текста {@link core.dynamicworksheet.message.interact.MessageInteractTextChanged}
 * Получаемые сигналы:
 * - сигнал об изменении текста {@link core.dynamicworksheet.message.interact.MessageInteractTextChanged}
 * - сигнал об изменении фокуса элемента {@link core.dynamicworksheet.message.interact.MessageInteractFocusChanged}
 */
public class ElementInput extends ElementBase<String> {

    private String mPlaceholder;

    public ElementInput(@Nullable IElement root, IValue<String> value) {
        super(root);
        mType = UIType.Input;
        setValue(value);
    }

    public void setPlaceholder(String placeholder) {
        mPlaceholder = placeholder;
    }

    public String getPlaceholder() {
        return mPlaceholder;
    }

    @Override
    public void onInteract(core.dynamicworksheet.message.interact.MessageInteract message) {
        super.onInteract(message);
        Class<? extends core.dynamicworksheet.message.interact.MessageInteract> clazz = message.getClass();
        if (clazz.isAssignableFrom(core.dynamicworksheet.message.interact.MessageInteractTextChanged.class)) {
            core.dynamicworksheet.message.interact.MessageInteractTextChanged mess = (core.dynamicworksheet.message.interact.MessageInteractTextChanged) message;
            getValue().setValue(mess.getText());
        } else if (clazz.isAssignableFrom(core.dynamicworksheet.message.interact.MessageInteractFocusChanged.class)) {
            core.dynamicworksheet.message.interact.MessageInteractFocusChanged mess = (core.dynamicworksheet.message.interact.MessageInteractFocusChanged) message;
            if (!mess.isFocused()) {
                // check valid on focus lost
                checkValid();
            } else {
                // do nothing in a moment
            }
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        mAdapterSubscribes.add(getValue().getObservable().subscribe(new Consumer<String>() {
            @Override
            public void accept(String text) throws Exception {
                getAdapter().onInteract(new core.dynamicworksheet.message.interact.MessageInteractTextChanged(text));
            }
        }));
    }
}
