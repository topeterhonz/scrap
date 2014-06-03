package com.hawa.scrap.network;

import retrofit.RequestInterceptor;

/**
 * Created by peterho on 18/05/14.
 */
public class TumblrRequestInterceptor implements RequestInterceptor {

    private String apiKey;
    public TumblrRequestInterceptor(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public void intercept(RequestFacade request) {
        request.addQueryParam("api_key", this.apiKey);
    }
}
