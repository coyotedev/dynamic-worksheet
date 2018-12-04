package com.fsl.androidworksheet.core.uiadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import core.dynamicworksheet.element.IElement;
import core.dynamicworksheet.message.interact.MessageInteract;
import core.dynamicworksheet.message.interact.MessageInteractHiddenChanged;

public interface IElementAdapter {
    View build(IElement element, ViewGroup root, Context ctx);

    class AdapterBase implements IElement.Adapter {
        private final View mView;

        protected AdapterBase(View view) {
            mView = view;
        }

        @Override
        public void onInteract(MessageInteract message) {
            if (message.getClass().isAssignableFrom(MessageInteractHiddenChanged.class)) {
                MessageInteractHiddenChanged msg = (MessageInteractHiddenChanged) message;
                int visibility = msg.isHidden() ? View.GONE : View.VISIBLE;
                mView.setVisibility(visibility);
            }
        }
    }
}
