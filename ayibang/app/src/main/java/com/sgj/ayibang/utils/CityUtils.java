package com.sgj.ayibang.utils;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.Preference;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by John on 2016/3/29.
 */
public class CityUtils {

    public CityUtils(Context mContext) {
        this.mContext = mContext;
    }

    private static final String TAG = "CityUtils";

    LocationManager locationManager;
    Location mLocation;
    String serviceName = Context.LOCATION_SERVICE;
    String provider;

    Context mContext;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss.SSSZ");

    String city;

    public String getCity() {



        locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(
                mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, listener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);

        return city;
    }

    LocationListener listener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            if (isBetterLocation(location, mLocation)){
                    // 获取纬度
                    double lat = location.getLatitude();
                    // 获取经度
                    double lon = location.getLongitude();
                    // 位置提供者
                    String provider = location.getProvider();
                    // 位置的准确性
                    float accuracy = location.getAccuracy();
                    // 高度信息
                    double altitude = location.getAltitude();
                    // 方向角
                    float bearing = location.getBearing();
                    // 速度 米/秒
                    float speed = location.getSpeed();

                    String locationTime = sdf.format(new Date(location.getTime()));
                    String currentTime = null;

                    if (mLocation != null)
                    {
                        currentTime = sdf.format(new Date(mLocation.getTime()));
                        mLocation = location;

                    }
                    else
                    {
                        mLocation = location;
                    }

                    // 获取当前详细地址
                    StringBuffer sb = new StringBuffer();
                    if (mLocation != null)
                    {
                        Geocoder gc = new Geocoder(mContext);
                        List<Address> addresses = null;
                        try
                        {
                            addresses = gc.getFromLocation(mLocation.getLatitude(), mLocation.getLongitude(), 1);
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }

                        if (addresses != null && addresses.size() > 0)
                        {
                            Address address = addresses.get(0);
                            sb.append(address.getCountryName() + address.getLocality());
                            sb.append(address.getSubThoroughfare());

                            city = address.getLocality();

                            Log.d(TAG, " CountryName : " + address.getCountryName() +
                                    " Locality : " + address.getLocality() +
                                    " SubThoroughfare : " + address.getSubThoroughfare());

                            SharedPreferences preferences = mContext.getSharedPreferences("city", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("Country", address.getCountryName());
                            editor.putString("City", address.getLocality());
                            editor.putString("SubThoroughfare", address.getSubThoroughfare());
                            editor.putString("CountryCode", address.getCountryCode());
                            editor.commit();

                        }
                    }

                Log.d(TAG, "经度：" + lon + "\n纬度：" + lat + "\n服务商：" + provider + "\n准确性：" + accuracy + "\n高度：" + altitude + "\n方向角：" + bearing
                        + "\n速度：" + speed + "\n上次上报时间：" + currentTime + "\n最新上报时间：" + locationTime + "\n您所在的城市：" + sb.toString());

                }

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };


    private static final int TWO_MINUTES = 1000 * 1 * 2;

    protected boolean isBetterLocation(Location location, Location currentBestLocation)
    {
        if (currentBestLocation == null)
        {
            // A new location is always better than no location
            return true;
        }

        // Check whether the new location fix is newer or older
        long timeDelta = location.getTime() - currentBestLocation.getTime();
        boolean isSignificantlyNewer = timeDelta > TWO_MINUTES;
        boolean isSignificantlyOlder = timeDelta < -TWO_MINUTES;
        boolean isNewer = timeDelta > 0;

        // If it's been more than two minutes since the current location, use
        // the new location
        // because the user has likely moved
        if (isSignificantlyNewer)
        {
            return true;
            // If the new location is more than two minutes older, it must be
            // worse
        }
        else if (isSignificantlyOlder)
        {
            return false;
        }

        // Check whether the new location fix is more or less accurate
        int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
        boolean isLessAccurate = accuracyDelta > 0;
        boolean isMoreAccurate = accuracyDelta < 0;
        boolean isSignificantlyLessAccurate = accuracyDelta > 200;

        // Check if the old and new location are from the same provider
        boolean isFromSameProvider = isSameProvider(location.getProvider(), currentBestLocation.getProvider());

        // Determine location quality using a combination of timeliness and
        // accuracy
        if (isMoreAccurate)
        {
            return true;
        }
        else if (isNewer && !isLessAccurate)
        {
            return true;
        }
        else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider)
        {
            return true;
        }
        return false;
    }

    /** Checks whether two providers are the same */
    private boolean isSameProvider(String provider1, String provider2)
    {
        if (provider1 == null)
        {
            return provider2 == null;
        }
        return provider1.equals(provider2);
    }

}
