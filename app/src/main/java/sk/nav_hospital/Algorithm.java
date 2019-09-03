package sk.nav_hospital;

import android.util.Log;

class Algorithm {

    int NEAR_BY_DISTANCE = 3000; // IN METER

    private double EARTH_RADIOUS = 6378000; //IN METER CHANGE KM IF RESULT WANT IN KM

    Algorithm(){

        // CONSTRUCTOR
    }

    double Haversine(double usrlat, double usrlng, double hospitallat, double hospitallog){

        double lat1 = deg2radian(hospitallat - usrlat);
        double lon1 = deg2radian(hospitallog - usrlng);
        double initialLat = deg2radian(usrlat);
        double finalLat = deg2radian(usrlng);

        double a = Math.sin(lat1/2) * Math.sin(lat1/2) +
                Math.sin(lon1/2) * Math.sin(lon1/2) * Math.cos(initialLat) * Math.cos(finalLat);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        Log.i("distance", String.valueOf(EARTH_RADIOUS * c));

        return EARTH_RADIOUS * c;


    }
    private static double deg2radian(double deg) {
        return (deg * Math.PI / 180.0);
    }
}
