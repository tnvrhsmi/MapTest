package test.maptest.com.maptest;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by tanvihashimee on 29/09/15.
 */
public class DataGrabber extends AsyncTask<String,Void,ArrayList<BusLocation> > {
    private OnApiProcessListener onApiProcessListener;

    public DataGrabber(String responseData, OnApiProcessListener onApiProcessListener) {
        this.onApiProcessListener = onApiProcessListener;
        executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,responseData);

    }

    @Override
    protected ArrayList<BusLocation> doInBackground(String... params) {
        //Parsing Data.
        try{
        return parseData(params[0]);
        }
        catch(JSONException ex){
            return null;
        }

    }

    @Override
    protected void onPostExecute(ArrayList<BusLocation> busLocations) {
        super.onPostExecute(busLocations);

        if(busLocations != null)
            onApiProcessListener.onSuccess(busLocations);
        else
            onApiProcessListener.onError();
    }

    //Parsing Data and putting in the array of locations
    private ArrayList<BusLocation> parseData(String param) throws JSONException {
        JSONArray mArray = new JSONArray(param);
        ArrayList<BusLocation> busLocations = new ArrayList<BusLocation>();
        for (int i = 0; i <mArray.length(); i++) {
            JSONObject busLocationJSON = mArray.getJSONObject(i);
            BusLocation mBusLocation = new BusLocation();
            mBusLocation.setBpLocLatitude(busLocationJSON.getDouble("bpLocLatitude"));
            mBusLocation.setBpLocName(busLocationJSON.getString("bpLocName"));
            mBusLocation.setBusCount(busLocationJSON.getString("BusCount"));
            mBusLocation.setColor(busLocationJSON.getString("color"));
            mBusLocation.setEarliestBus(busLocationJSON.getString("earliestBus"));
            mBusLocation.setScore(busLocationJSON.getString("score"));
            mBusLocation.setTimeByCar(busLocationJSON.getString("timeByCar"));
            mBusLocation.setBpLocLongitude(busLocationJSON.getDouble("bpLocLongitude"));
            busLocations.add(mBusLocation);
        }
        return  busLocations;
    }
}
