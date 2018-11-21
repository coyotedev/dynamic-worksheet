package net.coyotedev.androidworksheet.uiadapter;

import android.view.View;

import dynamicworksheet.element.IElement;

public class CoreView {

    private final IElement mCoreView;
    private final View mView;

    public CoreView(IElement core, View view) {
        mCoreView = core;
        mView = view;
    }

    public IElement getCoreView() {
        return mCoreView;
    }

    public View getView() {
        return mView;
    }
}
