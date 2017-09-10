package com.equalizer.hyosori.equalizer.presenter;


import com.equalizer.hyosori.equalizer.model.BandSet;
import com.equalizer.hyosori.equalizer.model.Eq;
import com.equalizer.hyosori.equalizer.view.SetterView;

public class SetterPresenter implements Presenter {

    private SetterView view;
    private Eq model;
    private BandSet base;
    private BandSet target;

    public SetterPresenter(SetterView view) {
        this.view = view;
        this.model = new Eq();
    }

    @Override
    public void onCreate() {
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

    public void onApplyBtnSelected(String baseName, String targetName) {
        this.base = new BandSet(baseName);
        this.target = new BandSet(targetName);

        this.view.setSeekBars(this.target.getBandSet());
    }

    public void onSeekBarChanged(int frequency, int amplitude) {

    }
}
