package com.example.bluetooth_b10809038;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailFragment extends Fragment {

    private  BLUDevice device ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_detail, container, false);
        Bundle bundle = getArguments();

        if(bundle != null)
        {
            String address = DetailFragmentArgs.fromBundle(getArguments()).getAddress();
            String rssi = DetailFragmentArgs.fromBundle(getArguments()).getRssi();
            String timestampNanos = DetailFragmentArgs.fromBundle(getArguments()).getTimestampNanos();
            String content = DetailFragmentArgs.fromBundle(getArguments()).getContent();
            TextView a1 = rootView.findViewById(R.id.textView_Address);
            TextView a2 = rootView.findViewById(R.id.textView_Rssi);
            TextView a3 = rootView.findViewById(R.id.textView_Timestamp);
            TextView a4 = rootView.findViewById(R.id.textView_Content);
            a1.setText(address);
            a2.setText(rssi);
            a3.setText(timestampNanos);
            a4.setText(content);
        }
        return rootView;
    }


}