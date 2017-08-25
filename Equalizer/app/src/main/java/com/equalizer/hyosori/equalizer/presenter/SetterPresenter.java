package com.equalizer.hyosori.equalizer.presenter;


import com.equalizer.hyosori.equalizer.model.Recorder;
import com.equalizer.hyosori.equalizer.view.SetterView;

public class SetterPresenter implements Presenter {

    private SetterView view;
    private Recorder model;

    public SetterPresenter(SetterView view) {
        this.view = view;
        this.model = new Recorder();
    }

    @Override
    public void onCreate() {
        model = new Recorder();
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
}
