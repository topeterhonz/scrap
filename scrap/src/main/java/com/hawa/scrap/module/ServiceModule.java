package com.hawa.scrap.module;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hawa.scrap.domain.ConfigurationsService;
import com.hawa.scrap.domain.PostsService;
import com.hawa.scrap.network.TumblrDeserializer;
import com.hawa.scrap.network.TumblrRequestInterceptor;
import com.hawa.scrap.operation.TumblrPostsOperation;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

@Module(
        injects = {
                PostsService.class,
                TumblrPostsOperation.class,
                ConfigurationsService.class,
        }
)
public class ServiceModule {

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
