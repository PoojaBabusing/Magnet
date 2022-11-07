
package com.bluetooth.communicatorexample;

import android.app.Application;

import com.bluetooth.communicator.BluetoothCommunicator;
import com.bluetooth.communicator.tools.BluetoothTools;

import java.util.ArrayList;
import java.util.Random;

public class Global extends Application {
    private BluetoothCommunicator bluetoothCommunicator;

    @Override
    public void onCreate() {
        super.onCreate();
        String name = android.os.Build.MODEL;

        ArrayList<Character> supportedCharacters = BluetoothTools.getSupportedUTFCharacters(this);
        boolean equals = true;
        for (int i = 0; i < name.length() && equals; i++) {
            if (!supportedCharacters.contains(Character.valueOf(name.charAt(i)))) {
                equals = false;
            }
        }
        if (!equals || name.length() > 18) {
            name = "User " + new Random().nextInt(21);
        }

        bluetoothCommunicator = new BluetoothCommunicator(this, name, BluetoothCommunicator.STRATEGY_P2P_WITH_RECONNECTION);
    }

    public BluetoothCommunicator getBluetoothCommunicator() {
        return bluetoothCommunicator;
    }
}
