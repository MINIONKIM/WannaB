package minion.kim.wannab;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient implements Runnable {
    final ByteArrayOutputStream arrStream = new ByteArrayOutputStream();
    final DataOutputStream outStream = new DataOutputStream(arrStream);

    byte[] outBuf;
    String requestStr;

    UDPClient(String key)
    {
        requestStr = key;
    }

    public void makeStream() throws Exception {
        outStream.writeChars(requestStr);
        outStream.close();

        outBuf = arrStream.toByteArray();
    }

    public void run() {

        try {
            makeStream();

            InetAddress servAddr = InetAddress.getByName("meeneeon.ddns.net");

            DatagramSocket sock = new DatagramSocket();
            DatagramPacket pkt = new DatagramPacket(outBuf,outBuf.length,servAddr,9999);

            sock.send(pkt);
            sock.close();
        } catch (Exception e){
            Log.d("UDP : ", "Connection Error");
        }
    }
}
