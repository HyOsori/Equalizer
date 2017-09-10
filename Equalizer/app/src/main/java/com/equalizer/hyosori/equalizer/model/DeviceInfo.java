package com.equalizer.hyosori.equalizer.model;


/**
 * Created by JiHyeonSEO on 2017-09-09.
 */

public class DeviceInfo {
    public String model;
    public int freq1_mean;
    public int freq2_mean;
    public int freq3_mean;
    public int freq4_mean;

    public DeviceInfo()
    {

    }

    public DeviceInfo(String model, int freq1, int freq2, int freq3, int freq4)
    {
        this.model = model;
        this.freq1_mean = freq1;
        this.freq2_mean = freq2;
        this.freq3_mean = freq3;
        this.freq4_mean = freq4;
    }
}