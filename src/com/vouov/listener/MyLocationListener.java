package com.vouov.listener;

import android.location.Location;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import com.vouov.bean.MyGEO;

/**
 * 位置信息变化监听器
 * User: yuminglong
 * Date: 11-8-21
 * Time: 上午9:59
 * Version: 1.0.0
 */
public class MyLocationListener implements LocationListener {
    private static final String TAG = "MyLocationListener";
    private static final int CHECK_INTERVAL = 1000 * 60 * 2;
    private AsyncTask<Location, Integer, MyGEO> task;
    public static Location currentLocation;

    public MyLocationListener(AsyncTask<Location, Integer, MyGEO> task) {
        this.task = task;
    }

    public MyLocationListener(AsyncTask<Location, Integer, MyGEO> task, Location myLocation) {
        this.task = task;
        if(currentLocation==null){
            Log.d(TAG, "Set current location");
            currentLocation = myLocation;
        }
    }

    public void onLocationChanged(Location location) {
        Log.d(TAG, "Location changed, provider = "+location.getProvider()
                +",lng = "+location.getLongitude()
                +",lat = "+location.getLatitude());
        if (currentLocation != null) {
            if (isBetterLocation(location, currentLocation)) {
                Log.d(TAG, "Update location");
                currentLocation = location;
            }
        } else {
            Log.d(TAG, "The current is null, update location");
            currentLocation = location;
        }

        if (currentLocation == location) {
            //如果有任务没有结束，结束任务
            if(task != null && task.getStatus() != AsyncTask.Status.FINISHED){
                Log.d(TAG, "Cancel task");
                task.cancel(true);
            }
            if(task != null){
                Log.d(TAG, "Execute task");
                task.execute(currentLocation);
            }
        }

    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d(TAG, "Status changed: "+provider+", status = "+status);
    }

    public void onProviderEnabled(String provider) {
       Log.d(TAG, "Provider enabled: "+provider);
    }

    public void onProviderDisabled(String provider) {
       Log.d(TAG, "Provider disabled: "+provider);
    }

    /**
     * Determines whether one Location reading is better than the current Location fix
     *
     * @param location            The new Location that you want to evaluate
     * @param currentBestLocation The current Location fix, to which you want to compare the new one
     */
    protected boolean isBetterLocation(Location location, Location currentBestLocation) {
        if (currentBestLocation == null) {
            // A new location is always better than no location
            return true;
        }
        // Check whether the new location fix is newer or older
        long timeDelta = location.getTime() - currentBestLocation.getTime();
        boolean isSignificantlyNewer = timeDelta > CHECK_INTERVAL;
        boolean isSignificantlyOlder = timeDelta < -CHECK_INTERVAL;
        boolean isNewer = timeDelta > 0;
        // If it's been more than two minutes since the current location, use the new location
        // because the user has likely moved
        if (isSignificantlyNewer) {
            return true;
        // If the new location is more than two minutes older, it must be worse
        } else if (isSignificantlyOlder) {
            return false;
        }
        // Check whether the new location fix is more or less accurate
        int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
        boolean isLessAccurate = accuracyDelta > 0;
        boolean isMoreAccurate = accuracyDelta < 0;
        boolean isSignificantlyLessAccurate = accuracyDelta > 200;
        // Check if the old and new location are from the same provider
        boolean isFromSameProvider = isSameProvider(location.getProvider(),
                currentBestLocation.getProvider());
        // Determine location quality using a combination of timeliness and accuracy
        if (isMoreAccurate) {
            return true;
        } else if (isNewer && !isLessAccurate) {
            return true;
        } else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
            return true;
        }
        return false;
    }

    /**
     * Checks whether two providers are the same
     */
    private boolean isSameProvider(String provider1, String provider2) {
        if (provider1 == null) {
            return provider2 == null;
        }
        return provider1.equals(provider2);
    }
}
