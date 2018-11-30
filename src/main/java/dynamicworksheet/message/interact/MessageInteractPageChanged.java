package dynamicworksheet.message.interact;

import dynamicworksheet.element.ElementWizard;

/**
 * Текущая страница изменилась (для {@link ElementWizard})
 * Направление сообщения - Core UI -> Real UI
 */
public class MessageInteractPageChanged extends MessageInteract {
    private ElementWizard.PageBundle mPage;

    public MessageInteractPageChanged(ElementWizard.PageBundle page) {
        mPage = page;
    }

    public ElementWizard.PageBundle getPageBundle() {
        return mPage;
    }
}
