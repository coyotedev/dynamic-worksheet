package core.dynamicworksheet.element;

import core.dynamicworksheet.value.IValue;
import core.dynamicworksheet.type.UIType;
import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Consumer;

/**
 * Элемент - лейбл.
 * Передаваемые сигналы:
 * - сигнал об изменении текста {@link core.dynamicworksheet.message.interact.MessageInteractTextChanged}
 * Получаемые сигналы: отсутствуют
 */
public class ElementText extends core.dynamicworksheet.element.ElementBase<String> {

    public ElementText(@Nullable IElement root, IValue<String> value) {
        super(root);
        mType = UIType.Text;
        setValue(value);
    }

    @Override
    public void setAdapter(IElement.Adapter adapter) {
        super.setAdapter(adapter);
        mAdapterSubscribes.add(getValue().getObservable().subscribe(new Consumer<String>() {
            @Override
            public void accept(String text) throws Exception {
                getAdapter().onInteract(new core.dynamicworksheet.message.interact.MessageInteractTextChanged(text));
            }
        }));
    }
}
