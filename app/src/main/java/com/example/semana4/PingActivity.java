package com.example.semana4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class PingActivity extends AppCompatActivity {



    private TextView textPingResultado;
    private Button buttonRegresar;

    private String ip;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping);


        text="Buscando\n";

        textPingResultado = findViewById(R.id.textPingResultado);
        buttonRegresar = findViewById(R.id.buttonRegresar);

        ip= getIntent().getExtras().getString("ip");

       buttonRegresar.setOnClickListener((view) -> {
            finish();
        });

        new Thread(() -> {
            for (int i=0; i<5;i++){

                try {
                    InetAddress searchIp = InetAddress.getByName(ip);

                    if (searchIp.isReachable(1000)){
                        text +="Recibido\n";
                        textPingResultado.setText(text);

                    }else {
                        text +="Perdido\n";
                        textPingResultado.setText(text);

                    }
                    runOnUiThread(()->{
                        textPingResultado.setText(text);
                    }
                    );
                }catch (UnknownHostException e){
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


        }).start();


    }
}




