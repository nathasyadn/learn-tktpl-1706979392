package id.ac.ui.cs.mobileprogramming.natasyameidiana.helloworld;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private WifiManager wifiManager;
    private WifiReceiver wifiReceiver;
    private ListView list;
    private List<String> wifiList;
    private Button buttonScan;
    private Button buttonAPI;

    private final int PERMISSION_ACCESS_FINE = 29;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.list_wifi);
        buttonScan = findViewById(R.id.scanBtn);
        buttonAPI = findViewById(R.id.APIButton);
        wifiReceiver = new WifiReceiver();
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (!wifiManager.isWifiEnabled()) {
            Toast.makeText(getApplicationContext(), "Turn On Your Wifi", Toast.LENGTH_LONG).show();
        }

        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_ACCESS_FINE);
                } else {
                    if (!wifiManager.isWifiEnabled()) {
                        Toast.makeText(getApplicationContext(), "Turn On Your Wifi", Toast.LENGTH_LONG).show();
                    } else {
                        list.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.list_view, R.id.label, new ArrayList<String>()));
                        Toast.makeText(MainActivity.this, "Scanning...", Toast.LENGTH_SHORT).show();
                        wifiManager.startScan();
                    }
                }
            }
        });

        buttonAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Sending to API", Toast.LENGTH_LONG).show();
                if (wifiList != null) {

                    if (wifiList.size() == 0) {
                        wifiList.add("Wifi not found");
                    }

                    StringBuilder result = new StringBuilder();
                    for (int i = 0; i < wifiList.size(); i++) {
                        result.append(wifiList.get(i));

                        if (i != wifiList.size() - 1) {
                            result.append(",");
                        }
                    }

                    new APICall(result.toString()).execute();
                }
            }
        });
    }

    @Override
    protected void onPause() {
        unregisterReceiver(wifiReceiver);
        super.onPause();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        getWifi();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ACCESS_FINE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                wifiManager.startScan();
            } else {
                Toast.makeText(MainActivity.this, "Permission Not Granted", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

    private void getWifi() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Toast.makeText(MainActivity.this, "Your version above marshmallow", Toast.LENGTH_SHORT).show();
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this, "Location off", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSION_ACCESS_FINE);
            } else {
                Toast.makeText(MainActivity.this, "Location on", Toast.LENGTH_SHORT).show();
                wifiManager.startScan();
            }
        } else {
            Toast.makeText(MainActivity.this, "Scanning Wifi", Toast.LENGTH_SHORT).show();
            wifiManager.startScan();
        }
    }

    class WifiReceiver extends BroadcastReceiver {
        public void onReceive(Context c, Intent intent) {
            String action = intent.getAction();
            if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION.equals(action)) {
                List<ScanResult> wifiScanList = wifiManager.getScanResults();
                wifiList = new ArrayList<>();

                /* Add List Wifi to Array*/
                for (int i = 0; i < wifiScanList.size(); i++) {
                    wifiList.add(wifiScanList.get(i).SSID);
                }

                if (wifiScanList.size() == 0) {
                    wifiList.add("Wifi not found");
                }

                list.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.list_view, R.id.label, wifiList));
            }
        }
    }
}