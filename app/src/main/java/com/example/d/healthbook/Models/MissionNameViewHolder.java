package com.example.d.healthbook.Models;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.d.healthbook.R;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

/**
 * Created by D on 14.07.2017.
 */

public class MissionNameViewHolder extends GroupViewHolder {
    private Animation  rotate_forward, rotate_backward;
    private TextView userProgressTVONE;
    private ImageView ic_arrow;

    public MissionNameViewHolder(View itemView) {
        super(itemView);
        userProgressTVONE = (TextView) itemView.findViewById(R.id.userProgressTVONE);
        ic_arrow= (ImageView) itemView.findViewById(R.id.arrow_look_down);
    }

    public void bind(ModelWhithTasks recipe) {
        userProgressTVONE.setText(recipe.getNameMission());
    }

    public void setTitle(ModelWhithTasks recipe) {
        userProgressTVONE.setText(recipe.getNameMission());
    }

    @Override
    public void expand() {
        animateExpand();
    }


    @Override
    public void collapse() {
        animateCollapse();
    }


    private void animateExpand() {
        rotate_forward= AnimationUtils.loadAnimation(itemView.getContext(),R.anim.rotate_forward_ic_arrow_look_down);
        ic_arrow.startAnimation(rotate_forward);


    }

    private void animateCollapse() {
        rotate_backward=AnimationUtils.loadAnimation(itemView.getContext(),R.anim.rotate_backward_ic_arrow_look_down);
        ic_arrow.startAnimation(rotate_backward);
    }

}
