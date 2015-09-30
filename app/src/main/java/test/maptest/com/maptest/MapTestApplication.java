package test.maptest.com.maptest;

import android.app.Application;
import android.widget.Toast;

/**
 * Created by tanvihashimee on 29/09/15.
 */
public class MapTestApplication extends Application {

    public void showToast(String textToShow){
        Toast.makeText(getApplicationContext(),textToShow,Toast.LENGTH_LONG).show();
    }

}
