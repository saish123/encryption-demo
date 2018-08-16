package com.angel.encryptiondemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.buttonAsymmetricEncryption)
    Button asymmetricButton;

    @BindView(R.id.buttonSymmetricEncryption)
    Button symmetricButton;

    AppCompatRadioButton rbEnglish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        asymmetricButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        startActivity(new Intent(getApplicationContext(),AsymmetricAlgorithmRSA.class));
            }
        });


        symmetricButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SymmetricAlgorithmAES.class));
            }
        });


    }
}
