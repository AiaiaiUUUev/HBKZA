package com.example.d.healthbook.Nurlans;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.d.healthbook.Appi.App;
import com.example.d.healthbook.R;
import com.example.d.healthbook.UI.BlockLayout;
import com.example.d.healthbook.UI.IBlockLayout;
import com.example.d.healthbook.UI.Size;


import java.util.concurrent.atomic.AtomicLong;

// Image+Content Block

public class ICBlock extends BlockLayout implements IBlockLayout
{

    static final AtomicLong NEXT_ID = new AtomicLong(2000);
    final long id = NEXT_ID.getAndIncrement();

    public ICBlock(Context context)
    {
        super(context);
    }

    @Override
    public IBlockLayout __construct()
    {
        Context ctx = getContext();

        ImageView image = new ImageView(ctx);
        image.setId((int) id);
        RelativeLayout.LayoutParams img_lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        img_lp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        image.setLayoutParams(img_lp);

        TextView content = new TextView(ctx);
        content.setTextColor(ContextCompat.getColor(ctx, R.color.black));
        RelativeLayout.LayoutParams cnt_lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        cnt_lp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        cnt_lp.addRule(RelativeLayout.RIGHT_OF, image.getId());
        cnt_lp.addRule(RelativeLayout.END_OF, image.getId());
        cnt_lp.leftMargin = 20;
        content.setLayoutParams(cnt_lp);

        this.setBackground(ContextCompat.getDrawable(ctx, R.drawable.shadow_wrc_layout));
        this.addView(image);
        this.addView(content);

        return this;
    }

    @Override
    public void adjustToView(View view, Size constraints)
    {
        super.adjustToView(view, constraints);
        this.setPadding(30, 40, 30, 40);
    }

    public void setImageResourse(int drawable)
    {
        ImageView view = (ImageView) getChildAt(0);
        view.setImageResource(drawable);
    }

    public void setImageDrawable(Drawable drawable)
    {
        ImageView view = (ImageView) getChildAt(0);
        view.setImageDrawable(drawable);
    }

    public void setContentText(String text)
    {
        TextView view = (TextView) getChildAt(1);
        view.setText(text);
    }

    public void setContentSize(int size)
    {
        TextView view = (TextView) getChildAt(1);
        view.setTextSize(size);
    }

    public void setContentColor(int color)
    {
        TextView view = (TextView) getChildAt(1);
        view.setTextColor(ContextCompat.getColor(App.getInstance().getCurrentContext(), color));
    }

}