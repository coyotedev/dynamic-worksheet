package core.dynamicworksheet.value;

import core.dynamicworksheet.element.IElement;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Интерфейс ValueSource'а.
 * Связь между элементами явлется связью между их ValueSource'ами.
 * Связь между ValueSource'ами устанавливается, где необходимо, в конструкторе соответствующего
 * источника. Например, связи имеют {@link ValueReference}, {@link ValueLogicalOperation}.
 * Связь обеспечивается механизмом rxjava - {@link io.reactivex.subjects.BehaviorSubject}. Особенность
 * данного механизма в том, что при установке подписки он сразу же отдаёт своё последнее значение.
 * Таким образом реализована инициализация зависимых ValueSource'ов - даже если в дальнейшем с
 * источником, от которого установлена зависимость, ничего не будет происходить, то значение будет
 * получено как минимум один раз.
 *
 * @param <T>Тип, полученный от родительского {@link IElement}
 */
public interface IValue<T> {
    void setValue(T value);
    T getValue();

    BehaviorSubject<T> getObservable();
}
