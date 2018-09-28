package com.cnbeta.cnbetaone.ui.setting;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cnbeta.cnbetaone.R;

import java.util.Objects;

import javax.inject.Inject;

public class SettingsFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceClickListener {
    public static final String KEY_ABOUT = "key_about_app";
    public static final String KEY_CHECK_UPDATE = "key_check_update";

    @Inject
    public SettingsFragment() {
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.pref_settings, rootKey);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LayoutInflater.from(getActivity()).inflate(R.layout.about_info, (ViewGroup) view, true);
        findPreference(KEY_ABOUT).setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        switch (preference.getKey()) {
            case KEY_ABOUT:
                openWithBrowser();
                return true;
            case KEY_CHECK_UPDATE:
                // TODO 检查更新
                break;
        }
        return false;
    }

    public void openWithBrowser() {
        String url = getString(R.string.github_home_url);
        Uri uri = Uri.parse(url);
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(uri);
        Objects.requireNonNull(getActivity()).startActivity(intent);
    }
}
