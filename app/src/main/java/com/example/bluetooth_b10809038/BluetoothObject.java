package com.example.bluetooth_b10809038;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

public class BluetoothObject {
    public DeviceAdapter mResultAdapter = new DeviceAdapter();
    private BluetoothManager mBluetoothManager = null;
    private BluetoothAdapter mBluetoothAdapter = null;
    private BluetoothLeScanner mBluetoothLeScanner = null;
    private Activity mActivity;

    public BluetoothObject() {
    }

    public BluetoothObject(Activity a) {
        setmActivity(a);
    }
    public void setmActivity(Activity a) {
        this.mActivity = a;
    }

    private static final int PERMISSION_REQUEST_CODE = 5;
    private final static String[] permissionWeNeed = new String[]{
            Manifest.permission.BLUETOOTH_ADMIN,
            Manifest.permission.BLUETOOTH,
            Manifest.permission.ACCESS_FINE_LOCATION};


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setupPermissions() {
        boolean isGranted = true;
        for (String permission : permissionWeNeed) {
            isGranted &= (ActivityCompat.checkSelfPermission(this.mActivity, permission) == PackageManager.PERMISSION_GRANTED);
        }
        if (!isGranted) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mActivity.requestPermissions(permissionWeNeed, PERMISSION_REQUEST_CODE);
            } else {
                Toast.makeText(mActivity, "no permission", Toast.LENGTH_SHORT).show();
               mActivity.finishAndRemoveTask();
            }
        } else {
            initBluetooth();
        }
    }

    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean isGranted = grantResults.length > 0;
        for (int g : grantResults) {
            isGranted &= g == PackageManager.PERMISSION_GRANTED;
        }

        if (isGranted) {
            initBluetooth();
        } else {
            Toast.makeText(mActivity, "no permission", Toast.LENGTH_SHORT).show();
            mActivity.finishAndRemoveTask();
        }
    }

    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void initBluetooth() {
        boolean start = false;

        mBluetoothManager = (BluetoothManager) mActivity.getSystemService(Context.BLUETOOTH_SERVICE);
        if (mBluetoothManager != null) {
            mBluetoothAdapter = mBluetoothManager.getAdapter();
            if (mBluetoothAdapter != null) {

                mBluetoothLeScanner = mBluetoothAdapter.getBluetoothLeScanner();
                Toast.makeText(mActivity, "Bluetooth started", Toast.LENGTH_SHORT).show();
                start = true;
            }
        }
        if (start == false) {
            Toast.makeText(mActivity, "Cannot start bluetooth function", Toast.LENGTH_SHORT).show();
            mActivity.finishAndRemoveTask();
        }
    }

    public boolean BluetoothStartScan(boolean scanning){

        if (scanning == false) {
            mBluetoothLeScanner.startScan(startScanCallback);
            return true;
        } else {
            mBluetoothLeScanner.stopScan(startScanCallback);
            return false;
        }
    }

    public final ScanCallback startScanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            BluetoothDevice device = result.getDevice();
            ScanRecord mScanRecord = result.getScanRecord();
            String address = device.getAddress();
            byte[] content = mScanRecord.getBytes();
            int flag = mScanRecord.getAdvertiseFlags();
            int mRssi = result.getRssi();
            long timestampNanos = result.getTimestampNanos();
            String contentData = byteToString(content);
            if (address == null || address.trim().length() == 0) {
                return;
            }
            mResultAdapter.addDevice(address, ""+mRssi, ""+timestampNanos,""+contentData);
            mResultAdapter.notifyDataSetChanged();
        }
    };
    public static String byteToString(byte[] b) {
        int length = b.length;
        String data = new String();
        for (int i = 0; i < length; i++) {
            data += Integer.toHexString((b[i] >> 4) & 0xf);
            data += Integer.toHexString(b[i] & 0xf);
        }
        return data;
    }

}
