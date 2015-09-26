package test.maptest.com.maptest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by nagesh on 25-Sep-15.
 */
public class MapActivity extends Activity{

    //TODO display map and the list here


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(new TextView(getApplicationContext()));
        super.onCreate(savedInstanceState);
    }
}
