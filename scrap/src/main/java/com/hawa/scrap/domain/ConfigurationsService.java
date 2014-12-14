package com.hawa.scrap.domain;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ConfigurationsService {

    @Inject
    public ConfigurationsService() {

    }

    public int getPostPageSize() {
        return 100;
    }

    public String getSiteId() {
        return "androidniceties.tumblr.com";
    }

    public String getTumblrApiKey() {
        return "OHHiScy8tKLA4WOoO8VUWI5IZ8Lm9DBMPfU3VTjy2fDQvd5ELS";
    }

    public String getTumblrApiSecret() {
        return "F85MYR9apBgYnCBrrAGgG9xIk0UG3OTKcOefgvoO63tXn23Y4y";
    }

    public String getTumblrCallbackUrl() {
        return "scrap://scrap/ok";
    }
}
