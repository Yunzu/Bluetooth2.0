package com.example.bluetooth_b10809038;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ScannerFragment extends Fragment {
    private RecyclerView rview;
    private Button scannerbutton;
    private boolean scanning = false;
    private BluetoothObject bluetoothObject;
    private View view;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bluetoothObject = new BluetoothObject(getActivity());
        bluetoothObject.setupPermissions();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_scanner, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rview = (RecyclerView) getActivity().findViewById(R.id.result_recycleview);//connect view
        bluetoothObject.mResultAdapter = new DeviceAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rview.setLayoutManager(layoutManager);// set layout view
        rview.setHasFixedSize(true);
        rview.setAdapter(bluetoothObject.mResultAdapter);

        scannerbutton = (Button) getActivity().findViewById(R.id.scanner_button);
        scannerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bluetoothObject.BluetoothStartScan(scanning);
                if (scanning == false) {
                    scanning = true;
                    scannerbutton.setText("STOP");
                } else {
                    scanning = false;
                    scannerbutton.setText("SCAN");
                }
            }
        });
    }
}









