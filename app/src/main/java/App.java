/**
 * Created by Mothupii_ on 2017-10-17.
 */

import android.app.Application;
import android.content.Context;
import android.net.ParseException;
import android.os.Build;
import android.service.autofill.SaveCallback;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.mothupii_.ilocatefriends.ActiveConnection;
import com.example.mothupii_.ilocatefriends.Parse;
import com.example.mothupii_.ilocatefriends.ParseObject;
import com.example.mothupii_.ilocatefriends.ParsePush;

/**
 * App - Called before any Activity
 */
public class App extends Application {

    private static Context context;
    private static String ACTIVITY = "App";
    //private  e;

    /**
     * Initializer
     */
    public App() {
        context = this;
    }

    /**
     * onCreate()
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(ACTIVITY, "onCreate()");
        {

       /** ParseObject.registerSubclass(FriendInvitation.class);
        ParseObject.registerSubclass(Friends.class);
        ParseObject.registerSubclass(ActiveConnection.class);
        Parse.initialize( "---application id---", "---client key---");


        ParsePush.subscribeInBackground("", new SaveCallback()
        {
           // @Override
            public void done(ParseException e)
            {
                if (e == null)
                {
                    Log.d(ACTIVITY, "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e(ACTIVITY, "failed to subscribe for push: " + e.getLocalizedMessage());
                }
            }
        });

    }

    /**
     * getAppContext()
     *
     * @return Context
     */
   // public static Context getAppContext();
    {
       // return App.context;
    }

   // private class FriendInvitation {
    }
}
}


