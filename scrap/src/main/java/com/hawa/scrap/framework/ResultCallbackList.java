package com.hawa.scrap.framework;

import java.util.ArrayList;

public class ResultCallbackList<TResult, TResultCode> extends ArrayList<ResultCallback<TResult, TResultCode>> implements ResultCallback<TResult, TResultCode> {
    @Override
    public void onResult(TResult result, TResultCode resultCode) {
        for (ResultCallback resultCallback : this) {
            resultCallback.onResult(result, resultCode);
        }
        this.clear();
    }

    @Override
    public void onError(ErrorCode errorCode) {
        for (ResultCallback resultCallback : this) {
            resultCallback.onError(errorCode);
        }
        this.clear();
    }

    @Override
    public void onProcessing() {
        throw new RuntimeException("This shouldn't be called");
    }

}
