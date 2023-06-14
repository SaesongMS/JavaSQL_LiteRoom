package com.example.lab2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NewPhoneActivity extends AppCompatActivity {

    public EditText mProducer;
    public EditText mModel;
    public EditText mVersion;
    public EditText mWeb_site;
    public PhoneViewModel mPhoneViewModel;

    boolean validate() {
        boolean valid = true;
        if (TextUtils.isEmpty(mProducer.getText()) ) {
            mProducer.setError("Please enter producer");
            valid = false;
        }
        if (TextUtils.isEmpty(mModel.getText()) ) {
            mModel.setError("Please enter model");
            valid = false;
        }
        if (TextUtils.isEmpty(mVersion.getText()) ) {
            mVersion.setError("Please enter version");
            valid = false;
        }
        if (TextUtils.isEmpty(mWeb_site.getText()) ) {
            mWeb_site.setError("Please enter web site");
            valid = false;
        }
        return valid;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phone);

        mProducer = findViewById(R.id.phone_producer);
        mModel = findViewById(R.id.phone_model);
        mVersion = findViewById(R.id.phone_android_version);
        mWeb_site = findViewById(R.id.phone_web_site);

        final Button web_site_button = findViewById(R.id.web_site_button);
        web_site_button.setOnClickListener(view -> {
            String url = mWeb_site.getText().toString();
            if(!url.startsWith("http://") && !url.startsWith("https://"))
                url = "http://" + url;
            Intent intent = new Intent("android.intent.action.VIEW",
                    Uri.parse(url));
            startActivity(intent);
        });

        final Button cancel_button = findViewById(R.id.cancel_button);
        cancel_button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            setResult(RESULT_CANCELED, replyIntent);
            finish();
        });

        final Button save_button = findViewById(R.id.save_button);
        save_button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (!validate()) {
                Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                String producer = mProducer.getText().toString();
                replyIntent.putExtra("producer", producer);
                String model = mModel.getText().toString();
                replyIntent.putExtra("model", model);
                String version = mVersion.getText().toString();
                replyIntent.putExtra("version", version);
                String web_site = mWeb_site.getText().toString();
                replyIntent.putExtra("web_site", web_site);
                setResult(RESULT_OK, replyIntent);
                finish();
            }

        });
    }
}
