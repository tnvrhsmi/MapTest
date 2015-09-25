package test.maptest.com.maptest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    /*TODO Project description
        1. Take the sample data given, And display them in a map as markers.

        2. Also list all the data in a list as overlay on the map (HINT: USE SLIDE UP PANEL:) )

        3. And when the marker is tapped , give some visual indication on the list item that is associated with the Marker (Basically you need to map/couple markers
        and List items well so that that are interlinked.)

        4. Best practices will be rewarded :)
     */
private String sampleData = "[{\"bpLocName\":\"Kalasipalayam\",\"bpLocLatitude\":\"12.967367\",\"bpLocLongitude\":\"77.588865\",\"earliestBus\":34,\n \"BusCount\":7,\"timeByCar\":12,\"score\":4,\"color\":\"GREEN\"},\n {\"bpLocName\":\"Jayanagar\",\"bpLocLatitude\":\"12.467367\",\"bpLocLongitude\":\"77.788865\"\n ,\"earliestBus\":14,\"BusCount\":12,\"timeByCar\":6,\"score\":9,\"color\":\"ORANGE\"},\n {\"bpLocName\":\"Electronic City\",\"bpLocLatitude\":\"12.848498\"\n ,\"bpLocLongitude\":\"77.67055\",\"earliestBus\":12,\"BusCount\":10,\"timeByCar\":3,\"score\":5,\"color\":\"GREY\"},\n {\"bpLocName\":\"Anand Rao Circle\",\"bpLocLatitude\":\"12.980701\",\"bpLocLongitude\":\"77.574677\",\"earliestBus\":12,\"BusCount\":10,\"timeByCar\":3,\n \"score\":5,\"color\":\"GREY\"},\n {\"bpLocName\":\"Majestic\",\"bpLocLatitude\":\"12.978464\",\"bpLocLongitude\":\"77.573175\",\"earliestBus\":12,\"BusCount\":10,\"timeByCar\":3,\n \"score\":5,\"color\":\"GREY\"},\n {\"bpLocName\":\"Silk Board\",\"bpLocLatitude\":\"12.917516\",\"bpLocLongitude\":\"77.623472\",\"earliestBus\":12,\n \"BusCount\":10,\"timeByCar\":3,\"score\":5,\"color\":\"GREY\"},\n {\"bpLocName\":\"KR Puram\",\"bpLocLatitude\":\"12.999548\",\n \"bpLocLongitude\":\"77.675088\",\"earliestBus\":12,\"BusCount\":10,\"timeByCar\":3,\"score\":5,\"color\":\"GREY\"},\n {\"bpLocName\":\"Marathahalli\",\"bpLocLatitude\":\"12.955379\",\"bpLocLongitude\":\"77.700419\",\"earliestBus\":12,\"BusCount\":10,\"timeByCar\":3,\n \"score\":5,\"color\":\"GREY\"},\n {\"bpLocName\":\"Hebbal\",\"bpLocLatitude\":\"13.04524\",\"bpLocLongitude\":\"77.590678\",\"earliestBus\":11,\n \"BusCount\":10,\"timeByCar\":3,\"score\":4,\"color\":\"GREY\"},\n {\"bpLocName\":\"INDRANAGAR\",\"bpLocLatitude\":\"12.986809\",\n \"bpLocLongitude\":\"77.647728\",\"earliestBus\":7,\"BusCount\":17,\"timeByCar\":4,\"score\":4,\"color\":\"GREY\"}]";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v){

        switch (v.getId()){
            case R.id.show_map:

                Intent intent = new Intent(MainActivity.this, MapActitivity.class);
                startActivity(intent);
                break;
        }

    }
}
