package com.example.bluetooth_b10809038;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.inthecheesefactory.thecheeselibrary.fragment.support.v4.app.StatedFragment;

import java.util.List;

public class ScannerFragment extends Fragment {
    private RecyclerView rview;
    private Button scannerbutton;
    private boolean scanning = false;
    private BluetoothObject bluetoothObject;
    private View view;
//    private Parcelable list;
    LinearLayoutManager layoutManager;
//    private static Bundle mSavedInstanceState;
    private final String KEY_RECYCLER_STATE = "state";
    Bundle savedState;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bluetoothObject = new BluetoothObject(getActivity());
        bluetoothObject.setupPermissions();

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        if (savedInstanceState != null) {
//            restoreStateFromArguments();
//            Parcelable savedRecyclerLayoutState = savedInstanceState.getParcelable(KEY_RECYCLER_STATE);
//            rview.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
//        }
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
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
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


    //    @Override
//    protected void onSaveState(Bundle outState) {
//        super.onSaveState(outState);
//        outState.putParcelable(KEY_RECYCLER_STATE,rview.getLayoutManager().onSaveInstanceState());
//
//    }
//
//    @Override
//    protected void onRestoreState(Bundle savedInstanceState) {
//        super.onRestoreState(savedInstanceState);
//        if(savedInstanceState != null)
//        {
//            Parcelable savedRecyclerLayoutState = savedInstanceState.getParcelable(KEY_RECYCLER_STATE);
//            rview.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
//        }
//    }




//
//
//    @Override
//    public void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onViewStateRestored(savedInstanceState);
//        if (savedInstanceState != null){
//            list = savedInstanceState.getParcelable("1");
//    }
//    }
//

//    @Override
//    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
//        super.onViewStateRestored(savedInstanceState);
//
//        if(savedInstanceState != null)
//        {
//            Parcelable savedRecyclerLayoutState = savedInstanceState.getParcelable(KEY_RECYCLER_STATE);
//            rview.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
//        }
//    }
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        saveStateToArguments();
////        outState.putParcelable(KEY_RECYCLER_STATE,rview.getLayoutManager().onSaveInstanceState());
//    }
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        saveStateToArguments();
//    }
//    private void saveStateToArguments() {
//
//        savedState = saveState();
//        if (savedState != null) {
//            Bundle b = getArguments();
//            b.putParcelable(KEY_RECYCLER_STATE,savedState);
//        }
//    }
//    private boolean restoreStateFromArguments() {
//        Bundle b = getArguments();
//        savedState = b.getParcelable(KEY_RECYCLER_STATE);
//        if (savedState != null) {
//            restoreState();
//            return true;
//        }
//        return false;
//    }
//    private void restoreState() {
//        if (savedState != null) {
//            Parcelable savedRecyclerLayoutState = savedState.getParcelable(KEY_RECYCLER_STATE);
//            rview.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
//        }
//    }
//    private Bundle saveState() {
//        Bundle state = new Bundle();
//        state.putParcelable(KEY_RECYCLER_STATE,rview.getLayoutManager().onSaveInstanceState());
//        return state;
//    }
////    @Override
////    public void onResume() {
////        super.onResume();
////
////        if (list != null) {
////            layoutManager.onRestoreInstanceState(list);
////        }
////    }
}









