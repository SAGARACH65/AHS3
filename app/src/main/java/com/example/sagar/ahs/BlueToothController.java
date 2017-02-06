
package com.example.sagar.ahs;

import android.bluetooth.BluetoothSocket;
import android.os.Message;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.os.Handler;



public class BlueToothController {

   // private static final String TAG = "MY_APP_DEBUG_TAG";
    private Handler mHandler; // handler that gets info from Bluetooth service

    private interface MessageConstants {
        public static final int MESSAGE_READ = 0;


        // ... (Add other message types here as needed.)
    }


    //Handler mHandler = new Handler() ;
}
//        @Override
      /*  public void handleMessage(Message msg) {
            byte[] writeBuf = (byte[]) msg.obj;
            // construct a string from the valid bytes in the buffer
            String writeMessage = new String(writeBuf,0,msg.arg1);
            String seperateValues[] =  writeMessage.split(",");
            String pressure_1 = seperateValues[0];
            String pressure_2 = seperateValues[1];
            String pressure_3 = seperateValues[2];
            String pressure_4 = seperateValues[3];
            String pressure_5 = seperateValues[4];

            int pressure_1_int = Integer.parseInt(pressure_1);
            int pressure_2_int = Integer.parseInt(pressure_2);
            int pressure_3_int = Integer.parseInt(pressure_3);
            int pressure_4_int = Integer.parseInt(pressure_4);
            int pressure_5_int = Integer.parseInt(pressure_5);


        }
    };*/

/*
import android.bluetooth.BluetoothSocket;
import android.os.Message;
import android.util.Log;

import com.example.sagar.ahs.Addition1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static android.content.ContentValues.TAG;











// private static final String TAG = "MY_APP_DEBUG_TAG";
//private Handler mHandler; // handler that gets info from Bluetooth service
        BluetoothSocket socket = null;
        mAdapter = BluetoothAdapter.getDefaultAdapter();

        // Listen to the server socket if we're not connected
        // while (true) {

        try {
            // Create a new listening server socket
            Log.d((String) this.getTitle(), ".....Initializing RFCOMM SERVER....");

            // MY_UUID is the UUID you want to use for communication
            mmServerSocket = mAdapter.listenUsingRfcommWithServiceRecord("MyService",    MY_UUID);
            //mmServerSocket =  mAdapter.listenUsingInsecureRfcommWithServiceRecord(NAME, MY_UUID); // you can also try  using In Secure connection...

            // This is a blocking call and will only return on a
            // successful connection or an exception
            socket = mmServerSocket.accept();

        } catch (Exception e) {

        }

        byte[] buffer = new byte[256];  // buffer store for the stream
        int bytes; // bytes returned from read()
        try {
            Log.d((String) this.getTitle(), "Closing Server Socket.....");
            mmServerSocket.close();

            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the BluetoothSocket input and output streams

            //tmpIn = socket.getInputStream();
            //tmpOut = socket.getOutputStream();

            DataInputStream mmInStream = new DataInputStream(tmpIn);
            DataOutputStream mmOutStream = new DataOutputStream(tmpOut);
            // here you can use the Input Stream to take the string from the client  whoever is connecting
            //similarly use the output stream to send the data to the client

            // Read from the InputStream
            bytes = mmInStream.read(buffer);
            String readMessage = new String(buffer, 0, bytes);
            // Send the obtained bytes to the UI Activity

           // String seperateValues[] = readMessage.split(",");
            String pressure_1 = seperateValues[0];
            String pressure_2 = seperateValues[1];
            String pressure_3 = seperateValues[2];
            String pressure_4 = seperateValues[3];
            String pressure_5 = seperateValues[4];

            int pressure_1_int = Integer.parseInt(readMessage);
            //int pressure_2_int = Integer.parseInt(pressure_2);
            //int pressure_3_int = Integer.parseInt(pressure_3);
            //int pressure_4_int = Integer.parseInt(pressure_4);
            //int pressure_5_int = Integer.parseInt(pressure_5);
        TextView et1= (TextView) findViewById(R.id.textView31);
            et1.setText(pressure_1_int);

        } catch (Exception e) {
            //catch your exception here
        }
*/        // }