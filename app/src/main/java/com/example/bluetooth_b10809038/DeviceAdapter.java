package com.example.bluetooth_b10809038;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashMap;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.BLEDeviceViewHolder> {

    public static ArrayList<BLUDevice> list;
    public static ArrayList savelist;
    public static HashMap<String, BLUDevice> hashMap;
    private Button detailbutton;

    public DeviceAdapter() {
        list = new ArrayList<BLUDevice>();
        hashMap = new HashMap<String, BLUDevice>();
    }


    public void addDevice(String address, String mRssi, String timestampNanos, String content) {

        if (hashMap.containsKey(address)) {
            return;
        }
        BLUDevice device = new BLUDevice();
        device.deviceName = address;
        device.Rssi = mRssi;
        device.timestampNanos = timestampNanos;
        device.content = content;
        list.add(0, device);

    }

    public BLEDeviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layout = R.layout.list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean attach = false;
        View view = inflater.inflate(layout, parent, attach);//don't attach view right away
        BLEDeviceViewHolder viewHolder = new BLEDeviceViewHolder(view);
        return viewHolder;//attach view

    }

    @Override
    public void onBindViewHolder(@NonNull BLEDeviceViewHolder holder, int position) {
        Log.d("BLU", "bind" + position);
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class BLEDeviceViewHolder extends RecyclerView.ViewHolder {
        TextView vAdress;
        TextView vRssi;
        TextView vTimestamp;

        public BLEDeviceViewHolder(@NonNull View itemView) {
            super(itemView);
            vAdress = itemView.findViewById(R.id.textView_Address);
            vRssi = itemView.findViewById(R.id.textView_Rssi);
            vTimestamp = itemView.findViewById(R.id.textView_Timestamp);
            detailbutton = itemView.findViewById(R.id.detail_button);
        }
        void bind(int index){
            BLUDevice device = list.get(index);
            vAdress.setText("Address:"+device.deviceName);
            vRssi.setText("Rssi"+device.Rssi);
            vTimestamp.setText("TimestampNanos:"+device.timestampNanos);
            detailbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new DetailFragmentArgs.Builder().setAddress(device.deviceName).setRssi(device.Rssi).setTimestampNanos(device.timestampNanos).setContent(device.content).build().toBundle();

                    Navigation.findNavController(v).navigate(R.id.action_scannerFragment_to_detailFragment, bundle);
                }
            });
        }
    }
}