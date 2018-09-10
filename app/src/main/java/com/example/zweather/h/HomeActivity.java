package com.example.zweather.h;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wnafee.vector.MorphButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeActivity extends AppCompatActivity implements MorphButton.OnStateChangedListener,DrawerLayout.DrawerListener{
    private final String TAG = "HomeActivity";
    ImageView bingimg = null;
    utils _utils = null;
    Button ref_button=null;
    String jsonFileName = "data.json";
    Handler handler = null;
    HorizontalScrollView scrollView=null;
    MorphButton menuButton=null;
    DrawerLayout settingPanel=null;
    EditText view_edit_city=null;
    TextView view_updateTime=null;
    TextView view_aqi=null;
    TextView view_api_info;
    TextView view_city = null;
    TextView view_temp = null;
    TextView view_weather = null;
    TextView view_wind = null;
    TextView view_humidity = null;
    TextView view_iname_value1 = null;
    TextView view_detail1 = null;
    TextView view_iname_value2 = null;
    TextView view_detail2 = null;
    TextView view_iname_value3 = null;
    TextView view_detail3 = null;
    TextView view_iname_value4 = null;
    TextView view_detail4 = null;
    TextView view_iname_value5 = null;
    TextView view_detail5 = null;
    TextView view_iname_value6 = null;
    TextView view_detail6 = null;
    TextView view_iname_value7 = null;
    TextView view_detail7 = null;
    TextView dayTitle = null;
    TextView infoTitle = null;
    Button save_button=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        SharedPreferences sharedPreferences=getSharedPreferences("save_time.dat",MODE_PRIVATE);
        view_edit_city.setText(sharedPreferences.getString("city","永川"));
        bingimg = (ImageView) findViewById(R.id.bingimg);
        _utils = new utils(HomeActivity.this);
        showimage("https://open.saintic.com/api/bingPic/?&picSize=2");
        settingPanel.addDrawerListener(this);
        ref_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        String jsondata = _utils.getJson("city", view_edit_city.getText().toString());
                        _utils.writerfile(jsonFileName,jsondata);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                settingPanel.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                                scrollView.removeAllViews();
                                Toast.makeText(HomeActivity.this, "完成", Toast.LENGTH_SHORT).show();
                                String jsondata = _utils.readfile(jsonFileName);
                                _utils.parseJson(jsondata);
                                String currentDate=String.valueOf(System.currentTimeMillis());
                                Log.d("当前时间:",String.valueOf(System.currentTimeMillis()));
                                SharedPreferences.Editor sharedPreferences_write=getSharedPreferences("save_time.dat",MODE_PRIVATE).edit();//写入
                                sharedPreferences_write.putString("time",currentDate);
                                sharedPreferences_write.apply();
                                Toast.makeText(HomeActivity.this,"保存当前时间戳："+currentDate,Toast.LENGTH_LONG);
                                showOnUi();
                            }
                        });
                    }
                };
                Thread thread = new Thread(runnable);
                thread.start();
            }
        });
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        String jsondata = _utils.getJson("city", view_edit_city.getText().toString());
                        _utils.writerfile(jsonFileName,jsondata);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                SharedPreferences.Editor sharedPreferences_write=getSharedPreferences("save_time.dat",MODE_PRIVATE).edit();//写入
                                sharedPreferences_write.putString("city",view_edit_city.getText().toString());
                                String currentDate=String.valueOf(System.currentTimeMillis());
                                Log.d("当前时间:",String.valueOf(System.currentTimeMillis()));
                                sharedPreferences_write.putString("time",currentDate);
                                sharedPreferences_write.apply();
                                Toast.makeText(HomeActivity.this,"保存当前时间戳："+currentDate,Toast.LENGTH_LONG);
                                settingPanel.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                                scrollView.removeAllViews();
                                Toast.makeText(HomeActivity.this, "完成", Toast.LENGTH_SHORT).show();
                                String jsondata = _utils.readfile(jsonFileName);
                                _utils.parseJson(jsondata);

                                showOnUi();
                            }
                        });
                    }
                };
                Thread thread = new Thread(runnable);
                thread.start();
            }
        });
        menuButton.setOnStateChangedListener(this);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.arg1) {
                    case 1:
                        _utils.writerfile(jsonFileName, (String) msg.obj);
                        String jsondata = _utils.readfile(jsonFileName);
                        Toast.makeText(HomeActivity.this, "获取成功", Toast.LENGTH_SHORT).show();
                        _utils.parseJson(jsondata);
                        String currentDate=String.valueOf(System.currentTimeMillis());
                        Log.d("当前时间:",String.valueOf(System.currentTimeMillis()));
                        SharedPreferences.Editor sharedPreferences_write=getSharedPreferences("save_time.dat",MODE_PRIVATE).edit();//写入
                        sharedPreferences_write.putString("time",currentDate);
                        sharedPreferences_write.apply();
                        Toast.makeText(HomeActivity.this,"保存当前时间戳："+currentDate,Toast.LENGTH_LONG);
                        showOnUi();
                        //显示到ui
                        break;
                }
            }
        };
        String jsondata = _utils.readfile(jsonFileName);
        if (jsondata == "" || jsondata == null) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    String jsondata = _utils.getJson("city", view_edit_city.getText().toString());
                    Message message = new Message();
                    message.arg1 = 1;
                    message.obj = jsondata;
                    handler.sendMessage(message);
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
        } else {
            _utils.parseJson(jsondata);
            String time= sharedPreferences.getString("time",null);
            long curTime=System.currentTimeMillis()-43200000;
            if (time!=null||time!="")
            {
                if (curTime>Long.parseLong(time))//如果今天的时间是大于昨天的时间
                {
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            String jsondata = _utils.getJson("city", view_edit_city.getText().toString());
                            Message message = new Message();
                            message.arg1 = 1;
                            message.obj = jsondata;
                            handler.sendMessage(message);
                        }
                    };
                    Thread thread = new Thread(runnable);
                    thread.start();
                }
                else
                {
                    showOnUi();
                    //显示到ui
                }
            }
            Log.d(TAG, "本地获取jsondata,开始解析" + jsondata);
        }
    }
    //获取bing图片
    private void showimage(final String string) {//显示bing壁纸--Glide
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Glide.with(HomeActivity.this).load(string).into(bingimg);
            }
        });
    }//显示bing图片
    private void initView() {
        ref_button=(Button)findViewById(R.id.ref_btn);
        view_edit_city=(EditText)findViewById(R.id.view_edit_city);
        save_button=(Button)findViewById(R.id.save_button);
        scrollView=(HorizontalScrollView)findViewById(R.id.scrollView);
        view_updateTime=(TextView)findViewById(R.id.view_updateTime);
        view_aqi=(TextView)findViewById(R.id.view_aqi);
        view_api_info=(TextView)findViewById(R.id.view_api_info);
        view_city = (TextView) findViewById(R.id.view_city);
        view_temp = (TextView) findViewById(R.id.view_temp);
        view_weather = (TextView) findViewById(R.id.view_weather);
        view_wind = (TextView) findViewById(R.id.view_wind);
        view_humidity = (TextView) findViewById(R.id.view_humidity);
        view_iname_value1 = (TextView) findViewById(R.id.view_iname_ivalue1);
        view_detail1 = (TextView) findViewById(R.id.view_detail1);
        view_iname_value2 = (TextView) findViewById(R.id.view_iname_ivalue2);
        view_detail2 = (TextView) findViewById(R.id.view_detail2);
        view_iname_value3 = (TextView) findViewById(R.id.view_iname_ivalue3);
        view_detail3 = (TextView) findViewById(R.id.view_detail3);
        view_iname_value4 = (TextView) findViewById(R.id.view_iname_ivalue4);
        view_detail4 = (TextView) findViewById(R.id.view_detail4);
        view_iname_value5 = (TextView) findViewById(R.id.view_iname_ivalue5);
        view_detail5 = (TextView) findViewById(R.id.view_detail5);
        view_iname_value6 = (TextView) findViewById(R.id.view_iname_ivalue6);
        view_detail6 = (TextView) findViewById(R.id.view_detail6);
        view_iname_value7 = (TextView) findViewById(R.id.view_iname_ivalue7);
        view_detail7 = (TextView) findViewById(R.id.view_detail7);
        menuButton=(MorphButton) findViewById(R.id.menubutton);
        settingPanel=(DrawerLayout)findViewById(R.id.SettingPanel);
        dayTitle=(TextView)findViewById(R.id.dayTitle);
        infoTitle=(TextView)findViewById(R.id.infoTitle);
    }//初始话界面控件实例
    private void showOnUi() {
        Mode mode = new Mode();
        view_updateTime.setText("更新时间 "+mode.getUpdatetime());
        view_edit_city.setText(mode.getCity());
        view_city.setText(mode.getCity());
        view_temp.setText(" "+mode.getTemp() + "°");
        view_weather.setText(mode.getWeather());
        view_wind.setText("风速" + mode.getWindspeed() + " " + mode.getWinddirect() + mode.getWindpower());
        view_humidity.setText(" 湿度" + mode.getHumidity()+"%");
        view_aqi.setText(mode.getAqi());
        view_api_info.setText(mode.getQuality());
        int apinum=Integer.parseInt(mode.getAqi());
        if (apinum<50)
        {
            view_aqi.setBackground(new ColorDrawable(Color.parseColor("#7CCD7C")));
            view_api_info.setBackground(new ColorDrawable(Color.parseColor("#9ACD32")));
        }else if (apinum>50&&apinum<100)
        {
            view_aqi.setBackground(new ColorDrawable(Color.parseColor("#CDC673")));
            view_api_info.setBackground(new ColorDrawable(Color.parseColor("#8FBC8F")));
        }else if (apinum>100&&apinum<150)
        {
            view_aqi.setBackground(new ColorDrawable(Color.parseColor("#CD4F39")));
            view_api_info.setBackground(new ColorDrawable(Color.parseColor("#8E8E38")));
        }else if (apinum>150&&apinum<200)
        {
            view_aqi.setBackground(new ColorDrawable(Color.parseColor("#CD3333")));
            view_api_info.setBackground(new ColorDrawable(Color.parseColor("#8E8E38")));
        }
        else if (apinum>200)
        {
            view_aqi.setBackground(new ColorDrawable(Color.parseColor("#CD0000")));
            view_api_info.setBackground(new ColorDrawable(Color.parseColor("#8B8989")));
        }
        createEveryDayWeather(mode,7);
        for (int i = 0; i < 7; i++) {
            switch (i) {
                case 0:
                    view_iname_value1.setText(mode.getIname(i) + "\t" + mode.getIvalue(i));
                    view_detail1.setText(mode.getDetail(i));
                    break;
                case 1:
                    view_iname_value2.setText(mode.getIname(i) + "\t" + mode.getIvalue(i));
                    view_detail2.setText(mode.getDetail(i));
                    break;
                case 2:
                    view_iname_value3.setText(mode.getIname(i) + "\t" + mode.getIvalue(i));
                    view_detail3.setText(mode.getDetail(i));
                    break;
                case 3:
                    view_iname_value4.setText(mode.getIname(i) + "\t" + mode.getIvalue(i));
                    view_detail4.setText(mode.getDetail(i));
                    break;
                case 4:
                    view_iname_value5.setText(mode.getIname(i) + "\t" + mode.getIvalue(i));
                    view_detail5.setText(mode.getDetail(i));
                    break;
                case 5:
                    view_iname_value6.setText(mode.getIname(i) + "\t" + mode.getIvalue(i));
                    view_detail6.setText(mode.getDetail(i));
                    break;
                case 6:
                    view_iname_value7.setText(mode.getIname(i) + "\t" + mode.getIvalue(i));
                    view_detail7.setText(mode.getDetail(i));
                    break;
            }
        }
    }//显示信息到ui界面
    private void createEveryDayWeather(Mode mode,int l){
        LinearLayout linearLayout=new LinearLayout(this);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout linearLayout1=null;
        LinearLayout linearLayout2=null;
        TextView time_info=null;
        TextView temp_hight=null;
        TextView temp_low=null;
        TextView weather=null;
        ImageView imageView=null;
        for (int i=0;i<l;i++)
        {
            linearLayout1=new LinearLayout(this);
            linearLayout2=new LinearLayout(this);
            time_info=new TextView(this);
            temp_hight=new TextView(this);
            temp_low=new TextView(this);
            weather=new TextView(this);
            imageView=new ImageView(this);
            linearLayout1.setOrientation(LinearLayout.VERTICAL);
            linearLayout1.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            linearLayout2.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout2.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            time_info.setText(mode.getDaily_week(i));
            time_info.setTextSize(24);
            SimpleDateFormat sDateFormat = new SimpleDateFormat("HH");
            String time= sDateFormat.format(new Date());
            if (Integer.parseInt(time)>18)
            {
                temp_hight.setText(" | "+mode.getDaily_night_weather(i)+"         ");
            }else{
                temp_hight.setText(" | "+mode.getDaily_day_weather(i)+"         ");
            }
            temp_hight.setTextSize(16);
            temp_low.setText(mode.getDaily_templow(i));
            temp_low.setTextSize(18);
            weather.setText(mode.getDaily_day_weather(i));
            weather.setTextSize(18);
            int a=8*Integer.parseInt(mode.getDaily_temphight(i));//动态温度颜色
            if (a>255)a=255;weather.setTextColor(Color.argb(255,a,142,35));
            int resId= _utils.getResId("img"+mode.getDaily_day_img(i),R.drawable.class);
            imageView.setImageResource(resId);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(48,50));
            linearLayout2.addView(temp_low);
            linearLayout2.addView(temp_hight);
            linearLayout1.addView(time_info);
            linearLayout1.addView(linearLayout2);
            linearLayout1.addView(imageView);
            linearLayout1.addView(weather);
            linearLayout.addView(linearLayout1);
            dayTitle.setVisibility(View.VISIBLE);
            infoTitle.setVisibility(View.VISIBLE);
        }
       scrollView.addView(linearLayout);
    }//动态创建每日天气

    @Override
    public void onStateChanged(MorphButton.MorphState changedTo, boolean isAnimating) {
        if (changedTo.toString().equals("START"))
        {
            settingPanel.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
        }
        else if (changedTo.toString().equals("END"))
        {
            settingPanel.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(View drawerView) {

    }

    @Override
    public void onDrawerClosed(View drawerView) {
        menuButton.setState(MorphButton.MorphState.END);
    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }
}
