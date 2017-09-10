package com.equalizer.hyosori.equalizer.model;


import android.media.audiofx.Equalizer;

import java.util.ArrayList;

public class Eq {
    public int[] PresentEq(Equalizer mEqualizer) {
        int[] bandLevelList = new int[4];
        for (int i = 0; i < 4; i++) {
            int level = mEqualizer.getBandLevel((short) i);
            bandLevelList[i] = level;
        }
        return bandLevelList;
    }
    public void ApplyEq(int baseModelNum, int targetModelNum, ArrayList<DeviceInfo> deviceInfos, Equalizer mEqualizer) {

    }
}
