package dynamicworksheet.element;

import dynamicworksheet.value.IValue;
import dynamicworksheet.message.interact.MessageInteract;
import dynamicworksheet.message.interact.MessageInteractFocusChanged;
import dynamicworksheet.message.interact.MessageInteractTextChanged;
import dynamicworksheet.type.UIType;
import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Consumer;

/**
 * Элемент - текстовый ввод
 * Передаваемые сигналы:
 * - сигнал об изменении текста {@link MessageInteractTextChanged}
 * Получаемые сигналы:
 * - сигнал об изменении текста {@link MessageInteractTextChanged}
 * - сигнал об изменении фокуса элемента {@link MessageInteractFocusChanged}
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
    public void onInteract(MessageInteract message) {
        super.onInteract(message);
        Class<? extends MessageInteract> clazz = message.getClass();
        if (clazz.isAssignableFrom(MessageInteractTextChanged.class)) {
            MessageInteractTextChanged mess = (MessageInteractTextChanged) message;
            getValue().setValue(mess.getText());
        } else if (clazz.isAssignableFrom(MessageInteractFocusChanged.class)) {
            MessageInteractFocusChanged mess = (MessageInteractFocusChanged) message;
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
                getAdapter().onInteract(new MessageInteractTextChanged(text));
            }
        }));
    }
}
