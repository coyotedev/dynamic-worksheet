package dynamicworksheet.message.interact;

import dynamicworksheet.element.ElementWizard;

/**
 * Текущая страница изменилась (для {@link ElementWizard})
 * Направление сообщения - Core UI -> Real UI
 */
public class MessageInteractPageChanged extends MessageInteract {
    public ElementWizard.PageBundle mPage;

    public MessageInteractPageChanged(ElementWizard.PageBundle page) {
        mPage = page;
    }
}
