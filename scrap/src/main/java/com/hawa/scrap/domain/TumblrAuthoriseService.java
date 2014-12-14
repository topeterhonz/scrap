package com.hawa.scrap.domain;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.hawa.scrap.dependencyinjection.ForActivity;
import com.hawa.scrap.dependencyinjection.ForApplication;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TumblrApi;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import javax.inject.Inject;

public class TumblrAuthoriseService {
    private OAuthService mOAuthService;
    private ConfigurationsService mConfigurationsService;
    private Token mRequestToken;
    private Context mContext;
    private Token mAccessToken;

    @Inject
    public TumblrAuthoriseService(ConfigurationsService configurationsService,
                                  @ForActivity Context context) {
        mConfigurationsService = configurationsService;
        mContext = context;
        mOAuthService = new ServiceBuilder()
                .provider(TumblrApi.class)
                .apiKey(mConfigurationsService.getTumblrApiKey())
                .apiSecret(mConfigurationsService.getTumblrApiSecret())
                .callback(mConfigurationsService.getTumblrCallbackUrl())
                .build();
    }

    public void LaunchTumblrAuthorize() {
        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... voids) {
                mRequestToken = mOAuthService.getRequestToken();
                return mOAuthService.getAuthorizationUrl(mRequestToken);
            }

            @Override
            protected void onPostExecute(String result) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(result));
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

                mContext.startActivity(intent);
            }
        }.execute();
    }

    public void VerifyTumblrAuthorise(String oAuthVerifier) {
        new AsyncTask<String, Void, Void>() {

            @Override
            protected Void doInBackground(String... strings) {
                Verifier verifier = new Verifier(strings[0]);
                mAccessToken = mOAuthService.getAccessToken(mRequestToken, verifier);
                return null;
            }

            @Override
            protected void onPostExecute(Void dummy) {

            }
        }.execute(oAuthVerifier);
    }
}
