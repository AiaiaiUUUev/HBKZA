package com.example.d.healthbook.VewHolders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.d.healthbook.R;

/**
 * Created by D on 07.07.2017.
 */

public class ViewHolderVisitData extends RecyclerView.ViewHolder {


    public TextView getTimeVisit() {
        return timeVisit;
    }

    public void setTimeVisit(TextView timeVisit) {
        this.timeVisit = timeVisit;
    }

    public TextView getTimeWork() {
        return timeWork;
    }

    public void setTimeWork(TextView timeWork) {
        this.timeWork = timeWork;
    }

    public CardView getCardView() {
        return cardView;
    }

    public void setCardView(CardView cardView) {
        this.cardView = cardView;
    }

    public LinearLayout getLinearLayout() {
        return linearLayout;
    }

    public void setLinearLayout(LinearLayout linearLayout) {
        this.linearLayout = linearLayout;
    }

    TextView timeVisit;
    TextView timeWork;
    CardView cardView;
    LinearLayout linearLayout;

    public ViewHolderVisitData(final View v) {
        super(v);
        timeVisit = (TextView) v.findViewById(R.id.timeVisit);
        timeWork = (TextView) v.findViewById(R.id.timeWork);
        cardView = (CardView) v.findViewById(R.id.cardViewVisit);
        linearLayout = (LinearLayout) v.findViewById(R.id.chooseVisitLL);

    }
}