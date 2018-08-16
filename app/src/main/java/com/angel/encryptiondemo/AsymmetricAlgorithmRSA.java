package com.angel.encryptiondemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

import javax.crypto.Cipher;

public class AsymmetricAlgorithmRSA extends Activity {
    static final String TAG = "AsymmetricAlgorithmRSA";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_aes);

        // Original text
        String theTestText = "This is just a simple test!";
        TextView tvorig = (TextView) findViewById(R.id.textViewOrignal);
        tvorig.setText("\n[ORIGINAL]:\n" + theTestText + "\n");

        // Generate key pair for 1024-bit RSA encryption and decryption
        Key publicKey = null;
        Key privateKey = null;
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(1024);
            KeyPair kp = kpg.genKeyPair();
            publicKey = kp.getPublic();
            privateKey = kp.getPrivate();
        } catch (Exception e) {
            Log.e(TAG, "RSA key pair error");
        }

        // Encode the original data with RSA private key
        byte[] encodedBytes = null;
        try {
            Cipher c = Cipher.getInstance("RSA");
            c.init(Cipher.ENCRYPT_MODE, privateKey);
            encodedBytes = c.doFinal(theTestText.getBytes());
        } catch (Exception e) {
            Log.e(TAG, "RSA encryption error");
        }
        TextView tvencoded = (TextView) findViewById(R.id.textViewEncoded);
        tvencoded.setText("[ENCODED]:\n" +
                Base64.encodeToString(encodedBytes, Base64.DEFAULT) + "\n");

        // Decode the encoded data with RSA public key
        byte[] decodedBytes = null;
        try {
            Cipher c = Cipher.getInstance("RSA");
            c.init(Cipher.DECRYPT_MODE, publicKey);
            decodedBytes = c.doFinal(encodedBytes);
            TextView tvdecoded = (TextView)findViewById(R.id.textViewDecoded);
            tvdecoded.setText("[DECODED]:\n" + new String(decodedBytes) + "\n");
        } catch (Exception e) {
            Log.e(TAG, "RSA decryption error");
        }
    }
}