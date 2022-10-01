package com.elbourn.android.twisted;

import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.webkit.WebViewAssetLoader;
import androidx.webkit.WebViewClientCompat;

public class MainActivity extends OptionsMenu {

    private String TAG = "MainActivity";
    NavController navController = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "start onCreate");
        setContentView(R.layout.activity_main);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        Log.i(TAG, "navHostFragment: " + navHostFragment);
        navController = navHostFragment.getNavController();
        Log.i(TAG, "navController: " + navController);
        navController.navigate(R.id.disclaimerFragment);
        Log.i(TAG, "start onCreate");
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        Log.i(TAG, "start onResume");
//        if (!(navController == null)) {
//            Log.i(TAG, "navController: " + navController);
//            navController.navigate(R.id.disclaimerFragment);
//        }
//        Log.i(TAG, "end onResume");
//    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
            finishAffinity();
    }
}