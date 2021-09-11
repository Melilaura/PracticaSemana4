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
    private Button buttonHost;
    private TextView textID;

    //private int partA,partB,partC,partD;
    private EditText ipA, ipB, ipC, ipD;
    private int partA, partB, partC, partD;

    private String ip;
    private String hostA,hostB, hostC;

    private Boolean press;



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

        // PING THING
        buttonPing.setOnClickListener((view)->{

            press=false;

            partA= Integer.parseInt(ipA.getText().toString());
            partB= Integer.parseInt(ipB.getText().toString());
            partC= Integer.parseInt(ipC.getText().toString());
            partD= Integer.parseInt(ipD.getText().toString());

            if(validateIpPart(partA) && validateIpPart(partB)&& validateIpPart(partC) && validateIpPart(partD)){
                ip= partA+"."+partB+"."+partC+"."+partD;
                ping(ip);
            }else
                {
                Toast.makeText(this, "La IP no es valida, inserte números entre 0 y 255", Toast.LENGTH_SHORT).show();

            }
        });

        textID= findViewById(R.id.textID);

        new Thread(()->{
            try {
                InetAddress localIp = InetAddress.getLocalHost();
                runOnUiThread(()->
                        textID.setText(localIp.getHostAddress()));
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            }).start();
        // PING THING FINISH


        // HOST THING

        buttonHost = findViewById(R.id. buttonHost);

        buttonHost.setOnClickListener((view)->{

            press= true;

            partA= Integer.parseInt(ipA.getText().toString());
            partB= Integer.parseInt(ipB.getText().toString());
            partC= Integer.parseInt(ipC.getText().toString());

            if(validateIpPart(partA) && validateIpPart(partB)&& validateIpPart(partC)){
                hostA=""+partA;
                hostB=""+partB;
                hostC=""+partC;
                Host();
            }
            else
            {
                Toast.makeText(this, "La IP no es valida, inserte números entre 0 y 255", Toast.LENGTH_SHORT).show();

            }
        });

        // HOST THING FINISH

        }



        private boolean validateIpPart (int part)
        {
            return part >= 0 && part <= 255;
        }

        private void ping(String ip){

        Intent resultIntent = new Intent(this, PingActivity.class);

        resultIntent.putExtra("ip", ip);

        startActivity(resultIntent);
        }


    private void Host(){

        Intent hostIntent = new Intent(this, PingActivity.class);

        hostIntent.putExtra("hostA",  hostA);
        hostIntent.putExtra("hostB", hostB);
        hostIntent.putExtra("hostC", hostC);
        hostIntent.putExtra("press", press);

        startActivity(hostIntent);
    }


    }
