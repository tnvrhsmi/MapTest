package test.maptest.com.maptest;

import java.util.ArrayList;

/**
 * Created by tanvihashimee on 29/09/15.
 */
public interface OnApiProcessListener {
    void onSuccess(ArrayList<BusLocation> locations);
    void onError();
}
