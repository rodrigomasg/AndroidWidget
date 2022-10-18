package com.example.androidwidget;

import static com.example.androidwidget.ExampleWidgetConfigActivity.KEY_BUTTON;
import static com.example.androidwidget.ExampleWidgetConfigActivity.SHARED_PREF;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

public class ExampleAppWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appwidgetId: appWidgetIds){
            Toast.makeText(context, "onUpdate", Toast.LENGTH_SHORT);
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            SharedPreferences prefs = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
            String btnTxt = prefs.getString(KEY_BUTTON + appwidgetId, "widget pro");


            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.example_widget);
            views.setOnClickPendingIntent(R.id.item_widget_btn_1, pendingIntent);
            views.setCharSequence(R.id.item_widget_btn_1, "setText", btnTxt);;

//            resize
            Bundle appWidgetBundle = appWidgetManager.getAppWidgetOptions(appwidgetId);
            resizeWidget(appWidgetBundle, views);

            appWidgetManager.updateAppWidget(appwidgetId, views);
        }
    }

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.example_widget);

    /*    int minWidth = newOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH);
        int maxWidth = newOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_WIDTH);
        int minHeight = newOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_HEIGHT);
        int maxHeight = newOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_HEIGHT);*/

      /*  String dimen = "MinWidth "  + minWidth + "\nMaxWidth " + maxWidth
                + "\nMinHeight " + minHeight + "\nMaxHeight" + maxHeight;
        Toast.makeText(context, dimen, Toast.LENGTH_LONG).show();*/

     /*   if (maxWidth > 100){
            views.setViewVisibility(R.id.item_widget_tv_1, View.VISIBLE);
        } else {
            views.setViewVisibility(R.id.item_widget_tv_1, View.GONE);
        }*/
        resizeWidget(newOptions, views);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    private void resizeWidget(Bundle widgetOptions, RemoteViews view){
        int minWidth = widgetOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH);
        int maxWidth = widgetOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_WIDTH);
        int minHeight = widgetOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_HEIGHT);
        int maxHeight = widgetOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_HEIGHT);


        if (maxWidth > 100){
            view.setViewVisibility(R.id.item_widget_tv_1, View.VISIBLE);
        } else {
            view.setViewVisibility(R.id.item_widget_tv_1, View.GONE);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        Toast.makeText(context, "onDeleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisabled(Context context) {
        Toast.makeText(context, "onDisable", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEnabled(Context context) {
        Toast.makeText(context, "onEnabled", Toast.LENGTH_SHORT).show();
    }
}
