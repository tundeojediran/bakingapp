package udacity.alc.dannytee.bakingapp.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by dannytee on 25/06/2017.
 */

public class BakingWidgetRemoteViewsService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new BakingWidgetRemoteViewsFactory(this.getApplicationContext());
    }
}
