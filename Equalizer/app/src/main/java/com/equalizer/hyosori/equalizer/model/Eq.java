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
        int[] baseList = new int[4];
        int[] targetList = new int[4];

        baseList[0] = deviceInfos.get(baseModelNum).freq1_mean;
        baseList[1] = deviceInfos.get(baseModelNum).freq2_mean;
        baseList[2] = deviceInfos.get(baseModelNum).freq3_mean;
        baseList[3] = deviceInfos.get(baseModelNum).freq4_mean;

        targetList[0] = deviceInfos.get(targetModelNum).freq1_mean;
        targetList[1] = deviceInfos.get(targetModelNum).freq2_mean;
        targetList[2] = deviceInfos.get(targetModelNum).freq3_mean;
        targetList[3] = deviceInfos.get(targetModelNum).freq4_mean;

        int min = baseList[0];
        int min_i = 0;
        for (int i = 0; i < 4; i++) {
            if (min > baseList[i]) {
                min_i = i;
                min = baseList[i];
            }
        }
        int basePivot = baseList[min_i];
        int targetPivot = targetList[min_i];
        int[] calculList = new int[4];
        for (int i = 0; i < 4; i++) {
            double ratio = baseList[i]/basePivot;
            int applylevel = (int) (ratio * targetPivot) - targetList[i];
            calculList[i] = (int) ((applylevel * 1500) / targetList[i]);
        }

        for (int i = 0; i < 4; i++) {
            mEqualizer.setBandLevel((short) i, (short) calculList[i]);
        }
    }
}
