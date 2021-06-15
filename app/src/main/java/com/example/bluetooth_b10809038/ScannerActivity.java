package com.example.bluetooth_b10809038;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bluetooth_b10809038.database.Contract;
import com.example.bluetooth_b10809038.database.DBHelper;

public class ScannerActivity extends AppCompatActivity {
    private View fragment;
    private SQLiteDatabase mDb;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        DBHelper dbHelper = new DBHelper(this);
        mDb = dbHelper.getWritableDatabase();



        fragment = findViewById(R.id.nav_host_fragment);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        Toolbar toolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);


    }

//    private Cursor getAllGuests() {
//        return mDb.query(
//                Contract.bluetoothEntry.TABLE_NAME,
//                null,
//                null,
//                null,
//                null,
//                null,
//                Contract.bluetoothEntry.COLUMN_TIMESTAMP
//        );
//    }
//    public void addToDevicelist(String address, String rssi) {
//        addNewDevice(address,rssi);
//    }



//    public long addNewDevice(String address, String rssi) {
//        ContentValues cv = new ContentValues();
//        cv.put(Contract.bluetoothEntry.COLUMN_Address, address);
//        cv.put(Contract.bluetoothEntry.COLUMN_Rssi, rssi);
//        return mDb.insert(Contract.bluetoothEntry.TABLE_NAME, null, cv);
//    }
}