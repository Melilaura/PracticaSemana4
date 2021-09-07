package com.example.semana4;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class PingActivity extends AppCompatActivity {

    private ConstraintLayout pingLayout;

    private TextView textResultado;
    private Button buttonRegresar;

    private String ip;
    private String estado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ip= getIntent().getStringExtra(ip);

        pingLayout = findViewById(R.id.pingLayout);

        textResultado = findViewById(R.id.textResultado);
        buttonRegresar = findViewById(R.id.buttonRegresar);

        buttonRegresar.setOnClickListener((view) -> {
            finish();
        });

        estado= "";
        new Thread(() -> {

            for (int i=0; i<5;i++){

                try {
                    InetAddress searchIp = InetAddress.getByName(ip);
                    if (searchIp.isReachable(100)){
                        estado +="Recibido\n";
                    }else {
                        estado +="Perdido\n";
                    }
                    runOnUiThread(()->{
                        textResultado.setText(estado);
                    });

                }catch (UnknownHostException e){

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


        }).start();


    }
}




