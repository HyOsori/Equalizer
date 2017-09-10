package com.equalizer.hyosori.equalizer.presenter;


import android.media.audiofx.Equalizer;

import com.equalizer.hyosori.equalizer.model.BandSet;
import com.equalizer.hyosori.equalizer.model.DeviceInfo;
import com.equalizer.hyosori.equalizer.model.ReadData;
import com.equalizer.hyosori.equalizer.model.Eq;
import com.equalizer.hyosori.equalizer.view.SetterView;
import java.util.ArrayList;

public class SetterPresenter implements Presenter {

    private SetterView view;
    private Eq model;
//    private BandSet base;
//    private BandSet target;
    ArrayList<DeviceInfo> earphoneInfoList = new ArrayList<DeviceInfo>();
    private Equalizer mEqualizer;





    public SetterPresenter(SetterView view) {
        this.view = view;
        this.model = new Eq();
    }
    @Override
    public void onCreate() {
        ReadData rd = new ReadData();
        earphoneInfoList.clear();
        earphoneInfoList = rd.ReadData();
        ArrayList<String> earphoneModelList = new ArrayList<String>();
        for (int i = 0; i < earphoneInfoList.size(); i++) {
            String model = earphoneInfoList.get(i).model;
            earphoneModelList.add(model);
        }
        view.setSpinnerData(earphoneModelList);
        try {
            mEqualizer = null;
            mEqualizer = new Equalizer(0, 0);
            mEqualizer.setEnabled(true);

        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        }

        Eq eq_ = new Eq();
        int[] bandLevelList = eq_.PresentEq(mEqualizer);
        view.setSeekBars(bandLevelList);
        model = new Eq();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    public void onApplyBtnSelected(int baseNum, int targetNum) {
//        this.base = new BandSet(baseName);
//        this.target = new BandSet(targetName);
        model.ApplyEq(baseNum, targetNum, earphoneInfoList, mEqualizer);
        this.view.setSeekBars(model.PresentEq(mEqualizer));

    }

    public void onSeekBarChanged(int frequency, int amplitude) {

    }
}
