package com.equalizer.hyosori.equalizer.model;


public class BandSet {
    public int[] amplitudes = new int[4];

    public BandSet(String name) {
        //이름으로 파일에서 정보를 읽어옵니다.

    }

    public BandSet() {
        for (int i = 0; i < 4; ++i) {
            amplitudes[i] = 0;
        }
    }

    public void setOne(int frequency, int amplitude) {
        switch (frequency) {
            case 60:
                amplitudes[0] = amplitude;
                break;
            case 230:
                amplitudes[1] = amplitude;
                break;
            case 910:
                amplitudes[2] = amplitude;
                break;
            case 3600:
                amplitudes[3] = amplitude;
                break;
        }
    }

    public int[] getBandSet() {
        int[] bandSet = new int[4];

        for (int i = 0; i < 4; ++i) {
            bandSet[i] = this.amplitudes[i]; //deep copy
        }

        return bandSet;
    }

}