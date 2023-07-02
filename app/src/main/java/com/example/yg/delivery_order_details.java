package com.example.yg;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.snackbar.Snackbar;
import com.google.maps.android.PolyUtil;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.android.data.Point;
import com.google.maps.android.data.Style;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TravelMode;

import java.lang.ref.WeakReference;
import java.util.List;

public class delivery_order_details extends AppCompatActivity implements OnMapReadyCallback {

    final long UPDATE_INTERVAL_IN_MILLISECONDS = 1000;
    final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 500;
    private static final int DEFAULT_CAMERA_ZOOM = 11;
    private static final int CAMERA_ANIMATION_DURATION = 1000;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;
    private LocationRequest locationRequest;
    private GoogleMap googleMap;
    private LatLng currentLocation;
    LatLng origin, destination;
    MapView mapView;
    final NavigationLauncherLocationCallback callbackL =
            new NavigationLauncherLocationCallback(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_order_details);
        GPSUtils gpsUtils = new GPSUtils(this);
        gpsUtils.statusCheck();

        origin = new LatLng(34.0581903, -118.2383913);
        Bundle bundle = getIntent().getExtras();
//        double lat = Double.parseDouble(bundle.getString("lat"));
//        double lon = Double.parseDouble(bundle.getString("long"));
        double lat = Double.parseDouble("15.347199");
        double lon = Double.parseDouble("44.207431");
        destination = new LatLng(lat, lon);

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);


    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;
        initializeLocationEngine();
        animateCamera(destination);
        googleMap.addMarker(new MarkerOptions().position(destination).title("Citizen"));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(delivery_order_details.this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(delivery_order_details.this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                ActivityCompat.requestPermissions(delivery_order_details.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
            return;
        }
        googleMap.setMyLocationEnabled(true);
        fetchRoute();}

    public void fetchRoute() {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyBARkX5tfkam81w-FgEibeft1LdWGOFlBM")
                .build();
        try {
            DirectionsResult result = DirectionsApi.newRequest(context)
                    .mode(TravelMode.DRIVING)
                    .origin(new com.google.maps.model.LatLng(origin.latitude, origin.longitude))
                    .destination(new com.google.maps.model.LatLng(destination.latitude, destination.longitude))
                    .await();

            if (result.routes.length > 0) {
                String polyline = result.routes[0].overviewPolyline.getEncodedPath();
                List<LatLng> decodedPoints = PolyUtil.decode(polyline);
                googleMap.addPolyline(new PolylineOptions().addAll(decodedPoints));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
        fetchRoute();


    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        mapView.onStop();
        super.onStop();
    }

    private void initializeLocationEngine() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        locationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationCallback = new NavigationLauncherLocationCallback(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }



    } @Override
    protected void onDestroy() {

        if (fusedLocationClient != null && locationCallback != null) {
            fusedLocationClient.removeLocationUpdates(locationCallback);
            super.onDestroy();
        }
    }



    private static class NavigationLauncherLocationCallback extends LocationCallback {

        private final WeakReference<delivery_order_details> activityWeakReference;

        NavigationLauncherLocationCallback(delivery_order_details activity) {
            this.activityWeakReference = new WeakReference<>(activity);
        }



        @Override
        public void onLocationResult(@NonNull LocationResult locationResult) {
            delivery_order_details activity = activityWeakReference.get();
            if (activity != null) {
                Location location = locationResult.getLastLocation();
                activity.updateCurrentLocation(new LatLng(location.getLatitude(), location.getLongitude()));

            }
        }


    }

    void updateCurrentLocation(LatLng currentLocation) {
        this.currentLocation = currentLocation;
    }



    private void animateCamera(LatLng point) {
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(point, DEFAULT_CAMERA_ZOOM), CAMERA_ANIMATION_DURATION, null);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // تم منح الأذونات، يتم إعادة تحميل الخريطة
                mapView.getMapAsync(this);
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }


    }
