package com.hawa.scrap.module;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.hawa.scrap.domain.ConfigurationsService;
import com.hawa.scrap.domain.PostsService;
import com.hawa.scrap.domain.TumblrAuthoriseService;
import com.hawa.scrap.network.TumblrDeserializer;
import com.hawa.scrap.network.TumblrRequestInterceptor;
import com.hawa.scrap.operation.TumblrPostsOperation;

import javax.inject.Singleton;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(PostsService.class).in(Singleton.class);
        bind(ConfigurationsService.class).in(Singleton.class);
    }

    @Provides
    @Singleton
    public TumblrPostsOperation providesTumblrOperation(ConfigurationsService configurationsService) {
        Gson gson = new GsonBuilder()
                .registerTypeHierarchyAdapter(Object.class, new TumblrDeserializer())
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://api.tumblr.com")
                .setConverter(new GsonConverter(gson))
                .setRequestInterceptor(new TumblrRequestInterceptor(configurationsService.getTumblrApiKey()))
                .setLog(new RestAdapter.Log() {
                    @Override
                    public void log(String message) {
                        Log.d("RestAdapter", message);
                    }
                })
                .build();

        return restAdapter.create(TumblrPostsOperation.class);
    }
}
