package com.hawa.scrap.framework;


public interface ResultCallback<TResult, TResultCode> {
    void onResult(TResult result, TResultCode resultCode);
    void onError(ErrorCode errorCode);
    void onProcessing();
}
