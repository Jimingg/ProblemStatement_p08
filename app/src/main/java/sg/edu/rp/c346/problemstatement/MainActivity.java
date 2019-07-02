package sg.edu.rp.c346.problemstatement;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
public class MainActivity extends AppCompatActivity {

    LatLng East,Central,North;
    Marker n,c,e;
    Spinner spinnerL;
    private GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        spinnerL = findViewById(R.id.spinnerlocation);
        spinnerL.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        if (map != null) {
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(North, 18));
                        }
                        break;
                    case 1:
                        if (map != null) {
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(Central,
                                    18));
                        }
                        break;
                    case 2:
                        if (map != null) {
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(East,
                                    18));
                        }
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }

                 North = new LatLng(1.424450, 103.829849);
                 n = map.addMarker(new
                        MarkerOptions()
                        .position(North)
                        .title("North - HQ")
                        .snippet("Block 333, Admiralty Ave 3, 765654 Operating hours: 10am-5pm Tel:65433456")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_account_balance_pink_a200_48dp)));

                 Central = new LatLng(1.297520, 103.846320);
                 c = map.addMarker(new
                        MarkerOptions()
                        .position(Central)
                        .title("Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542 Operating hours: 11am-8pm Tel:67788652")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                 East = new LatLng(1.349860, 103.934190);
                e = map.addMarker(new
                        MarkerOptions()
                        .position(East)
                        .title("East")
                        .snippet("Block 555, Tampines Ave 3, 287788 Operating hours: 9am-5pm Tel:66776677")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {

                        Toast.makeText(getApplicationContext(),marker.getTitle(),
                                Toast.LENGTH_SHORT).show();
                        return false;



                    }
                });
                UiSettings ui = map.getUiSettings();
                ui.setCompassEnabled(true);
                ui.setZoomControlsEnabled(true);


            }
        });




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // show menu when menu button is pressed
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.normal:
                if (map != null) {
                    map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
                break;
            case R.id.terrain:
                if (map != null){
                    map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                }
                break;
            case R.id.satellite:
                if (map != null){
                    map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                }
                break;
        }
        return true;
    }
}
