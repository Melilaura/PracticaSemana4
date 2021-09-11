package com.example.semana4;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class PingActivity extends AppCompatActivity {



    private TextView textPingResultado;
    private Button buttonRegresar;

    private String ip;
    private String text;
    private String host;

    private Boolean press;
    private String hostA,hostB,hostC;

    private ConstraintLayout resultLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping);

        resultLayout = findViewById(R.id.resultadoLayout);

        //ping things

        text=" ";
        textPingResultado = findViewById(R.id.textPingResultado);
        ip= getIntent().getExtras().getString("ip");

        //host thing

        Intent i =getIntent();
        Intent j =getIntent();
        Intent k =getIntent();
        Intent l =getIntent();
        hostA=i.getStringExtra("hostA");
        hostB=j.getStringExtra("hostB");
        hostC=k.getStringExtra("hostC");
        press=l.getBooleanExtra("press", false);

        // return
        buttonRegresar = findViewById(R.id.buttonRegresar);
        buttonRegresar.setOnClickListener((view) -> {
            finish();
        });

        //Ping Thread
        new Thread(() -> {

            if (press==false) {
            for (int m = 0; m < 5; m++) {

                try {
                    InetAddress searchIp = InetAddress.getByName(ip);

                    if (searchIp.isReachable(100)) {
                        text += "Recibido\n";
                        textPingResultado.setText(text);
                    } else {
                        text += "Perdido\n";
                        textPingResultado.setText(text);
                    }
                    runOnUiThread(() -> {
                                textPingResultado.setText(text);
                            }
                    );
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
                for (int n = 0; n < 255; n++) {

                   host= hostA+"."+hostB+"."+hostC+"."+n;
                    try {

                        InetAddress hostIp = InetAddress.getByName(host);
                        if (hostIp.isReachable(100)) {
                            text += hostIp +"\n";
                        }
                        runOnUiThread(() -> {
                                    textPingResultado.setText(text);
                                }
                        );
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();


        //Host Thread






    }
}




