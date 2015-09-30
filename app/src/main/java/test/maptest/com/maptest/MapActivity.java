package test.maptest.com.maptest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by nagesh on 25-Sep-15.
 */
public class MapActivity extends AppCompatActivity implements OnApiProcessListener, OnMapReadyCallback,OnLocationSelectedListener {

    public static final String BUS_LOCATION_DATA = "bus_location_data";

    //TODO display map and the list here

    private MapTestApplication application;
    private ArrayList<BusLocation> locations;
    private MapFragment mapFragment;
    private HashMap<Marker, BusLocation> haspMapLocation = new HashMap<Marker, BusLocation>();
    private RecyclerView locationsRecyclerView;
    private LocationListAdapter mLocationAdapter;
    private SlidingUpPanelLayout slidingUpPaneLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initialize application
        application = (MapTestApplication) getApplication();
        setContentView(R.layout.activity_map);
        slidingUpPaneLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        slidingUpPaneLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeMap();
        initializeRecyclerView();
        initializeDataFetch();
    }


    private void initializeDataFetch() {
        String responseData = "";

        //Getting Data from the data received from previous screen
        if (getIntent() != null && getIntent().getExtras() != null) {
            responseData = getIntent().getExtras().getString(BUS_LOCATION_DATA);
            if (responseData != null && responseData.length() > 0) {
                DataGrabber mDataGrabber = new DataGrabber(responseData, this);
            }
        } else {
            application.showToast(getResources().getString(R.string.display_error));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        if (slidingUpPaneLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED)
            slidingUpPaneLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        else if (slidingUpPaneLayout.getPanelState() == SlidingUpPanelLayout.PanelState.COLLAPSED)
            slidingUpPaneLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
        else {
            super.onBackPressed();
        }
    }

    //Handling Marker onclick.
    private void initializeMap() {
        mapFragment =
                (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMap().setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                slidingUpPaneLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
            }
        });
        mapFragment.getMap().setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                slidingUpPaneLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                //Getting Location from the mapped marker and notifying Recycler View
                BusLocation mLocation = haspMapLocation.get(marker);
                setInfo(mLocation);
                ArrayList<BusLocation> filteredLocations
                        = (ArrayList<BusLocation>) locations.clone();
                filteredLocations.remove(mLocation);
                notifyRecyclerView(filteredLocations);
                return false;
            }
        });
    }

    private void moveToCurrentCity(GoogleMap googleMap) {
        // Enable MyLocation Layer of Google Map
        googleMap.setMyLocationEnabled(true);
        LatLng latLng = new LatLng(12.9667, 77.5667);
        // Show the current city , assuming current city is Bangalore in Google Map
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        // Zoom in the Google Map
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10));
    }

    //Initializing RecyclerView and assigning Layout Manager
    private void initializeRecyclerView() {
        locationsRecyclerView = (RecyclerView) findViewById(R.id.location_recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        locationsRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        locationsRecyclerView.setLayoutManager(mLayoutManager);
    }

    //Set Information for the cureent selected Location
    private void setInfo(BusLocation busLocation) {
        TextView txtLocationName = (TextView) findViewById(R.id.location_name);
        TextView txtBusCount = (TextView) findViewById(R.id.location_bus_count);
        TextView txtEarliestBus = (TextView) findViewById(R.id.location_earliest_bus);
        TextView txtTimeCar = (TextView) findViewById(R.id.location_timecar);

        txtLocationName.setText(getResources().getString(R.string.location_name) + busLocation.getBpLocName());
        txtBusCount.setText(getResources().getString(R.string.bus_available) + busLocation.getBusCount());
        txtEarliestBus.setText(getResources().getString(R.string.earliest_bus) + busLocation.getEarliestBus());
        txtTimeCar.setText(getResources().getString(R.string.time_taken) + busLocation.getTimeByCar());

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locations.clear();
        haspMapLocation.clear();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onSuccess(ArrayList<BusLocation> locations) {
        //Populate map and locations as marker in the Map
        this.locations = locations;
        mapFragment.getMapAsync(this);

    }

    private void notifyRecyclerView(ArrayList<BusLocation> locations) {
        //creating Adapter or updating with current data
        if (mLocationAdapter != null) {
            mLocationAdapter.updateDataset(locations);
        } else {
            mLocationAdapter = new LocationListAdapter(locations,this);
            locationsRecyclerView.setAdapter(mLocationAdapter);
        }
    }

    @Override
    public void onError() {
        //Notify User with Error Message
        application.showToast(getResources().getString(R.string.display_error));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        moveToCurrentCity(mapFragment.getMap());
        //adding Marker with fetched Location
        for (int i = 0; i < locations.size(); i++) {
            BusLocation mLocation = locations.get(i);
            Marker marker = googleMap.addMarker(new MarkerOptions().position(
                    new LatLng(mLocation.getBpLocLatitude(), mLocation.getBpLocLongitude())).
                    title(mLocation.getBpLocName()));

            //Putting in Hashmap for future use
            haspMapLocation.put(marker, locations.get(i));
            //haspMapLocation.put(marker, locations.get(i));
        }

    }

    @Override
    public void onLocationSelect(BusLocation mBusLocation) {
        Marker mMarker = getMarkerFromloc(mBusLocation);
        mMarker.showInfoWindow();
        mapFragment.getMap().moveCamera(CameraUpdateFactory.newLatLng(mMarker.getPosition()));
        slidingUpPaneLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        setInfo(mBusLocation);
        ArrayList<BusLocation> filteredLocations
                = (ArrayList<BusLocation>) locations.clone();
        filteredLocations.remove(mBusLocation);
        notifyRecyclerView(filteredLocations);

    }

    private Marker getMarkerFromloc(BusLocation value) {
        for (Marker o : haspMapLocation.keySet()) {
            if (haspMapLocation.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }
}
