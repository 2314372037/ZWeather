package com.example.zweather.h;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by zh231 on 2018/3/14 0014.
 */

public class utils {
    Mode mode = new Mode();
    Context context = null;
    public utils(Context context) {
        this.context = context;
    }

    public void parseJson(String data) {
        try {
            data = data.replace("null", "");
            JSONObject Object = new JSONObject(data);//获取整个json数据
            mode.setStatus(Object.getInt("status"));
            mode.setMsg(Object.getString("msg"));
            if (mode.getStatus() == 0) {
                JSONObject jsonObject = Object.optJSONObject("result");//获取result对象
                mode.setCity(jsonObject.getString("city"));
                mode.setCityid(jsonObject.getString("cityid"));
                mode.setCitycode(jsonObject.getString("citycode"));
                mode.setDate(jsonObject.getString("date"));
                mode.setWeek(jsonObject.getString("week"));
                mode.setWeather(jsonObject.getString("weather"));
                mode.setTemp(jsonObject.getString("temp"));
                mode.setTemphigh("temphigh");
                mode.setTemplow(jsonObject.getString("templow"));
                mode.setImg(jsonObject.getString("img"));
                mode.setHumidity(jsonObject.getString("humidity"));
                mode.setPressure(jsonObject.getString("pressure"));
                mode.setWindspeed(jsonObject.getString("windspeed"));
                mode.setWinddirect(jsonObject.getString("winddirect"));
                mode.setWindpower(jsonObject.getString("windpower"));
                mode.setUpdatetime(jsonObject.getString("updatetime"));
                JSONArray jsonArray = jsonObject.getJSONArray("index");//获取index数组
                JSONObject jsonObject1=null;
                for (int i = 0; i < jsonArray.length(); i++) {
                     jsonObject1= jsonArray.getJSONObject(i);
                    mode.setIname(jsonObject1.getString("iname"),i);
                    mode.setIvalue(jsonObject1.getString("ivalue"),i);
                    mode.setDetail(jsonObject1.getString("detail"),i);
                }
                JSONObject jsonObject2= jsonObject.optJSONObject("aqi");//获取空气info
                mode.setAqi(jsonObject2.getString("aqi"));
                mode.setQuality(jsonObject2.getString("quality"));
                mode.setPm2_5(jsonObject2.getString("pm2_5"));
                mode.setPm10(jsonObject2.getString("pm10"));
                mode.setSo2(jsonObject2.getString("so2"));
                JSONArray jsonArray1=jsonObject.optJSONArray("daily");
                JSONObject jsonObject3=null;
                JSONObject jsonObject_night=null;
                JSONObject jsonObject_day=null;
                for (int i=0;i<jsonArray1.length();i++)
                {
                    jsonObject3 = jsonArray1.getJSONObject(i);
                    jsonObject_night = jsonObject3.getJSONObject("night");
                    jsonObject_day = jsonObject3.getJSONObject("day");
                    mode.setDaily_week(jsonObject3.getString("week"), i);
                    mode.setDaily_sunrise(jsonObject3.getString("sunrise"), i);
                    mode.setDaily_sunset(jsonObject3.getString("sunset"), i);
                    mode.setDaily_night_weather(jsonObject_night.getString("weather"), i);
                    mode.setDaily_day_weather(jsonObject_day.getString("weather"), i);
                    mode.setDaily_temphight(jsonObject_day.getString("temphigh"), i);
                    mode.setDaily_templow(jsonObject_night.getString("templow"), i);
                    mode.setDaily_night_img(jsonObject_night.getString("img"), i);
                    mode.setDaily_day_img(jsonObject_day.getString("img"), i);
//                    Log.d("HomeActivity", "-----------------------------------"+"图片:"+mode.getDaily_day_img(i));
//                    Log.d("HomeActivity", "-----------------------------------"+"白天天气:"+mode.getDaily_day_weather(i));
//                    Log.d("HomeActivity", "-----------------------------------"+"夜间天气:"+mode.getDaily_night_weather(i));
//                    Log.d("HomeActivity", "-----------------------------------"+"日出:"+mode.getDaily_sunrise(i));
//                    Log.d("HomeActivity", "-----------------------------------"+"日落:"+mode.getDaily_sunset(i));
//                    Log.d("HomeActivity", "-----------------------------------"+"日期:"+mode.getDaily_week(i));
//                    Log.d("HomeActivity", "-----------------------------------"+"当日最高:"+mode.getDaily_temphight(i));
//                    Log.d("HomeActivity", "-----------------------------------"+"当日最低:"+mode.getDaily_templow(i));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String readfile(String filename) {//读取本地json
        String data="";
        FileInputStream input = null;
        BufferedReader reader = null;
        try {
            input = context.openFileInput(filename);
            reader = new BufferedReader(new InputStreamReader(input));
            String line;
            while ((line = reader.readLine()) != null) {
                data += line;
            }
        } catch (IOException io) {
            io.printStackTrace();
            return null;
        } finally {
            try {
                if (input != null)
                    input.close();
                if (reader != null)
                    reader.close();
            } catch (IOException i) {
                i.printStackTrace();
            }
        }
        return data;
    }

    public void writerfile(String filename, String data) {//写入本地json文件
        FileOutputStream output = null;
        BufferedWriter bufferedWriter = null;
        try {
            output = context.openFileOutput(filename, Context.MODE_PRIVATE);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(output));
            bufferedWriter.write(data);
        } catch (IOException io) {
            io.printStackTrace();
            Log.d("HomeActivity", "writerfile: "+io.getMessage());
        } finally {
            try {
                if (bufferedWriter != null)
                    bufferedWriter.close();
                if (output != null)
                    output.close();
            } catch (IOException i) {
                i.printStackTrace();
            }
        }
    }

    public String parseCity(String str) {
        String city = null;
        try {
            city = java.net.URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace();
        }
        return city;
    }

    public String getJson(String method, String city) {

        String key = "98fade718660bf21";//api_key,需要填入自己的appkey(自己添加;)
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        String data="";
        try {
            URL url = new URL("http://api.jisuapi.com/weather/query?appkey=" + key + "&" + method + "=" + parseCity(city));
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(6000);
            connection.setReadTimeout(6000);
            inputStream = connection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                data += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null)
                    bufferedReader.close();
                if (connection != null)
                    connection.disconnect();
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException i) {
                i.printStackTrace();
            }
        }
        return data;
    }

    public int getResId(String variableName, Class<?> c) {
        try {
            int id=-1;
            Field[] idField = c.getDeclaredFields();
            for (int i=0;i<idField.length;i++)
            {
                if (variableName.equals(idField[i].getName()))
                {
                    id= idField[i].getInt(idField[i]);
                    break;
                }
            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
