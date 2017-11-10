package com.example.d.healthbook.Nurlans;

import android.app.Application;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.d.healthbook.Appi.App;
import com.example.d.healthbook.R;
import com.example.d.healthbook.UI.BlockLayout;
import com.example.d.healthbook.UI.IBlockLayout;
import com.example.d.healthbook.UI.Size;

public class TextBlock extends BlockLayout implements IBlockLayout
{

    public TextBlock(Context context)
    {
        super(context);
    }

    @Override
    public IBlockLayout __construct()
    {
        Context ctx = getContext();

        TextView label = new TextView(ctx);
        label.setTextColor(ContextCompat.getColor(ctx, R.color.black));
        RelativeLayout.LayoutParams lbl_lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lbl_lp.setMargins(0, 10, 0, 0);
        lbl_lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        label.setLayoutParams(lbl_lp);

        TextView value = new TextView(ctx);
        value.setTextColor(ContextCompat.getColor(ctx, R.color.colorDarkGray));
        value.setTextSize(24);
        RelativeLayout.LayoutParams val_lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        val_lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        value.setLayoutParams(val_lp);

        this.setBackground(ContextCompat.getDrawable(ctx, R.drawable.shadow_wrc_layout));
        this.addView(label);
        this.addView(value);

        return this;
    }

    @Override
    public void adjustToView(View view, Size constraints)
    {
        super.adjustToView(view, constraints);
    }

    public void setLabelText(String text)
    {
        TextView view = (TextView) getChildAt(0);
        view.setText(text);
    }

    public void setLabelSize(int size)
    {
        TextView view = (TextView) getChildAt(0);
        view.setTextSize(size);
    }

    public void setLabelColor(int color)
    {
        TextView view = (TextView) getChildAt(0);
        view.setTextColor(ContextCompat.getColor(App.getInstance().getCurrentContext(), color));
    }

    public void setValueText(String text)
    {
        TextView view = (TextView) getChildAt(1);
        view.setText(text);
    }

    public void setValueSize(int size)
    {
        TextView view = (TextView) getChildAt(1);
        view.setTextSize(size);
    }

    public void setValueColor(int color)
    {
        TextView view = (TextView) getChildAt(1);
        view.setTextColor(ContextCompat.getColor(App.getInstance().getCurrentContext(), color));
    }

}