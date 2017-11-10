package com.example.d.healthbook.Adapters;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.d.healthbook.R;

/**
 * Created by D on 09.06.2017.
 */

public class CustomAdapterSpinner extends ArrayAdapter {

    String hint;

    public CustomAdapterSpinner(Activity act, int layoutResId, String[] dataArray, String hint) {
        super(act, layoutResId, dataArray);
        this.hint = hint;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = null;
        if (position >= getCount()) { //logic to display hint
            v = super.getView(0, convertView, parent);
            ((TextView) v.findViewById(R.id.text_Spinner_my)).setText("Выберите день недели");
            ((TextView) v.findViewById(R.id.text_Spinner_my)).setTextColor(Color.parseColor("#acacac"));
            ((TextView) v.findViewById(R.id.text_Spinner_my)).setHint(hint); //"Hint to be displayed"
            //    }
        } else {
            v = super.getView(position, convertView, parent);
        }
        return v;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

}