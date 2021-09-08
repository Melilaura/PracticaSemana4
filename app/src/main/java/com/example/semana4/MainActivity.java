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
    private TextView textID;

    //private int partA,partB,partC,partD;
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

            int partA= Integer.parseInt(ipA.getText().toString());
            int partB= Integer.parseInt(ipB.getText().toString());
            int partC= Integer.parseInt(ipC.getText().toString());
            int partD= Integer.parseInt(ipC.getText().toString());

            if(validateIpPart(partA) && validateIpPart(partB)&& validateIpPart(partC) && validateIpPart(partD)){
                String ip= " "+ partA + partB + partC + partD;
                ping(ip);
            }else {
                Toast.makeText(this, "La IP no es valida", Toast.LENGTH_SHORT).show();

            }
        });

        textID= findViewById(R.id.textID);

        new Thread(()->{
            try {
                InetAddress localIp = InetAddress.getLocalHost();
                runOnUiThread(()-> textID.setText(localIp.getHostAddress()));
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            }).start();

        }

        private boolean validateIpPart (int part){
            return part >= 0 && part <= 255;
        }

        private void ping(String ip){
        Intent resultIntent = new Intent(this, PingActivity.class);
        resultIntent.putExtra("ip", ip);
        startActivity(resultIntent);
        }

        private String formatIpAddress (int ip){
        return String.format(Locale.US, "%d,%d,%d,%d", (ip & 0xff), (ip >> 8 & 0xff),(ip >> 16 & 0xff),(ip >> 24 & 0xff));
        }



    }
