package com.elbourn.android.twisted;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends OptionsMenu {

    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "start onCreate");
        setContentView(R.layout.activity_main);
        Log.i(TAG, "end onCreate");
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
            finishAffinity();
    }
}