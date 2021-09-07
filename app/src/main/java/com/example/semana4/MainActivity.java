package com.example.semana4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout mainLayout;

    private Button buttonPing;
    private TextView textIP;

    private int partA,partB,partC,partD;
    private EditText ipA, ipB, ipC, ipD;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = findViewById(R.id.mainLayout);

        ipA = findViewById(R.id.ipA);
        ipB = findViewById(R.id.ipB);
        ipC = findViewById(R.id.ipC);
        ipD = findViewById(R.id.ipD);

        buttonPing = findViewById(R.id.buttonPing);

        buttonPing.setOnClickListener((view)->{

            partA= Integer.parseInt(ipA.getText().toString());
            partB= Integer.parseInt(ipB.getText().toString());
            partC= Integer.parseInt(ipC.getText().toString());
            partD= Integer.parseInt(ipC.getText().toString());

            if(validateIpPart(partA) && validateIpPart(partB)&& validateIpPart(partC) && validateIpPart(partD)){
                String ip= " "+ partA + partB+ partC+partD;
                ping(ip);
            }else {
                Toast.makeText(this, "La IP no es vaalida", Toast.LENGTH_SHORT);
            }
        });

        textIP= findViewById(R.id.textIP);

        new Thread(()->{
            try {
                InetAddress localIp = InetAddress.getLocalHost();
                runOnUiThread(()->{
                    textIP.setText(localIp.getHostAddress());
                });
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            }).start();

        }

        private boolean validateIpPart (int part){
        if(part < 0 || part > 255){
            return false;
        }
        return true;
        }

        private void ping(String IP){
        Intent resultIntent = new Intent(this, PingActivity.class);
        resultIntent.putExtra("ip", IP);
        startActivity(resultIntent);
        }

        private String formatIpAddress (int ip){
        return String.format(Locale.US, "Xd,Xd,Xd,Xd", (ip & 0xff), (ip >> 8 & 0xff),(ip >> 16 & 0xff),(ip >> 24 & 0xff));
        }



    }
