package com.example.d.healthbook.Nurlans;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.d.healthbook.Activities.MainPageActivity;
import com.example.d.healthbook.Appi.App;
import com.example.d.healthbook.R;
import com.example.d.healthbook.UI.BlockLayout;
import com.example.d.healthbook.UI.IBlockLayout;
import com.example.d.healthbook.UI.Size;
import com.liulishuo.magicprogresswidget.MagicProgressCircle;


public class CompositeBlock extends BlockLayout implements IBlockLayout
{

    public CompositeBlock(Context context)
    {
        super(context);
    }

    @Override
    public IBlockLayout __construct()
    {
        this.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.shadow_wrc_layout));
        return this;
    }

    @Override
    public void adjustToView(View view, Size constraints)
    {
        super.adjustToView(view, constraints);
    }

    // LinearLayout.HORIZONTAL | LinearLayout.VERTICAL
    public void addGroup( String name, int width, int height, int orientation )
    {
        addGroup(null, name, width, height, orientation);
    }

    public void addGroup( String name, int width, int height )
    {
        addGroup(null, name, width, height);
    }

    public void addGroup( String group, String name, int width, int height )
    {
        RelativeLayout layout = new RelativeLayout(this.getContext());
        layout.setLayoutParams(new LinearLayout.LayoutParams(width, height));
        layout.setId(name.hashCode());
        layout.setTag(name);
        if( group != null ) ((ViewGroup) getGroup(group)).addView(layout); else addView(layout);
    }

    public void addGroup( String group, String name, int width, int height, int orientation )
    {
        LinearLayout layout = new LinearLayout(this.getContext());
        layout.setOrientation(orientation);
        layout.setLayoutParams(new LinearLayout.LayoutParams(width, height));
        layout.setId(name.hashCode());
        layout.setTag(name);
        if( group != null ) ((ViewGroup) getGroup(group)).addView(layout); else addView(layout);
    }

    public View getGroup(String name)
    {
        return (name == null ) ? this : findViewWithTag(name);
    }

    public void setAlignment(String v0, int alignment)
    {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(alignment);
        getGroup(v0).setLayoutParams(params);
    }

    public void setAlignment(String v0, String v1, int alignment)
    {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(alignment, getGroup(v1).getId());
        getGroup(v0).setLayoutParams(params);
    }

    public TextView addTextField(String group, String text, int text_color, float text_size, Typeface font_weight)
    {
        TextView txtView = new TextView(this.getContext());
        txtView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        txtView.setTextColor(ContextCompat.getColor(getContext(), text_color));
        txtView.setTextSize(text_size);
        txtView.setTypeface(font_weight);
        txtView.setText(text);
        if( group != null ) ((ViewGroup) getGroup(group)).addView(txtView); else addView(txtView);

        return txtView;
    }

    public TextView addTextField(String text, int text_color, float text_size, Typeface font_weight)
    {
        return addTextField(null, text, text_color, text_size, font_weight);
    }

    public ImageView addImage(String group, int drawable)
    {
        ImageView imgView = new ImageView(this.getContext());
        imgView.setImageDrawable(ContextCompat.getDrawable(this.getContext(), drawable));
        if( group != null ) ((ViewGroup) getGroup(group)).addView(imgView); else addView(imgView);

        return imgView;
    }

    public ImageView addImage(int drawable)
    {
        return addImage(null, drawable);
    }

    public CheckBox addCheckbox(String group)
    {
        CheckBox checkBox = new CheckBox(this.getContext());
        checkBox.setButtonDrawable(R.drawable.circular_checkbox);
        if( group != null ) ((ViewGroup) getGroup(group)).addView(checkBox); else addView(checkBox);

        return checkBox;
    }

    public CheckBox addCheckbox()
    {
        return addCheckbox(null);
    }

    public LinearLayout addMultilineCheckbox(String group, String text)
    {
        LinearLayout layout = new LinearLayout(getContext());
        layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        layout.setPadding(5,5,5,5);
        layout.setOrientation(LinearLayout.VERTICAL);

        CheckBox checkBox = new CheckBox(this.getContext());
        checkBox.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        checkBox.setButtonDrawable(R.drawable.circular_checkbox);
        checkBox.setGravity(Gravity.CENTER_HORIZONTAL);

        TextView txtView = new TextView(this.getContext());
        txtView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        txtView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorBlack));
        txtView.setTextSize(14);
        txtView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        txtView.setText(text);
        txtView.setGravity(Gravity.CENTER_HORIZONTAL);

        layout.addView(checkBox);
        layout.addView(txtView);

        if( group != null ) ((ViewGroup) getGroup(group)).addView(layout); else addView(layout);

        return layout;
    }

    public LinearLayout addMultilineCheckbox(String text)
    {
        return addMultilineCheckbox(null, text);
    }

    public RelativeLayout addCircularProgressBar(String group, String tag_id, int width, int height, int stroke_width, int stroke_color)
    {
        RelativeLayout layout = new RelativeLayout(this.getContext());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
        layout.setLayoutParams(params);

        MagicProgressCircle progressCircle = new MagicProgressCircle(this.getContext());
        progressCircle.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        progressCircle.setStrokeWidth(stroke_width);
        progressCircle.setStartColor(ContextCompat.getColor(getContext(), stroke_color));
        progressCircle.setEndColor(ContextCompat.getColor(getContext(), stroke_color));
        progressCircle.setSmoothPercent((float) 0.27);

        TextView txtView = new TextView(this.getContext());
        RelativeLayout.LayoutParams txt_lt = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        txt_lt.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        txtView.setLayoutParams(txt_lt);
        txtView.setTextColor(ContextCompat.getColor(getContext(), stroke_color));
        txtView.setTextSize((int) (height * 0.15));
        txtView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        txtView.setText("27%");

        layout.setTag(tag_id);
        layout.addView(progressCircle);
        layout.addView(txtView);

        if( group != null ) ((ViewGroup) getGroup(group)).addView(layout); else addView(layout);

        return layout;
    }

    public RelativeLayout addCircularProgressBar(String tag_id, int width, int height, int stroke_width, int stroke_color)
    {
        return addCircularProgressBar(null, tag_id, width, height, stroke_width, stroke_color);
    }

}