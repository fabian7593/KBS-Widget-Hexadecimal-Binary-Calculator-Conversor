package com.keybellsoft.lastwidget;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
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


    private Button btn0;
    private Button btn1;
    private Button btnClear;
    private Button Convert;

    private TextView EditTextNumbers;



    protected PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.my_widget);
        //remoteViews.set(R.id.btnResult, "Set button text here");



        if ("result".equals(intent.getAction())){
            remoteViews.setTextViewText(R.id.EditTextNumbers, convertToHexa(EditTextNumbers.getText().toString()).toUpperCase());


           /* if(!EditTextNumbers.getText().toString().equals(""))
                EditTextNumbers.setText(convertToHexa(EditTextNumbers.getText().toString()).toUpperCase());*/
        }else if ("btn0".equals(intent.getAction())) {
            //EditTextNumbers.setText(EditTextNumbers.getText().toString() + "0");
            remoteViews.setTextViewText(R.id.EditTextNumbers, convertToHexa(EditTextNumbers.getText().toString() + "0"));
        }
        else if ("btn1".equals(intent.getAction())) {
            //EditTextNumbers.setText(EditTextNumbers.getText().toString() + "2");
            remoteViews.setTextViewText(R.id.EditTextNumbers, convertToHexa(EditTextNumbers.getText().toString() + "1"));
        }
        else if ("btnClear".equals(intent.getAction())) {
            EditTextNumbers.setText("");
            remoteViews.setTextViewText(R.id.EditTextNumbers, convertToHexa(""));
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        //TODO utilice el view y utilice el activity context y los datos me daban null
       // View view = new View(context);

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.my_widget);
        //remoteViews.set(R.id.btnResult, "Set button text here");

        remoteViews.setOnClickPendingIntent(R.id.btnResult,
                getPendingSelfIntent(context, "result"));


        remoteViews.setOnClickPendingIntent(R.id.btn0,
                getPendingSelfIntent(context, "btn0"));

        remoteViews.setOnClickPendingIntent(R.id.btn1,
                getPendingSelfIntent(context, "btn1"));

        remoteViews.setOnClickPendingIntent(R.id.btnClear,
                getPendingSelfIntent(context, "btnClear"));



        //todo conversion a context
       // EditTextNumbers =  ((TextView)((Activity)
        // context).findViewById(R.id.EditTextNumbers));

        /*Convert =  ((Button)((Activity)
                context).findViewById(R.id.btnResult));

        Convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!EditTextNumbers.getText().toString().equals(""))
                    EditTextNumbers.setText(convertToHexa(EditTextNumbers.getText().toString()).toUpperCase());
            }
        });

//todo tambien con find by id
        //btn0 = (Button) view.findViewById(R.id.btn0);
        btn0 =  ((Button)((Activity)
                context).findViewById(R.id.btn0));
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditTextNumbers.setText(EditTextNumbers.getText().toString() + "0");
            }
        });
        //btn1 = (Button) view.findViewById(R.id.btn1);
        btn1 =  ((Button)((Activity)
                context).findViewById(R.id.btn1));
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditTextNumbers.setText(EditTextNumbers.getText().toString() + "1");
            }
        });
       // btnClear = (Button) view.findViewById(R.id.btnClear);
        btnClear =  ((Button)((Activity)
                context).findViewById(R.id.btnClear));
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditTextNumbers.setText("");
            }
        });*/

//        EditTextNumbers =  ((TextView)((Activity)
               // context).findViewById(R.id.EditTextNumbers));
       // EditTextNumbers = (TextView) view.findViewById(R.id.EditTextNumbers);

        // There may be multiple widgets active, so update all of them
        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            updateAppWidget(context, appWidgetManager, appWidgetIds[i]);
        }
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(final View v) {
            switch(v.getId()){
                case R.id.btn0:
                    EditTextNumbers.setText(EditTextNumbers.getText().toString() + "0");
                    break;
                case R.id.btn1:
                    EditTextNumbers.setText(EditTextNumbers.getText().toString() + "1");
                    break;
                case R.id.btnClear:
                    EditTextNumbers.setText("");
                    break;
                case R.id.btnResult:
                    EditTextNumbers.setText(convertToHexa(EditTextNumbers.getText().toString()).toUpperCase());
                    break;
            }

        }
    };


    public String convertToHexa(String result) {
        try {
            int decimal = Integer.parseInt(result, 2);
            String hexStr = Integer.toString(decimal, 16).toUpperCase();
            return hexStr;
        } catch (Exception io) {
            return "";
        }
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        //CharSequence widgetText = MyWidgetConfigureActivity.loadTitlePref(context, appWidgetId);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_widget);
       // views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
}

