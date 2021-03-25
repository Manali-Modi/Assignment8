package com.example.assignment8;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsFragment extends PreferenceFragmentCompat {

    Preference statusPreference;
    Preference actionPreference;

    Activity ctx;
    ActionBar actionBar;

    int sColor;
    int aColor;

    public SettingsFragment(Activity ct) {
        ctx = ct;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        actionBar = ((SettingsActivity) ctx).getSupportActionBar();
        assert actionBar != null;
        actionBar.setBackgroundDrawable(new ColorDrawable(aColor));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);

        statusPreference = findPreference("status_color");
        actionPreference = findPreference("action_color");

        sColor = Color.parseColor(statusPreference.getSharedPreferences().getString("status_color", ctx.getResources().getString(R.color.purple_700)));
        //sColor = ctx.getResources().getColor(R.color.purple_700);
        aColor = Color.parseColor(actionPreference.getSharedPreferences().getString("action_color", ctx.getResources().getString(R.color.purple_500)));
        //aColor = ctx.getResources().getColor(R.color.purple_700);
        Log.d("color", "aColor - " + aColor);
        Window window = getActivity().getWindow();
        window.setStatusBarColor(sColor);

        statusPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ColorPickerDialogBuilder.with(ctx)
                        .setTitle("Choose colour")
                        .initialColor(sColor)
                        .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                        .density(12)
                        .setOnColorSelectedListener(new OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int selectedColor) {
                                Toast.makeText(ctx, "onColorSelected: 0x" + Integer.toHexString(selectedColor), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("Ok", new ColorPickerClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                                window.setStatusBarColor(selectedColor);
                                statusPreference.getSharedPreferences().edit().putString("status_color", "#" + Integer.toHexString(selectedColor)).apply();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .build()
                        .show();
                return true;
            }
        });

        actionPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ColorPickerDialogBuilder.with(ctx)
                        .setTitle("Choose colour")
                        .initialColor(aColor)
                        .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                        .density(12)
                        .setOnColorSelectedListener(new OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int selectedColor) {
                                Toast.makeText(ctx, "onColorSelected: 0x" + Integer.toHexString(selectedColor), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("ok", new ColorPickerClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                                Log.d("color", "selectedColor - " + selectedColor);
                                actionBar.setBackgroundDrawable(new ColorDrawable(selectedColor));
                                actionPreference.getSharedPreferences().edit().putString("action_color","#" + Integer.toHexString(selectedColor)).apply();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .build()
                        .show();
                return true;
            }
        });
    }
}
