package core.dynamicworksheet.message.interact;

import core.dynamicworksheet.element.ElementWizard;

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
