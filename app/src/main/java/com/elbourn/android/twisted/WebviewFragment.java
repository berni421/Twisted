package com.elbourn.android.twisted;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.webkit.WebViewAssetLoader;
import androidx.webkit.WebViewClientCompat;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class WebviewFragment extends Fragment {

    static String APP = BuildConfig.APPLICATION_ID;
    static String TAG = "WebviewFragment";
    View view = null;
    String assetUrl = "https://appassets.androidplatform.net/assets/website/index.html";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_webview, container, false);
        startWebView(view);
        return view;
    }

    void startWebView(View view) {
        Context context = getContext();
        String msg = "Loading twisted .. please wait.";
        Log.i(TAG, "msg: " + msg);
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
        WebView myWebView = (WebView) view.findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setAppCacheEnabled(true);
        final WebViewAssetLoader assetLoader = new WebViewAssetLoader.Builder()
                .addPathHandler("/assets/", new WebViewAssetLoader.AssetsPathHandler(context))
                .build();
        myWebView.setWebViewClient(new WebViewClientCompat() {
            @Override
            @RequiresApi(21)
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                return assetLoader.shouldInterceptRequest(request.getUrl());
            }

            @Override
            @SuppressWarnings("deprecation") // for API < 21
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                return assetLoader.shouldInterceptRequest(Uri.parse(url));
            }
        });
        myWebView.loadUrl(assetUrl);
        Animation animFadeIn = AnimationUtils.loadAnimation(context,R.anim.fade_in);
        view.startAnimation(animFadeIn);
    }
}