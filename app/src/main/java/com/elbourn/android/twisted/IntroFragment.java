package com.elbourn.android.twisted;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;

import static android.content.Context.MODE_PRIVATE;

public class IntroFragment extends Fragment {

    static String APP = BuildConfig.APPLICATION_ID;
    static String TAG = "IntroFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Context context = getContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP, MODE_PRIVATE);
        Boolean introCheckBox = sharedPreferences.getBoolean("introCheckBox", false);
        Log.i(TAG, "introCheckBox: " + introCheckBox);
        if (introCheckBox) {
            startWebviewFragment();
        }
        View view = inflater.inflate(R.layout.fragment_intro, container, false);
        setupButtons(view);
        return view;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        Log.i(TAG, "start onResume");
//        Context context = getContext();
//        SharedPreferences sharedPreferences = context.getSharedPreferences(APP, MODE_PRIVATE);
//        Boolean introCheckBox = sharedPreferences.getBoolean("introCheckBox", false);
//        Log.i(TAG, "introCheckBox: " + introCheckBox);
//        if (introCheckBox) {
//            startWebviewFragment();
//        }
//        Log.i(TAG, "end onResume");
//    }

    void setupButtons(View view) {
        ImageButton introImageButton = view.findViewById(R.id.introImageButton);
        introImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "introImageButton clicked");
                startWebviewFragment();
            }
        });
        CheckBox introCheckBox = view.findViewById(R.id.introCheckBox);
        introCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "introCheckBox clicked");
                CheckBox checkBox = (CheckBox) v;
                Context context = getContext();
                SharedPreferences sharedPreferences = context.getSharedPreferences(APP, MODE_PRIVATE);
                setIntroCheckBox(context, checkBox.isChecked());
            }
        });
    }

    static public boolean getIntroCheckBox(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP, MODE_PRIVATE);
        Boolean introCheckBox = sharedPreferences.getBoolean("introCheckBox", false);
        Log.i(TAG, "introCheckBox: " + introCheckBox);
        return introCheckBox;
    }

    static public void setIntroCheckBox(Context context, Boolean checkBox) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP, MODE_PRIVATE);
        if (checkBox) {
            Log.i(TAG, "introCheckBox true");
            sharedPreferences.edit().putBoolean("introCheckBox", true).apply();
        } else {
            Log.i(TAG, "introCheckBox false");
            sharedPreferences.edit().putBoolean("introCheckBox", false).apply();
        }
        Boolean introCheckBox = sharedPreferences.getBoolean("introCheckBox", false);
        Log.i(TAG, "introCheckBox: " + introCheckBox);
    }

    void startWebviewFragment() {
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.action_introFragment_to_webviewFragment);

    }
}