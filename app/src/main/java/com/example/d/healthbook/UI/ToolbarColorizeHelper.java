package com.example.d.healthbook.UI;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ToolbarColorizeHelper
{

    public static void colorizeToolbar(Toolbar toolbar, @ColorInt int background, @ColorInt int foreground) {

        if (toolbar == null) return;

        final PorterDuffColorFilter srcColorFilter = new PorterDuffColorFilter(foreground, PorterDuff.Mode.SRC_IN);
        final PorterDuffColorFilter multColorFilter = new PorterDuffColorFilter(foreground, PorterDuff.Mode.MULTIPLY);

        for (int i = 0; i < toolbar.getChildCount(); i++) {
            final View view = toolbar.getChildAt(i);

            //Back button or drawer open button
            if (view instanceof ImageButton)
            {
                ((ImageButton) view).getDrawable().setColorFilter(srcColorFilter);
            }

            if (view instanceof ImageView)
            {
                ((ImageView) view).setColorFilter(srcColorFilter);
            }

            if( view instanceof TextView )
            {
                ((TextView) view).setTextColor(foreground);
            }

            if (view instanceof ActionMenuView) {
                for (int j = 0; j < ((ActionMenuView) view).getChildCount(); j++) {

                    final View innerView = ((ActionMenuView)view).getChildAt(j);

                    //Any ActionMenuViews - icons that are not back button, text or overflow menu
                    if (innerView instanceof ActionMenuItemView)
                    {
                        final Drawable[] drawables = ((ActionMenuItemView)innerView).getCompoundDrawables();
                        for (int k = 0; k < drawables.length; k++) {

                            final Drawable drawable = drawables[k];
                            if (drawable != null) {
                                final int drawableIndex = k;
                                //Set the color filter in separate thread
                                //by adding it to the message queue - won't work otherwise
                                innerView.post(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        ((ActionMenuItemView) innerView).getCompoundDrawables()[drawableIndex].setColorFilter(srcColorFilter);
                                    }
                                });
                            }
                        }
                    }
                }
            }
        }

        //Overflow icon
        Drawable overflowIcon = toolbar.getOverflowIcon();
        if (overflowIcon != null) {
            overflowIcon.setColorFilter(srcColorFilter);
            toolbar.setOverflowIcon(overflowIcon);
        }

        toolbar.setBackgroundColor(background);

        // toolbar native title text view
        toolbar.setTitleTextColor(foreground);
        toolbar.setSubtitleTextColor(foreground);
    }


}