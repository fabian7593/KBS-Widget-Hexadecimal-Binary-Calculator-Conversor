package com.keybellsoft.lastwidget;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link  }
 */
public class MyWidget extends AppWidgetProvider {

    private static final String btn0 = "btn0";
    private static final String btn1 = "btn1";
    private static final String btnClear = "btnClear";
    private static final String btnResult = "btnResult";

    private static String editTextNumbers = "";

    protected PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.my_widget);
        AppWidgetManager widManager = (AppWidgetManager.getInstance(context));

        if (btnResult.equals(intent.getAction())) {
            remoteViews.setTextViewText(R.id.EditTextNumbers, convertToHexa(editTextNumbers));

        } else if (btn0.equals(intent.getAction())) {
            editTextNumbers = editTextNumbers + "0";
            remoteViews.setTextViewText(R.id.EditTextNumbers, editTextNumbers);
        } else if (btn1.equals(intent.getAction())) {
            editTextNumbers = editTextNumbers + "1";
            remoteViews.setTextViewText(R.id.EditTextNumbers, editTextNumbers);
        } else if (btnClear.equals(intent.getAction())) {
            editTextNumbers = "";
            remoteViews.setTextViewText(R.id.EditTextNumbers, "");
        }
        ComponentName thisWidget = new ComponentName(context, MyWidget.class);
        widManager.updateAppWidget(widManager.getAppWidgetIds(thisWidget), remoteViews);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        // Get all ids
        ComponentName thisWidget = new ComponentName(context, MyWidget.class);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.my_widget);
        remoteViews.setOnClickPendingIntent(R.id.btnResult, getPendingSelfIntent(context, btnResult));
        remoteViews.setOnClickPendingIntent(R.id.btn0, getPendingSelfIntent(context, btn0));
        remoteViews.setOnClickPendingIntent(R.id.btn1, getPendingSelfIntent(context, btn1));
        remoteViews.setOnClickPendingIntent(R.id.btnClear, getPendingSelfIntent(context, btnClear));
        appWidgetManager.updateAppWidget(appWidgetManager.getAppWidgetIds(thisWidget), remoteViews);
    }

    public String convertToHexa(String result) {
        try {
            int decimal = Integer.parseInt(result, 2);
            String hexStr = Integer.toString(decimal, 16).toUpperCase();
            return hexStr;
        } catch (Exception io) {
            return "";
        }
    }
}

