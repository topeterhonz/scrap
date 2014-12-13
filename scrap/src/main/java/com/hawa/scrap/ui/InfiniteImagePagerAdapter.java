package com.hawa.scrap.ui;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hawa.scrap.R;
import com.hawa.scrap.domain.PostsService;
import com.hawa.scrap.framework.ErrorCode;
import com.hawa.scrap.framework.ResultCallback;
import com.hawa.scrap.model.Post;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;

public class InfiniteImagePagerAdapter extends PagerAdapter {

    private Context mContext;
    private PostsService mPostsService;
    private List<Post> mPosts;
    private int mTotalPages;

    public InfiniteImagePagerAdapter(Context context, PostsService postsService) {
        mContext = context;
        mPostsService = postsService;
        mPosts = mPostsService.getPostsCached();
        mTotalPages = mPostsService.getTotalPosts();
    }

    private void showLoading(View view) {
        view.findViewById(R.id.page_content_progress).setVisibility(View.VISIBLE);
    }

    private void hideLoading(View view) {
        view.findViewById(R.id.page_content_progress).setVisibility(View.GONE);
    }

    @Override
    public int getCount() {
        return mTotalPages == 0 ? Integer.MAX_VALUE : mTotalPages;
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
        final ImageViewTouch imageView = (ImageViewTouch) view.findViewById(R.id.page_content_image);

        if (mPosts == null || mPosts.size() <= position) {
            mPostsService.getPostsForNextPage(new ResultCallback<List<Post>, PostsService.ResultCode>() {
                @Override
                public void onResult(List<Post> posts, PostsService.ResultCode resultCode) {
                    mPosts = posts;
                    loadImageFromUrl(position, view, imageView);
                }

                @Override
                public void onError(ErrorCode errorCode) {
                    hideLoading(view);
                }

                @Override
                public void onProcessing() {
                    showLoading(view);
                }
            });
        } else {
            loadImageFromUrl(position, view, imageView);
        }

        container.addView(view);
        return view;
    }


    private void loadImageFromUrl(int index, final View view, ImageView imageView) {
        showLoading(view);

        String imageUrl = mPosts.get(index).getPhotos().get(0).getOriginal_size().getUrl();

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
