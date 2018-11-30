package dynamicworksheet.message.interact;

import dynamicworksheet.type.Direction;

/**
 * Запрос на изменение текущей страницы (для {@link dynamicworksheet.element.ElementWizard})
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
