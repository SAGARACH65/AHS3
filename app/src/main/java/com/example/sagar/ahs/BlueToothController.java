package com.example.sagar.ahs;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.ParcelUuid;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;

/**
 * Created by sagar on 1/28/2017.
 */

public class BlueToothController {

    private OutputStream outputStream;
    private InputStream inStream;

    public void pair() {
        BluetoothAdapter blueAdapter = BluetoothAdapter.getDefaultAdapter();


    }
}
