package com.fsl.creditorapp.core.uiadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableRow;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import core.dynamicworksheet.element.ElementImageUrl;
import core.dynamicworksheet.element.IElement;
import core.dynamicworksheet.message.interact.MessageInteract;
import core.dynamicworksheet.message.interact.MessageInteractTextChanged;

public class ElementImageUrlAdapter implements IElementAdapter {
    @Override
    public View build(IElement element, ViewGroup root, Context ctx) {
        ImageView ret = new ImageView(ctx);
        ElementImageUrl image = (ElementImageUrl) element;
        final ElementImageUrl.Size size = image.getSize();
        ret.setLayoutParams(new TableRow.LayoutParams(size.getWidth(), size.getHeight()));
        ret.setScaleType(ImageView.ScaleType.FIT_XY);

        element.setAdapter(new AdapterBase(ret) {
            @Override
            public void onInteract(MessageInteract message) {
                super.onInteract(message);
                if (message.getClass().isAssignableFrom(MessageInteractTextChanged.class)) {
                    MessageInteractTextChanged msg = (MessageInteractTextChanged) message;
                    Glide.with(ctx).load(msg.getText()).apply(new RequestOptions().override(size.getWidth(), size.getHeight())).into(ret);
                }
            }
        });
        return ret;
    }
}
