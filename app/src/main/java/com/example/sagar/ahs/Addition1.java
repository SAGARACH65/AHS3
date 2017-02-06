package com.example.sagar.ahs;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import static android.content.ContentValues.TAG;
import static java.lang.Integer.parseInt;

public class Addition1 extends AppCompatActivity {
    private final static int REQUEST_ENABLE_BT = 1;

    private boolean check = true;
    private String name;
    private String Area;
    private String M_unit;
    private String Crop_grown;
    private Bundle extras;
    private String harvest_day;
    private String nitromessage;
    Algorithm al1 = new Algorithm(Addition1.this);
    public BluetoothSocket scSocket;

    private static UUID MY_UUID = UUID.fromString("446118f0-8b1e-11e2-9e96-0800200c9a66");
//or DEADBEEF-0000-0000-0000-000000000000
    // The local server socket
    private BluetoothServerSocket mmServerSocket;

    // based on android.bluetooth.BluetoothAdapter
    private BluetoothAdapter mAdapter;
    private BluetoothDevice remoteDevice;


    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    private OutputStream outStream = null;
    //private static final UUID MY_UUID =
       //     UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    // Server's MAC address
    private static String address = "20:16:03:21:70:44";//20:16:03:21:70:44
    BluetoothAdapter mBluetoothAdapter;
    int readBufferPosition;
    BluetoothSocket mmSocket;
    BluetoothDevice mmDevice;
    OutputStream mmOutputStream;
    InputStream mmInputStream;
    Thread workerThread;
    byte[] readBuffer;
    public void moveToSecond(View view) {

        Intent intent = new Intent(this, Addition2.class);
        extras = new Bundle();

        extras.putString("name", name);
        extras.putString("Area", Area);
        extras.putString("Measurement Unit", M_unit);
        extras.putString("Crop planted", Crop_grown);
        extras.putString("Growth_End_Date", harvest_day);
        extras.putString("nitromessage", nitromessage);
        intent.putExtras(extras);
        startActivity(intent);
        finish();
    }

    public void moveToThird(View view) {

        Intent intent = new Intent(this, Addition3.class);
        extras = new Bundle();

        extras.putString("name", name);
        extras.putString("Area", Area);
        extras.putString("Measurement Unit", M_unit);
        extras.putString("Crop planted", Crop_grown);
        extras.putString("Growth_End_Date", harvest_day);
        extras.putString("nitromessage", nitromessage);
        intent.putExtras(extras);
        startActivity(intent);
        finish();

    }

    private interface MessageConstants {
        public static final int MESSAGE_READ = 0;
        public static final int MESSAGE_WRITE = 1;
        public static final int MESSAGE_TOAST = 2;

        // ... (Add other message types here as needed.)
    }
   // private Handler mHandler; // handler that gets info from Bluetooth service



    public void syncDevice(View view) throws  IOException {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
        BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
       // btSocket = device.createRfcommSocketToServiceRecord(MY_UUID);
       // btSocket.connect();
        try {
            btSocket = (BluetoothSocket) device.getClass().getMethod("createRfcommSocket", new Class[]{int.class}).invoke(device, 1);
            btSocket.connect();
        }catch (Exception e2) {
                Log.e("", "Couldn't establish Bluetooth connection!");
            }
            ConnectedThread b1 = new ConnectedThread(btSocket);
    }
    public class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;
        private byte[] mmBuffer; // mmBuffer store for the stream

        public ConnectedThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the input and output streams; using temp objects because
            // member streams are final.
            try {
                tmpIn = socket.getInputStream();
            } catch (IOException e) {
                ///Log.e(TAG, "Error occurred when creating input stream", e);
            }
            try {
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
                // Log.e(TAG, "Error occurred when creating output stream", e);
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            mmBuffer = new byte[1024];
            int numBytes; // bytes returned from read()

            // Keep listening to the InputStream until an exception occurs.
            while (true) {
                try {
                    // Read from the InputStream.
                    numBytes = mmInStream.read(mmBuffer);
                    // Send the obtained bytes to the UI activity.
                    Message readMsg = mHandler.obtainMessage(
                       MessageConstants.MESSAGE_READ, numBytes, -1,
                            mmBuffer);
                    readMsg.sendToTarget();

                } catch (IOException e) {

                    break;
                }
            }
        }
    }
       /* UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); //Standard SerialPortService ID
        mmSocket = mmDevice.createRfcommSocketToServiceRecord(uuid);
        mmSocket.connect();
        mmOutputStream = mmSocket.getOutputStream();
        mmInputStream = mmSocket.getInputStream();

        //beginListenForData();
        final Handler handler = new Handler();
        final byte delimiter = 10; //This is the ASCII code for a newline character

       // stopWorker = false;
        readBufferPosition = 0;
        readBuffer = new byte[1024];
        workerThread = new Thread(new Runnable()
        {
            public void run()
            {
                while(!Thread.currentThread().isInterrupted() )
                {
                    try
                    {
                        int bytesAvailable = mmInputStream.available();
                        if(bytesAvailable > 0)
                        {
                            byte[] packetBytes = new byte[bytesAvailable];
                            mmInputStream.read(packetBytes);
                            for(int i=0;i<bytesAvailable;i++)
                            {
                                byte b = packetBytes[i];
                                if(b == delimiter)
                                {
                                    byte[] encodedBytes = new byte[readBufferPosition];
                                    System.arraycopy(readBuffer, 0, encodedBytes, 0, encodedBytes.length);
                                    final String data = new String(encodedBytes, "US-ASCII");
                                    readBufferPosition = 0;

                                    handler.post(new Runnable()
                                    {
                                        public void run()
                                        {
                          //                  myLabel.setText(data);
                                        }
                                    });
                                }
                                else
                                {
                                    readBuffer[readBufferPosition++] = b;
                                }
                            }
                        }
                    }
                    catch (IOException ex)
                    {
                        //stopWorker = true;
                    }
                }
            }
        });
*/


    /*class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;
        private byte[] mmBuffer; // mmBuffer store for the stream

        public ConnectedThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the input and output streams; using temp objects because
            // member streams are final.
            try {
                tmpIn = socket.getInputStream();
            } catch (IOException e) {
                Log.e(TAG, "Error occurred when creating input stream", e);
            }
            try {
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
                Log.e(TAG, "Error occurred when creating output stream", e);
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            mmBuffer = new byte[1024];
            int numBytes; // bytes returned from read()

            // Keep listening to the InputStream until an exception occurs.
            while (true) {
                try {
                    // Read from the InputStream.
                    numBytes = mmInStream.read(mmBuffer);
                    // Send the obtained bytes to the UI activity.
                    Message readMsg = mHandler.obtainMessage(
                            Addition1.MessageConstants.MESSAGE_READ, numBytes, -1,
                            mmBuffer);
                    readMsg.sendToTarget();

                } catch (IOException e) {
                    Log.d(TAG, "Input stream was disconnected", e);
                    break;
                }
            }
        }
    }*/




    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            byte[] writeBuf = (byte[]) msg.obj;
            // construct a string from the valid bytes in the buffer
            String writeMessage = new String(writeBuf, 0, msg.arg1);
            String seperateValues[] = writeMessage.split(",");
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
    };




    public void findCrop(View view) {
        //getting the environmental variable values
        Spinner prioritycrop = (Spinner) findViewById(R.id.spinner3);
        String priority_name = String.valueOf(prioritycrop.getSelectedItem());

        EditText temp = (EditText) findViewById(R.id.editText3);
        float tempEntered = Float.parseFloat(temp.getText().toString());


        EditText ph = (EditText) findViewById(R.id.editText4);
        float phEntered = Float.parseFloat(ph.getText().toString());

        EditText sunshine = (EditText) findViewById(R.id.editText5);
        float sunshineEntered = Float.parseFloat(sunshine.getText().toString());

        EditText humidity = (EditText) findViewById(R.id.editText6);
        float humidityEntered = Float.parseFloat(humidity.getText().toString());

        EditText nitrogen = (EditText) findViewById(R.id.editText7);
        float nitrogenentered = Float.parseFloat(nitrogen.getText().toString());


        //checking if rainfall needs to be selected or not
        CheckBox cb2 = (CheckBox) findViewById(R.id.checkBox2);
        if (cb2.isSelected()) {


            EditText rainfall = (EditText) findViewById(R.id.editText8);
            float rainfallentered = Float.parseFloat(rainfall.getText().toString());
            String[] Crop_Result = al1.findBestCrop(priority_name, tempEntered, phEntered, sunshineEntered, humidityEntered, nitrogenentered);

            // TextView tv1=(TextView) findViewById(R.id.textView24);
            //  tv1.setText(s1+s2);
            unpackandsend(Crop_Result, nitrogenentered);
        } else {

            String[] Crop_Result = al1.findBestCrop(priority_name, tempEntered, phEntered, sunshineEntered, humidityEntered, nitrogenentered);

            //String s1=Crop_Result[0];
            //String s2=Crop_Result[1];
            //TextView tv1=(TextView) findViewById(R.id.textView24);
            //tv1.setText(s1+s2);
            unpackandsend(Crop_Result, nitrogenentered);

            //TextView tv1=(TextView) findViewById(R.id.textView24);


        }
    }


    public void unpackandsend(String []Crop_Result,float nitrogen_entered){
        String nitromessage=null;
        String joiner=" ";
        byte checker=0;
        int k;
        if(nitrogen_entered<30){

            nitromessage="Nitrogen Content is Low.Plant Legumonistic Crops Alongside";
        }
        else if(nitrogen_entered>100){
            nitromessage="Too much Nitrogen in Soil.Plant Corn to minimize it";

        }


        for(int i=0;i<(Crop_Result.length);i++){
           // TextView tv1=(TextView) findViewById(R.id.textView16);
          //  tv1.setText(Crop_Result[0]);

           if("TOP".equals(Crop_Result[i])){
                        k=(i-1);
                 for( i=0;i<=(k);i++){
                     joiner=joiner+" "+Crop_Result[i];
                 }
               Intent intent = new Intent(this, ConfirmCrop.class);
               extras.putString("Message","TOP");
               extras.putString("crops",joiner );
               extras.putString("Nitrogen",nitromessage);
               intent.putExtras(extras);
               startActivity(intent);
               finish();
            }
            else if("SECOND".equals(Crop_Result[i])){
                k=i-1;
               for( i=0;i<=(k);i++){
                   joiner=joiner+" "+Crop_Result[i];
               }
               Intent intent = new Intent(this, ConfirmCrop.class);
               extras.putString("Message","SECOND");
               extras.putString("crops",joiner );
               extras.putString("Nitrogen",nitromessage);
               intent.putExtras(extras);
               startActivity(intent);
               finish();
            }
            else if("TOPACCTOPRIORITY".equals(Crop_Result[i])){
                k=i-1;
               for( i=0;i<=(k);i++){
                   joiner=joiner+" "+Crop_Result[i];
               }
               Intent intent = new Intent(this, ConfirmCrop.class);
               extras.putString("Message","TOPACCTOPRIORITY");
               extras.putString("crops",joiner );
               extras.putString("Nitrogen",nitromessage);
               intent.putExtras(extras);
               startActivity(intent);
                finish();
           }
            else if("SelectedNotGood".equals(Crop_Result[i])){
               k=i-1;
               for( i=0;i<=(k);i++){
                   joiner=joiner+" "+Crop_Result[i];
               }
               Intent intent = new Intent(this, ConfirmCrop.class);
               extras.putString("Message","NONE");
               extras.putString("crops",joiner );
               extras.putString("Nitrogen",nitromessage);
               intent.putExtras(extras);
               startActivity(intent);
                finish();
           }

            }
          //code if no item



        }




    //private Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_addition1);
        //taking data from bundle of intent
        Intent intent = getIntent();
         extras = intent.getExtras();

        name= extras.getString("name");
         Area= extras.getString("Area");
         M_unit= extras.getString("Measurement Unit");
         Crop_grown=extras.getString("Crop planted");
        nitromessage=extras.getString("nitromessage");

        harvest_day=extras.getString("Growth_End_Date");


        TextView tv1=(TextView) findViewById(R.id.textView16);
        tv1.setText(name);
        TextView tv2=(TextView) findViewById(R.id.textView17);
        tv2.setText(Area);
        TextView tv3=(TextView) findViewById(R.id.textView23);
        tv3.setText(M_unit);
        TextView tv4=(TextView) findViewById(R.id.textView19);
        tv4.setText(Crop_grown);
        TextView tv5=(TextView) findViewById(R.id.tv122);
        tv5.setText(harvest_day);


        //for the checkbox partial visibility of rainfall portion
        final TextView tv6=(TextView) findViewById(R.id.textbox100);


        final EditText et1=(EditText) findViewById(R.id.editText8);
        et1.setVisibility(View.INVISIBLE);
        tv6.setVisibility(View.INVISIBLE);

        CheckBox cb1=(CheckBox) findViewById(R.id.checkBox2);


        Button button =(Button)findViewById(R.id.button6);

       /* button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent dbmanager = new Intent(getApplicationContext(),AndroidDatabaseManager.class);
                startActivity(dbmanager);
            }
        });
*/        cb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check) {
                    tv6.setVisibility(View.VISIBLE);
                    et1.setVisibility(View.VISIBLE);
                    check=false;
                }
                else
                {
                    tv6.setVisibility(View.INVISIBLE);
                    et1.setVisibility(View.INVISIBLE);
                    check=true;
                }
                }
        });







    }


}

