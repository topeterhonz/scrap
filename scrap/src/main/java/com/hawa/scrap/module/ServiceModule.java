package com.hawa.scrap.module;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.hawa.scrap.InfiniteImagePagerAdapter;
import com.hawa.scrap.PostCache;
import com.hawa.scrap.TumblrImageProvider;
import com.hawa.scrap.network.TumblrDeserializer;
import com.hawa.scrap.network.TumblrRequestInterceptor;
import com.hawa.scrap.network.TumblrService;

import javax.inject.Singleton;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(PostCache.class).in(Singleton.class);
        bind(TumblrImageProvider.class).in(Singleton.class);
    }

    @Provides @Singleton
    public TumblrService providesTumblrService() {
        Gson gson = new GsonBuilder()
                .registerTypeHierarchyAdapter(Object.class, new TumblrDeserializer())
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://api.tumblr.com")
                .setConverter(new GsonConverter(gson))
                .setRequestInterceptor(new TumblrRequestInterceptor("fuiKNFp9vQFvjLNvx4sUwti4Yb5yGutBN4Xh10LXZhhRKjWlV4"))
                .setLog(new RestAdapter.Log() {
                    @Override
                    public void log(String message) {
                        Log.d("RestAdapter", message);
                    }
                })
                .build();

        return restAdapter.create(TumblrService.class);
    }

    @Provides @Singleton
    public InfiniteImagePagerAdapter providesImagePagerAdapter() {
        return null;
    }
}
