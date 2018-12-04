package core.dynamicworksheet.message.interact;

import core.dynamicworksheet.element.ElementWizard;
import core.dynamicworksheet.type.Direction;

/**
 * Запрос на изменение текущей страницы (для {@link ElementWizard})
 * Направление сообщения - Real UI -> Core UI
 */
public class MessageInteractPageChangeRequest extends MessageInteract {
    private Direction mDirection;

    public MessageInteractPageChangeRequest(Direction direction) {
        mDirection = direction;
    }

    public Direction getDirection() {
        return mDirection;
    }
}
