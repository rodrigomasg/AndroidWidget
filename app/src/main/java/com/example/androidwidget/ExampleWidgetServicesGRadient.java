package com.example.androidwidget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

public class ExampleWidgetServicesGRadient extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ExampleWdgetItemFactory(getApplicationContext(), intent);
    }

    class ExampleWdgetItemFactory implements RemoteViewsFactory {
        private Context context;
        private int appWidgetId;
        private String[] exampleData = {
                "Uno", "dos", "tres", "cuatro", "cinco", "seais", "siete", "ocho", "nueve", "dies"};

        ExampleWdgetItemFactory(Context context, Intent intent) {
            this.context = context;
            this.appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }



        @Override
        public void onCreate() {
//            connect to data source

        }

        @Override
        public void onDataSetChanged() {

        }

        @Override
        public void onDestroy() {
//            close conectuion with data source

        }

        @Override
        public int getCount() {
            return exampleData.length;
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.example_widget_gradient);
            views.setTextViewText(R.id.item_widget_stack, exampleData[position]);
            SystemClock.sleep(500);
            return views;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
