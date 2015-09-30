package test.maptest.com.maptest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tanvihashimee on 30/09/15.
 */
public class LocationListAdapter extends RecyclerView.Adapter<LocationListAdapter.ViewHolder> implements View.OnClickListener {
    private final OnLocationSelectedListener mOnLocationSelectedListener;
    private ArrayList<BusLocation> locations;


    public LocationListAdapter(ArrayList<BusLocation> locations, OnLocationSelectedListener mOnLocationSelectedListener
    ) {
        this.locations = locations;
        this.mOnLocationSelectedListener = mOnLocationSelectedListener;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mLocationName;
        public TextView mAvailableBus;
        public View mainView;

        // each data item is just a string in this case
        public ViewHolder(View mainView) {
            super(mainView);
            this.mainView = mainView;
            mLocationName = (TextView) mainView.findViewById(R.id.location_name);
            mAvailableBus = (TextView) mainView.findViewById(R.id.location_bus_count);
        }
    }


    // Create new views (invoked by the layout manager)
    @Override
    public LocationListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        // create a new view
        View mainView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.inflate_item_location, parent, false);

        // set the view's size, margins, paddings and layout parameters
        ViewHolder viewHolder = new ViewHolder(mainView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mLocationName.setText(holder.mLocationName.getContext().
                getResources().getString(R.string.location_name) +
                locations.get(position).getBpLocName());
        holder.mAvailableBus.setText(holder.mLocationName.getContext().
                getResources().getString(R.string.bus_available) +
                locations.get(position).getBusCount());

        holder.mainView.setOnClickListener(this);
        holder.mainView.setTag(locations.get(position));
    }

    // Return the size of your dataset
    @Override
    public int getItemCount() {
        return locations.size();
    }

    public void updateDataset(ArrayList<BusLocation> locations) {
        this.locations = locations;
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        BusLocation mBusLocation = (BusLocation) view.getTag();
        if (mOnLocationSelectedListener != null)
            mOnLocationSelectedListener.onLocationSelect(mBusLocation);
    }
}