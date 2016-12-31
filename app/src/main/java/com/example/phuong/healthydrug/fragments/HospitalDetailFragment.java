package com.example.phuong.healthydrug.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.widget.TextView;
import android.widget.Toast;

import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.example.phuong.healthydrug.R;
import com.example.phuong.healthydrug.models.Hospital;
import com.example.phuong.healthydrug.utils.TrackGPS;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by phuong on 29/12/2016.
 */
@EFragment(R.layout.detail_item_hospital_fragment)
public class HospitalDetailFragment extends BaseFragment implements OnMapReadyCallback, RoutingListener {
    private static final int[] COLORS = new int[]{R.color.primary_dark, R.color.primary, R.color.primary_light, R.color.accent, R.color.primary_dark_material_light};
    @FragmentArg
    public long idHospital;
    double longitude;
    double latitude;
    @ViewById(R.id.tvNameHospital)
    TextView mNameHospital;
    @ViewById(R.id.tvPhoneHospital)
    TextView mPhoneHospital;
    @ViewById(R.id.tvWebsiteHospital)
    TextView mWebsiteHospital;
    private GoogleMap map;
    private Marker marker;
    private String address;
    private LatLng myLatLng;
    private Hospital mHospital;
    private TrackGPS gps;

    @Override
    public void inits() {
        initData();
        SupportMapFragment mSupportMapFragment = SupportMapFragment.newInstance(null);
        FragmentManager fm = getChildFragmentManager();
        fm.beginTransaction().replace(R.id.map1, mSupportMapFragment).commit();
        mSupportMapFragment.getMapAsync(this);

        gps = new TrackGPS(getActivity());
        if (gps.canGetLocation()) {
            longitude = gps.getLongitude();
            latitude = gps.getLatitude();
        } else {
            gps.showSettingsAlert();
        }
    }

    public void initData() {
        //mHospital = Hospital.findById(Hospital.class, idHospital);
        //address = mHospital.getName() + mHospital.getAddress();
        mNameHospital.setText("Bạch Mai");
        mPhoneHospital.setText("78 Đường Giải Phóng, Phương Mai, Đống Đa, Hà Nội");
        mWebsiteHospital.setText("bachmaihospital.org");
        address = "54 Nguyễn Lương Bằng, Liên Chiểu, Đà Nẵng";
//        Log.d("tag", mHospital.toString());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.getUiSettings().setZoomControlsEnabled(true);
        map.animateCamera(CameraUpdateFactory.zoomIn());
        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1000);
        } else {
            drawDirection();
        }
    }

    @Override
    public void onRoutingFailure(RouteException e) {

    }

    @Override
    public void onRoutingStart() {

    }

    @Override
    public void onRoutingSuccess(ArrayList<Route> route, int shortestRouteIndex) {
        List<Polyline> polylines = new ArrayList<>();
        for (int i = 0; i < route.size(); i++) {
            int colorIndex = i % COLORS.length;

            PolylineOptions polyOptions = new PolylineOptions();
            polyOptions.color(getResources().getColor(COLORS[colorIndex]));
            polyOptions.width(10 + i * 3);
            polyOptions.addAll(route.get(i).getPoints());
            Polyline polyline = map.addPolyline(polyOptions);
            polylines.add(polyline);
            Toast.makeText(getActivity().getApplicationContext(), "Route " + (i + 1) + ": distance - " + route.get(i).getDistanceValue() + ": duration - " + route.get(i).getDurationValue(), Toast.LENGTH_SHORT).show();
        }

        // Start marker
        MarkerOptions options = new MarkerOptions();
        options.position(myLatLng);
        options.icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        map.addMarker(options);

        // End marker
        options = new MarkerOptions();
        options.position(getRoomLocation(address));
        options.icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_RED));
        map.addMarker(options);
    }

    @Override
    public void onRoutingCancelled() {

    }

    public void drawDirection() {
        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1000);
        } else {
            myLatLng = new LatLng(latitude, longitude);
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(myLatLng, 13));
            CameraPosition myPosition = new CameraPosition.Builder()
                    .target(myLatLng).zoom(15).tilt(30).build();
            if (marker != null) {
                marker.remove();
            }
            marker = map
                    .addMarker(new MarkerOptions()
                            .position(myLatLng)
                            .title(getResources().getString(R.string.message_you_are_here))
                            .icon(BitmapDescriptorFactory
                                    .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            map.animateCamera(CameraUpdateFactory
                    .newCameraPosition(myPosition));

            Routing routing = new Routing.Builder()
                    .travelMode(Routing.TravelMode.WALKING)
                    .withListener(this)
                    .waypoints(myLatLng, getRoomLocation(address))
                    .build();
            routing.execute();
        }
    }

    public LatLng getRoomLocation(String address) {
        if (address != null) {
            Geocoder geocoder = new Geocoder(getContext());
            List<Address> addresses = null;
            try {
                addresses = geocoder.getFromLocationName(address, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (addresses != null && addresses.size() > 0) {
                return new LatLng(addresses.get(0).getLatitude(), addresses.get(0).getLongitude());
            } else {
                Toast.makeText(getActivity().getApplication(), getResources().getString(R.string.message_not_found_address), Toast.LENGTH_SHORT).show();
            }
        }
        return null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1000: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    drawDirection();
                } else {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1000);
                }
                return;
            }
        }
    }
}
