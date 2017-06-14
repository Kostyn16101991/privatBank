package ua.step.kostyn.retrofitfich.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.step.kostyn.retrofitfich.APINetworck;
import ua.step.kostyn.retrofitfich.RetrofitRoute;
import ua.step.kostyn.retrofitfich.RouteNetworck;
import ua.step.kostyn.retrofitfich.models.MapEventBusModel;
import ua.step.kostyn.retrofitfich.Networck;
import ua.step.kostyn.retrofitfich.R;
import ua.step.kostyn.retrofitfich.models.RequestRoutModel;

/**
 * Created by konstantin on 31.05.17.
 */

public class MapFragment extends BaseFragment implements OnMapReadyCallback, TextWatcher, LocationListener {
    @BindView(R.id.addressET)
    EditText addressET;

    private GoogleMap myMap;
    private int countLatter;
    private Marker marker;
    private Marker myLocation;
    private LocationManager locationManager;
    private Location location;
    private String latitude, longitude;
    private APINetworck retrofit;
    private int myLocationCount = 0;


    public static MapFragment newInstance() {
        return new MapFragment();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        retrofit = new RetrofitRoute().getRetrofitArea();
        return inflater.inflate(R.layout.map_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addressET.addTextChangedListener(this);
        //GEO POSITION////
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, 123);
        }
        try {
            System.out.println("getLastKnownLocation: lat " + location.getLatitude() + " getLastKnownLocation: lng" + location.getLongitude());
        } catch (Exception e) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        }
        location = locationManager.getLastKnownLocation(locationManager.PASSIVE_PROVIDER);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe
    public void fromEventBus(MapEventBusModel mapEventBusModel) {
        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
        for (int i = 0; i < mapEventBusModel.getList().size(); i++) {
            try {
                List<Address> adressList = geocoder.getFromLocationName(mapEventBusModel.getList().get(i).getAddress(), 5);
                if (adressList.size() > 0) {
                    System.out.println(mapEventBusModel.getList().get(i));
                    HashMap<String, String> route = new HashMap<>();
                    route.put("origin", latitude + "," + longitude);
                    route.put("destination", adressList.get(0).getLatitude() + "," + adressList.get(0).getLongitude());
                    route.put("key", "AIzaSyBnU4W98Ef_0dyRUuQ1Vfqy0PRndUH1Cxg");
                    Call<RequestRoutModel> call = retrofit.getRequestRoutModel(route);
                    call.enqueue(new Callback<RequestRoutModel>() {
                        @Override
                        public void onResponse(Call<RequestRoutModel> call, Response<RequestRoutModel> response) {
                            System.out.println(response.body().getRoutes());
                            for (int j = 0; j < response.body().getRoutes().get(0).getLegs().get(0).getSteps().size(); j++) {
                                PolygonOptions polygonOptions = new PolygonOptions()
                                        .add(new LatLng(response.body().getRoutes().get(0).getLegs().get(0).getSteps().get(j).getStartLocation().getLat(),
                                                        response.body().getRoutes().get(0).getLegs().get(0).getSteps().get(j).getStartLocation().getLng()),
                                                new LatLng(response.body().getRoutes().get(0).getLegs().get(0).getSteps().get(j).getEndLocation().getLat(),
                                                        response.body().getRoutes().get(0).getLegs().get(0).getSteps().get(j).getEndLocation().getLng()));
                                myMap.addPolygon(polygonOptions);
                            }
                        }

                        @Override
                        public void onFailure(Call<RequestRoutModel> call, Throwable t) {

                        }
                    });
                    marker = myMap.addMarker(new MarkerOptions()
                            .position(new LatLng(adressList.get(0).getLatitude(), adressList.get(0).getLongitude()))
                            .title("someTitle"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(mapEventBusModel);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        myMap = googleMap;
    }

    @OnClick(R.id.addressBtn)
    public void search() {
        if (marker != null) {
            myMap.clear();
        }
        new Networck().getAddressRate("", addressET.getText().toString());

    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//        countLatter++;
//        if (countLatter > 2){
//            new Networck().getAddressRate("", addressET.getText().toString());
//        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }


    @Override
    public void onLocationChanged(Location location) {
        latitude = String.valueOf(location.getLatitude());
        longitude = String.valueOf(location.getLongitude());
        if (myLocationCount >= 1) {
            myLocation.setPosition(new LatLng(location.getLatitude(), location.getLongitude()));
            myLocation.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        } else {
            myLocation = myMap.addMarker(new MarkerOptions()
                    .position(new LatLng(location.getLatitude(), location.getLongitude()))
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            myLocationCount++;
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
