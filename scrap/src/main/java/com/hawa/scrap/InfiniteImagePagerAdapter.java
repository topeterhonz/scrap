package com.hawa.scrap;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class InfiniteImagePagerAdapter extends PagerAdapter {

    private Context mContext;
    private TumblrImageProvider mImageProvider;

    public InfiniteImagePagerAdapter(Context context, TumblrImageProvider imageProvider) {
        mContext = context;
        mImageProvider = imageProvider;
    }

    private void showLoading(View view) {
        view.findViewById(R.id.page_content_progress).setVisibility(View.VISIBLE);
    }

    private void hideLoading(View view) {
        view.findViewById(R.id.page_content_progress).setVisibility(View.GONE);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object == view;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.page_content, container, false);
        TextView textView = (TextView) view.findViewById(R.id.page_content_text);
        textView.setText(Integer.toString(position));
        final ImageView imageView = (ImageView) view.findViewById(R.id.page_content_image);

        showLoading(view);

        if (mImageProvider.isAvailable(position)) {
            loadImageFromUrl(position, view, imageView);
        } else {
            mImageProvider.loadNext(new TumblrImageProvider.LoadCompleteListener() {
                @Override
                public void onSuccess() {
                    loadImageFromUrl(position, view, imageView);
                }

                @Override
                public void onFailure() {
                    hideLoading(view);
                }
            });
        }


        container.addView(view);
        return view;
    }


    private void loadImageFromUrl(int index, final View view, ImageView imageView) {
        String imageUrl = mImageProvider.provideImageUrl(index);

        Picasso.with(mContext).load(imageUrl).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                hideLoading(view);
            }

            @Override
            public void onError() {
                hideLoading(view);
            }
        });
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
