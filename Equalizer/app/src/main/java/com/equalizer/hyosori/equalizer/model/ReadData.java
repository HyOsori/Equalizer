package com.equalizer.hyosori.equalizer.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Joonho Wohn on 2017-09-10.
 */

public class ReadData {
    public ArrayList<DeviceInfo> earphoneInfoList = new ArrayList<DeviceInfo>();

    public ArrayList<DeviceInfo> ReadData()
    {
        File file = new File("sdcard/data.json");
        earphoneInfoList.clear();

        if(file.exists()) {
            StringBuilder text = new StringBuilder();

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;

                while ((line = br.readLine()) != null) {
                    text.append(line);
                    text.append("\n");
                }
                // 새로운 문자열을 만들어서 내부 버퍼의 내용을 복사하고 반환
                String jString = text.toString();

                JSONObject jsonObject = new JSONObject(jString);

                // json value 값 얻기
                //String device = jsonObject.getString("device").toString();
                //System.out.println("divece:" + device);

                JSONArray freqArr = new JSONArray(jsonObject.getString("earphone"));
                DeviceInfo d_ = new DeviceInfo();
                for(int i = 0; i < freqArr.length(); i++){
                    JSONObject earphoneInfo = freqArr.getJSONObject(i);
                    DeviceInfo dvinfo = new DeviceInfo();
                    dvinfo.model  = earphoneInfo.getString("model");
                    dvinfo.freq1_mean = earphoneInfo.getInt("freq1_mean");
                    dvinfo.freq2_mean = earphoneInfo.getInt("freq2_mean");
                    dvinfo.freq3_mean = earphoneInfo.getInt("freq3_mean");
                    dvinfo.freq4_mean = earphoneInfo.getInt("freq4_mean");

                    //ArrayList<DeviceInfo> deviceInfoList = new ArrayList<DeviceInfo>();
                    //earphoenInfoList = new ArrayList<JSONObject>();
                    earphoneInfoList.add(dvinfo);
                }

                for(int i = 0; i < freqArr.length(); i++)
                {
                    Log.i("earphone1 : ", earphoneInfoList.get(i).toString());
                }
            }catch (JSONException je){
                Log.e("jsonErr", "json 에러", je);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            Log.i("error : ", "파일 없음");
        }
        return earphoneInfoList;
    }
}
