package com.example.androidwidget;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;

public class ExampleWidgetConfigActivity extends AppCompatActivity {
    public static final String SHARED_PREF = "prefs";
    public static final String KEY_BUTTON = "key button";

    private int appWigdetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_widget_config);


        Intent configIntent = getIntent();
        Bundle extras = configIntent.getExtras();
        if (extras != null) {
            appWigdetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        Intent resulValue = new Intent();
        resulValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWigdetId);
        setResult(RESULT_CANCELED, resulValue);


        if (appWigdetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
        }

        editText = findViewById(R.id.conf_et);
        Button btnSet = findViewById(R.id.conf_btn);
        btnSet.setOnClickListener(v -> confirmConf());
    }

    private void confirmConf() {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);

        Intent intent = new Intent( this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        String btnText = editText.getText().toString();

//        this part i sofr gradietn
        Intent serviceIntent = new Intent(this, ExampleWidgetServicesGRadient.class);
        serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWigdetId);
        serviceIntent.setData(Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME)));



        RemoteViews views = new RemoteViews(this.getPackageName(), R.layout.example_widget);
        views.setOnClickPendingIntent(R.id.item_widget_btn_1, pendingIntent);
        views.setCharSequence(R.id.item_widget_btn_1, "setText", btnText);
//        views.setInt(R.id.item_widget_btn_1, "setBackgroundColor", Color.RED);
//        views.setBoolean(R.id.item_widget_btn_1, "setEnabled", false);
        appWidgetManager.updateAppWidget(appWigdetId, views);
//        this part gradient
        views.setRemoteAdapter(R.id.item_widget_stack, serviceIntent);
        views.setEmptyView(R.id.item_widget_stack, R.id.item_widget_tv_stack_empty);



        SharedPreferences prefs = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_BUTTON + appWigdetId, btnText);
        editor.apply();

        Intent resulValue = new Intent();
        resulValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWigdetId);
        setResult(RESULT_OK, resulValue);
        finish();

    }
}