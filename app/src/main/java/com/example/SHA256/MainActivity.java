package com.example.SHA256;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.ClipboardManager;

import com.example.SHA256.*;
import com.example.SHA256.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;


public class MainActivity extends AppCompatActivity {

    EditText inputText;
    Button hashBtn;
    TextView outputText;
    String digest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        myProgram();


    }

    public void myProgram(){

        inputText = (EditText) findViewById(R.id.inputText);
        hashBtn = (Button) findViewById(R.id.CalculateHashbutton);
        outputText = (TextView) findViewById(R.id.resultBox);

        hashBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                String text = inputText.getText().toString();

                try {
                    digest = sha256(text);
                }catch (Exception e){
                    e.printStackTrace();
                }

                outputText.setText(digest);
                ClipData clip = ClipData.newPlainText("digest", digest);
                clipboard.setPrimaryClip(clip);

                Toast.makeText(getApplicationContext(), "Copied to clipboard", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public String sha256(String input) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // Change this to UTF-16 if needed
        md.update(input.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();

        return String.format("%064x", new BigInteger(1, digest));
    }


}