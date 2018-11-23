package dynamicworksheet.message.interact;

import dynamicworksheet.type.Direction;

public class MessageInteractPageChangeRequest extends MessageInteract {
    public Direction mDirection;

    public MessageInteractPageChangeRequest(Direction direction) {
        mDirection = direction;
    }
}
