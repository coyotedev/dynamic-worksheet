package net.coyotedev.androidworksheet.uiadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableRow;

import com.bumptech.glide.Glide;

import dynamicworksheet.element.ElementImageUrl;
import dynamicworksheet.element.IElement;

public class ElementImageUrlAdapter implements IElementAdapter {
    @Override
    public View build(IElement element, ViewGroup root, Context ctx) {
        ImageView ret = new ImageView(ctx);
        ret.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
        ElementImageUrl image = (ElementImageUrl) element;
        ElementImageUrl.Size size = image.getSize();
        ret.setMinimumWidth(size.getWidth());
        ret.setMinimumHeight(size.getHeight());
        Glide.with(ctx).load(image.getValue().getValue()).into(ret);
        return ret;
    }
}
