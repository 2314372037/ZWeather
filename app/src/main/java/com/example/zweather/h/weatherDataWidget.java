package com.example.zweather.h;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

/**
 * Implementation of App Widget functionality.
 */
public class weatherDataWidget extends AppWidgetProvider {

    Mode mode=null;
    utils tools=null;//获取工具类
    String jsonFileName = "data.json";
    Handler handler = null;
    final String TAG="widget";
    private void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        Toast.makeText(context, "正在更新天气数据", Toast.LENGTH_SHORT).show();
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.weather_data_widget);
        //更新ui数据
        //views.setTextViewText(R.id.widGet_weather_city,"你好啊");
        updateUi(context,views);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        tools=new utils(context);

        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
    private void updateUi(Context context,RemoteViews views)
    {
        String jsondata = tools.readfile(jsonFileName);
        if (jsondata!=""||jsondata!=null)
        {
            tools.parseJson(jsondata);
            mode =new Mode();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");// HH:mm:ss
            Date date = new Date(System.currentTimeMillis());
            views.setTextViewText(R.id.widGet_weather_city,mode.getCity());
            views.setTextViewText(R.id.widGet_time,simpleDateFormat.format(date));
            views.setTextViewText(R.id.widGet_weather_weather,mode.getWeather()+" "+mode.getTemp()+"°");
            views.setTextViewText(R.id.widGet_weather_updateTime,"更新时间："+mode.getUpdatetime());
            views.setTextViewText(R.id.widGet_sunrise,"日出："+mode.getDaily_sunrise(0));
            views.setTextViewText(R.id.widGet_sunset,"日落："+mode.getDaily_sunset(0));
            int resId= tools.getResId("img"+mode.getDaily_day_img(0),R.drawable.class);
            views.setImageViewResource(R.id.widGet_weather_img,resId);
        }
        else
        {
            Toast.makeText(context, "没有本地数据", Toast.LENGTH_SHORT).show();
        }
    }
}

