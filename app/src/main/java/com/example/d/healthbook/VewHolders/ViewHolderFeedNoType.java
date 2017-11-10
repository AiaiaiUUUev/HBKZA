package com.example.d.healthbook.VewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.d.healthbook.R;

/**
 * Created by D on 26.06.2017.
 */

public class ViewHolderFeedNoType extends RecyclerView.ViewHolder {


    ImageView imageFeed;
    TextView feedNameSurname;
    TextView feedText;

    public ImageView getImageFeed() {
        return imageFeed;
    }

    public void setImageFeed(ImageView imageFeed) {
        this.imageFeed = imageFeed;
    }

    public TextView getFeedNameSurname() {
        return feedNameSurname;
    }

    public void setFeedNameSurname(TextView feedNameSurname) {
        this.feedNameSurname = feedNameSurname;
    }

    public TextView getFeedText() {
        return feedText;
    }

    public void setFeedText(TextView feedText) {
        this.feedText = feedText;
    }

    public TextView getFeed_created_time() {
        return feed_created_time;
    }

    public void setFeed_created_time(TextView feed_created_time) {
        this.feed_created_time = feed_created_time;
    }

    TextView feed_created_time;

    public ViewHolderFeedNoType(View v) {
        super(v);
        imageFeed = (ImageView) v.findViewById(R.id.imageFeed);
        feedNameSurname = (TextView) v.findViewById(R.id.feedNameSurname);
        feed_created_time = (TextView) v.findViewById(R.id.feed_created_time);
        feedText = (TextView) v.findViewById(R.id.feedText);
    }


}