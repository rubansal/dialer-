package com.codingblocks.texttospeech;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ru.dimorinny.floatingtextbutton.FloatingTextButton;

public class call extends AppCompatActivity {

    EditText editText;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        editText=findViewById(R.id.editText);
        btn=findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();
                final View alertLayout = inflater.inflate(R.layout.contact, null);
                final FloatingTextButton call = (FloatingTextButton) alertLayout.findViewById(R.id.fab);
                final FloatingTextButton normal = (FloatingTextButton) alertLayout.findViewById(R.id.fab1);
                AlertDialog.Builder alert = new AlertDialog.Builder(call.this);

                // this is set the view from XML inside AlertDialog
                alert.setView(alertLayout);
                AlertDialog dialog = alert.create();
                dialog.show();

                normal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (ContextCompat.checkSelfPermission(
                                alertLayout.getContext(), android.Manifest.permission.CALL_PHONE) !=
                                PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions((Activity) alertLayout.getContext(), new
                                    String[]{android.Manifest.permission.CALL_PHONE}, 0);

                        } else {
                            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + editText.getText().toString())));
                        }
                    }
                });

                call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i=new Intent(call.this,MainActivity.class);
                        startActivity(i);

//                        if (ContextCompat.checkSelfPermission(
//                                alertLayout.getContext(), android.Manifest.permission.CALL_PHONE) !=
//                                PackageManager.PERMISSION_GRANTED) {
//                            ActivityCompat.requestPermissions((Activity) alertLayout.getContext(), new
//                                    String[]{android.Manifest.permission.CALL_PHONE}, 0);
//
//                        } else {
//                            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + editText.getText().toString())));
//                        }
                    }
                });
            }
        });
    }
}
