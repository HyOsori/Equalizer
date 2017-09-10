package com.equalizer.hyosori.equalizer.presenter;


import com.equalizer.hyosori.equalizer.model.Recorder;
import com.equalizer.hyosori.equalizer.view.GetterView;

public class GetterPresenter implements Presenter {
    private GetterView view;
    private Recorder model;

    public GetterPresenter(GetterView view) {
        this.view = view;
        this.model = new Recorder();
    }

    public void onCreate() {
        model = new Recorder();
    }

    public void onPause() {

    }

    public void onResume() {

    }

    public void onDestroy() {

    }

    public boolean onGetBtnClicked(int frequency) {
        return model.record(frequency);
    }

    public boolean onSaveBtnClicked(String name) {
        return model.saveDeviceData(name);
    }
}
