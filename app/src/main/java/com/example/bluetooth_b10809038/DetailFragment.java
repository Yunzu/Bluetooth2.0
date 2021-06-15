package com.example.bluetooth_b10809038;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bluetooth_b10809038.database.Contract;
import com.example.bluetooth_b10809038.database.DBHelper;

public class DetailFragment extends Fragment {

    private  BLUDevice device ;
    private SQLiteDatabase mDb;
    private Button savebutton;
    private TextView a1,a2,a3,a4,a5;
    ScannerActivity scannerActivity;
    String address;
    String rssi;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        DBHelper dbHelper = new DBHelper(getActivity());
        mDb = dbHelper.getWritableDatabase();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_detail, container, false);
        Bundle bundle = getArguments();

        if(bundle != null)
        {
            address = DetailFragmentArgs.fromBundle(getArguments()).getAddress();
            rssi = DetailFragmentArgs.fromBundle(getArguments()).getRssi();
            String timestampNanos = DetailFragmentArgs.fromBundle(getArguments()).getTimestampNanos();
            String content = DetailFragmentArgs.fromBundle(getArguments()).getContent();
            a1 = rootView.findViewById(R.id.textView_Address);
            a2 = rootView.findViewById(R.id.textView_Rssi);
            a3 = rootView.findViewById(R.id.textView_Timestamp);
            a4 = rootView.findViewById(R.id.textView_Content);
            a5 = rootView.findViewById(R.id.save_textView);
            a1.setText(address);
            a2.setText(rssi);
            a3.setText(timestampNanos);
            a4.setText(content);

        }

        return rootView;

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        savebutton = (Button)getActivity().findViewById(R.id.save_button);
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewDevice(address,rssi);
                a5.setText("Success saved!");
            }
        });
    }

    public long addNewDevice(String address, String rssi) {
        ContentValues cv = new ContentValues();
        cv.put(Contract.bluetoothEntry.COLUMN_Address,address);
        cv.put(Contract.bluetoothEntry.COLUMN_Rssi, rssi);
        return mDb.insert(Contract.bluetoothEntry.TABLE_NAME, null, cv);
    }



}