package dynamicworksheet.message.interact;

import dynamicworksheet.element.ElementWizard;

public class MessageInteractPageChanged extends MessageInteract {
    public ElementWizard.PageBundle mPage;

    public MessageInteractPageChanged(ElementWizard.PageBundle page) {
        mPage = page;
    }
}
