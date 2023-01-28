package com.elbourn.android.twisted;

import android.app.appsearch.GetByDocumentIdRequest;
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
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "start onViewCreated");
        startWebView(view);

        Log.i(TAG, "end onViewCreated");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "start onResume");
        startAnimation();
        Log.i(TAG, "end onResume");
    }

    void startAnimation() {
        Log.i(TAG, "start startAnamation");
        // Sadly navigation based animation does not work if Intro is hidden
        Animation animRotateIn = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_in);
        view.startAnimation(animRotateIn);
        Log.i(TAG, "end startAnamation");
    }

    void startWebView(View view) {
        Log.i(TAG, "start startWebView");
        Context context = getContext();
        String msg = "Loading .. please wait.";
        Log.i(TAG, "msg: " + msg);
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
        WebView myWebView = (WebView) view.findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
//        webSettings.setAppCacheEnabled(true);
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
        Log.i(TAG, "end startWebView");
    }
}