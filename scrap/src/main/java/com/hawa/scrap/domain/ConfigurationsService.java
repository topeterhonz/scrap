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
        return "fuiKNFp9vQFvjLNvx4sUwti4Yb5yGutBN4Xh10LXZhhRKjWlV4";
    }
}
