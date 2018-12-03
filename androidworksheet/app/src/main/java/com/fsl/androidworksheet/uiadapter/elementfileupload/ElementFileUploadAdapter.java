package com.fsl.androidworksheet.uiadapter.elementfileupload;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fsl.androidworksheet.BuildConfig;
import com.fsl.androidworksheet.uiadapter.IElementAdapter;

import java.io.File;
import java.util.Locale;

import dynamicworksheet.element.ElementFileUpload;
import dynamicworksheet.element.IElement;
import dynamicworksheet.message.interact.MessageInteract;
import dynamicworksheet.message.interact.MessageInteractFileChanged;
import dynamicworksheet.validation.ValidationUpload;

import static android.content.Context.MODE_PRIVATE;

public class ElementFileUploadAdapter implements IElementAdapter {
    /*package*/ static final String SHARED_PREFS_KEY = BuildConfig.APPLICATION_ID + ".ElementFileUploadAdapter.SHARED_PREFS";
    /*package*/ static final String BUNDLE_KEY = "URI_BUNDLE_KEY";
    /*package*/ String SHARED_PREFS_URI_KEY = BuildConfig.APPLICATION_ID + ".ElementFileUploadAdapter.SHARED_PREFS_FILE_URI" + this.hashCode();

    private static class BehaviourRelativeLayout extends RelativeLayout {

        static abstract class OnDetachListener {
            abstract void onDetach();
        }

        private OnDetachListener mOnDetachListener;

        BehaviourRelativeLayout(Context context) {
            super(context);
        }

        void setOnDetachListener(OnDetachListener listener) {
            mOnDetachListener = listener;
        }

        @Override
        protected void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            if (mOnDetachListener != null) {
                mOnDetachListener.onDetach();
            }
        }
    }

    @Override
    public View build(final IElement element, ViewGroup root, final Context ctx) {
        final BehaviourRelativeLayout ret = new BehaviourRelativeLayout(ctx);
        TextView label = new TextView(ctx);
        final ImageView image = new ImageView(ctx);
        ElementFileUpload file = (ElementFileUpload) element;

        label.setText(file.getPlaceholder());
        image.setImageDrawable(ContextCompat.getDrawable(ctx, android.R.drawable.ic_menu_upload));

        // sharedPrefs callback
        {
            final SharedPreferences prefs = ctx.getSharedPreferences(ElementFileUploadAdapter.SHARED_PREFS_KEY, MODE_PRIVATE);
            final SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                    if (SHARED_PREFS_URI_KEY.equals(key)) {
                        String value = sharedPreferences.getString(key, null);
                        Uri contentUri = Uri.parse(value);
                        image.setImageURI(contentUri);
                        value = getRealPathFromURI(ctx, contentUri);
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inJustDecodeBounds = true;
                        BitmapFactory.decodeFile(value, options);

                        ValidationUpload.FileParams params = new ValidationUpload.FileParams
                                (
                                        value,
                                        humanReadableFileSize(new File(value).length()),
                                        options.outMimeType,
                                        options.outWidth,
                                        options.outHeight
                                );
                        element.onInteract(new MessageInteractFileChanged(params));
                    }
                }
            };
            prefs.registerOnSharedPreferenceChangeListener(listener);
            ret.setOnDetachListener(new BehaviourRelativeLayout.OnDetachListener() {
                @Override
                void onDetach() {
                    prefs.edit().clear().apply();
                    prefs.unregisterOnSharedPreferenceChangeListener(listener);
                }
            });
        }

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ctx, HelperActivity.class);
                i.putExtra(BUNDLE_KEY, SHARED_PREFS_URI_KEY);
                ctx.startActivity(i);
            }
        });

        element.setAdapter(new AdapterBase(ret) {
            @Override
            public void onInteract(MessageInteract message) {
                super.onInteract(message);
                if (message.getClass().isAssignableFrom(MessageInteractFileChanged.class)) {
                    MessageInteractFileChanged msg = (MessageInteractFileChanged) message;
                    image.setImageURI(Uri.fromFile(new File(((MessageInteractFileChanged) message).getParams().mPath)));
                }
            }
        });

        ret.addView(label);
        ret.addView(image);
        return ret;
    }

    private String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private static String humanReadableFileSize(long bytes) {
        final int UNIT = 1000;
        if (bytes < UNIT) {
            return bytes + " B";
        }
        int exp = (int) (Math.log(bytes) / Math.log(UNIT));
        String pre = "kMGTPE".charAt(exp-1) + "";
        return String.format(Locale.getDefault(), "%d%sB", (int) (bytes / Math.pow(UNIT, exp)), pre);
    }
}
