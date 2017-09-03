package com.equalizer.hyosori.equalizer.model;


public class BandSet {
    private int[] amplitudes = new int[5];

    public BandSet(String name) {
        //이름으로 파일에서 정보를 읽어옵니다.
    }

    public int[] getBandSet() {
        int[] bandSet = new int[5];

        for (int i = 0; i < 5; ++i) {
            bandSet[i] = this.amplitudes[i]; //deep copy
        }

        return bandSet;
    }

}