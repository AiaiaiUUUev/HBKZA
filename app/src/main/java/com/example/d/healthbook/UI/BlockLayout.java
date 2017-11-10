package com.example.d.healthbook.UI;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.d.healthbook.Appi.App;

import org.apmem.tools.layouts.FlowLayout;

public abstract class BlockLayout extends RelativeLayout implements IBlockLayout
{

    Rect constraints;

    public BlockLayout(Context context)
    {
        super(context);
    }

    public void setMargin( int left, int top, int right, int bottom )
    {
        if( ((FlowLayout.LayoutParams) this.getLayoutParams()) == null )
        {
            this.constraints = new Rect(left, top, right, bottom);
            return;
        }

        FlowLayout.LayoutParams block_lp = (FlowLayout.LayoutParams) this.getLayoutParams();
        block_lp.width = block_lp.width - left - right;
        block_lp.height = block_lp.height - top - bottom;
        block_lp.setMargins(left, top, right, bottom);
        this.setLayoutParams(block_lp);
    }

    @Override
    public void adjustToView(View view, Size constraints)
    {
        Context ctx = App.getInstance().getCurrentContext();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) ctx).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        MarginLayoutParams params = (MarginLayoutParams) view.getLayoutParams();
        int width = displaymetrics.widthPixels - params.leftMargin - params.rightMargin;
        int _w = (constraints.getWidth() != 0) ? (int) Math.floor(width * constraints.getWidth()) : ViewGroup.LayoutParams.WRAP_CONTENT;
        int _h = (constraints.getHeight() != 0) ? (int) Math.floor(_w * constraints.getHeight()) : ViewGroup.LayoutParams.WRAP_CONTENT;

        this.setLayoutParams(new FlowLayout.LayoutParams(_w, _h));

        if( this.constraints != null )
        {
            this.setMargin((int) this.constraints.getX(),(int) this.constraints.getY(),(int) this.constraints.getWidth(),(int) this.constraints.getHeight());
        }

        ((ViewGroup) view).addView(this);
    }

}