package com.elbourn.android.twisted;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import static android.content.Context.MODE_PRIVATE;

public class DisclaimerFragment extends Fragment {

    static String APP = BuildConfig.APPLICATION_ID;
    static String TAG = "DisclaimerFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Context context = getContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP, MODE_PRIVATE);
        Boolean disclaimerCheckBox = sharedPreferences.getBoolean("disclaimerCheckBox", false);
        Log.i(TAG, "disclaimerCheckBox: " + disclaimerCheckBox);
        if (disclaimerCheckBox) {
            startIntroFragment();
        }
        View view = inflater.inflate(R.layout.fragment_disclaimer, container, false);
        setupButtons(view);
        return view;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        Log.i(TAG, "start onResume");
//        Context context = getContext();
//        SharedPreferences sharedPreferences = context.getSharedPreferences(APP, MODE_PRIVATE);
//        Boolean disclaimerCheckBox = sharedPreferences.getBoolean("disclaimerCheckBox", false);
//        if (disclaimerCheckBox) {
//            startIntroFragment();
//        }
//        Log.i(TAG, "end onResume");
//    }

    void setupButtons(View view) {
        CheckBox disclaimerAgreedBox = view.findViewById(R.id.disclaimerCheckBox);
        disclaimerAgreedBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "disclaimerAgreedBox clicked");
                CheckBox checkBox = (CheckBox) v;
                Context context = getContext();
                SharedPreferences sharedPreferences = context.getSharedPreferences(APP, MODE_PRIVATE);
                if (checkBox.isChecked()) {
                    Log.i(TAG, "disclaimerAgreedBox true");
                    sharedPreferences.edit().putBoolean("disclaimerCheckBox", true).apply();
                    startIntroFragment();
                }
                Log.i(TAG, "disclaimerAgreedBox: " + disclaimerAgreedBox);
            }
        });
    }

    void startIntroFragment() {
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.action_disclaimerFragment_to_introFragment);
    }
}