package com.equalizer.hyosori.equalizer.model;


public class Recorder {
    private BandSet band = new BandSet();

    public boolean record(int frequency) {
        int amplitude = 0;

        /*
            TODO: 측정을 해서 그 값을 amplitude에 넣어주세요
        */

        band.setOne(frequency, amplitude);

        return true;
    }

    public boolean saveDeviceData(String name) {
        /*
            TODO: band와 받아온 name을 저장합니다.
        */

        return true;
    }
}
