package com.cnbeta.cnbetaone.ui.setting;


import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import com.cnbeta.cnbetaone.R;

import javax.inject.Inject;

public class SettingsFragment extends PreferenceFragment {

    @Inject
    public SettingsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_settings);
    }

}
