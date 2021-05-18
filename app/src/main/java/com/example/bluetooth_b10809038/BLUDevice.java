package com.example.bluetooth_b10809038;

public class BLUDevice {
    public String deviceName;
    public String Rssi;
    public String timestampNanos;
    public String content;

    @Override
    public String toString() {
        return "BLUDevice{" +
                "deviceName='" + deviceName + '\'' +
                ", Rssi='" + Rssi + '\'' +
                ", timestampNanos='" + timestampNanos + '\'' +
                '}';
    }
}
