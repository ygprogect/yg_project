//package com.example.yg;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//
//import android.Manifest;
//import android.content.pm.PackageManager;
//import android.location.Location;
//import android.location.LocationListener;
//import android.os.Bundle;
//import android.widget.Toast;
//
//import com.google.android.gms.location.FusedLocationProviderClient;
//import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.MapView;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.Marker;
//import com.google.android.gms.maps.model.MarkerOptions;
//import com.google.android.gms.tasks.OnSuccessListener;
//
//public class map_screen extends AppCompatActivity implements OnMapReadyCallback, LocationListener {
//
//    private MapView mapView;
//    private GoogleMap googleMap;
//    private FusedLocationProviderClient fusedLocationClient;
//    private Location currentLocation;
//    private Marker currentLocationMarker;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_map_screen);
//
//        mapView = findViewById(R.id.mapView);
//        mapView.onCreate(savedInstanceState);
//
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
//
//        mapView.getMapAsync(this);
//    }
//
//    @Override
//    public void onMapReady(GoogleMap map) {
//        googleMap = map;
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // إذا لم تكن الأذونات ممنوحة، يتم طلبها
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
//            return;
//        }
//        googleMap.setMyLocationEnabled(true);
//        googleMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
//            @Override
//            public boolean onMyLocationButtonClick() {
//                if (currentLocation != null) {
//                    LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
//                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
//                }
//                return false;
//            }
//        });
//
//        fusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
//            @Override
//            public void onSuccess(Location location) {
//                if (location != null) {
//                    currentLocation = location;
//                    LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
//                    currentLocationMarker = googleMap.addMarker(new MarkerOptions().position(latLng).title("My Location"));
//                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
//                } else {
//                    Toast.makeText(map_screen.this, "Location not found", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        mapView.onResume();
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        mapView.onPause();
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        mapView.onDestroy();
//    }
//
//    @Override
//    public void onLowMemory() {
//        super.onLowMemory();
//        mapView.onLowMemory();
//    }
//
//    @Override
//    public void onLocationChanged(Location location) {
//        if (currentLocationMarker != null) {
//            currentLocationMarker.remove();
//        }
//        currentLocation = location;
//        LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
//        currentLocationMarker = googleMap.addMarker(new MarkerOptions().position(latLng).title("My Location"));
//        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == 100) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // تم منح الأذونات، يتم إعادة تحميل الخريطة
//                mapView.getMapAsync(this);
//            } else {
//                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//}