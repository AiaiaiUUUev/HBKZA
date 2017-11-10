package com.example.d.healthbook.VewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.d.healthbook.R;

/**
 * Created by D on 26.06.2017.
 */

public class ViewHolderFeedWhithType extends RecyclerView.ViewHolder {




public ImageView getImageFeedWhitType() {
        return imageFeedWhitType;
        }

public void setImageFeedWhitType(ImageView imageFeedWhitType) {
        this.imageFeedWhitType = imageFeedWhitType;
        }

public TextView getTitleNewsFeed() {
        return titleNewsFeed;
        }

public void setTitleNewsFeed(TextView titleNewsFeed) {
        this.titleNewsFeed = titleNewsFeed;
        }

public TextView getFeedCategoryNewsType() {
        return feedCategoryNewsType;
        }

public void setFeedCategoryNewsType(TextView feedCategoryNewsType) {
        this.feedCategoryNewsType = feedCategoryNewsType;
        }

public TextView getFeed_preview() {
        return feed_preview;
        }

public void setFeed_preview(TextView feed_preview) {
        this.feed_preview = feed_preview;
        }

        ImageView imageFeedWhitType;
        TextView titleNewsFeed;
        TextView feedCategoryNewsType;
        TextView feed_preview;

public ViewHolderFeedWhithType(View v) {
        super(v);
        imageFeedWhitType = (ImageView) v.findViewById(R.id.imageFeedWhitType);
        titleNewsFeed = (TextView) v.findViewById(R.id.titleNewsFeed);
        feed_preview = (TextView) v.findViewById(R.id.feed_preview);
        feedCategoryNewsType = (TextView) v.findViewById(R.id.feedCategoryNewsType);
        }


        }