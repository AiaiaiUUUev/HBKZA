package com.example.d.healthbook.Models;

import android.graphics.Color;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.d.healthbook.R;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

/**
 * Created by D on 14.07.2017.
 */

public class TasksViewholder extends ChildViewHolder {

    TextView infoProgressTV;
    CheckBox progressChBx;
    ImageView checkboxIMG;


    public TasksViewholder(View itemView) {
        super(itemView);
        infoProgressTV = (TextView) itemView.findViewById(R.id.infoProgressTV);
        progressChBx = (CheckBox) itemView.findViewById(R.id.progressChBx);
        checkboxIMG= (ImageView) itemView.findViewById(R.id.checkboxIMG);

    }

    public void bind(Task task) {
        infoProgressTV.setText(task.getName());
        if (task.getComplete()) {
            checkboxIMG.setVisibility(View.VISIBLE);
            infoProgressTV.setTextColor(Color.parseColor("#f8999696"));
        } else {
            checkboxIMG.setVisibility(View.INVISIBLE);
            infoProgressTV.setTextColor(Color.parseColor("#ff000000"));

        }
    }


}