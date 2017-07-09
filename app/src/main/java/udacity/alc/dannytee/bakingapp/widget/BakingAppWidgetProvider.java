package udacity.alc.dannytee.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import udacity.alc.dannytee.bakingapp.LastIngredientPreference;
import udacity.alc.dannytee.bakingapp.R;
import udacity.alc.dannytee.bakingapp.activities.RecipeDetailsActivity;
import udacity.alc.dannytee.bakingapp.activities.RecipesActivity;

/**
 * Created by dannytee on 25/06/2017.
 */

public class BakingAppWidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them


        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }


        static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
        int appWidgetId) {

            CharSequence widgetText = LastIngredientPreference.getLastSelectedIngredient(context);


            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_app_widget_provider);
            views.setTextViewText(R.id.appwidget_text, widgetText);

            Intent titleIntent = new Intent(context, RecipesActivity.class);
            PendingIntent titlePendingIntent = PendingIntent.getActivity(context, 0, titleIntent, 0);
            views.setOnClickPendingIntent(R.id.appwidget_text, titlePendingIntent);

//
            appWidgetManager.updateAppWidget(appWidgetId, views);

        }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        final String action = intent.getAction();
        if (action.equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)) {
            AppWidgetManager mgr = AppWidgetManager.getInstance(context);
            ComponentName cn = new ComponentName(context, BakingAppWidgetProvider.class);
            mgr.notifyAppWidgetViewDataChanged(mgr.getAppWidgetIds(cn), R.id.appwidget_text);
        }
        super.onReceive(context, intent);
    }

}
