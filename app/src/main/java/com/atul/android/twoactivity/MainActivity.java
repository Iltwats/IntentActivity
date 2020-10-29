package com.atul.android.twoactivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.atul.android.twoactivity.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
    }
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "com.atul.android.twoactivitiy.extra.MESSAGE";
    public static final int TEXT_REQUEST = 1;

    public void launchSecondActivity(View view) {
        Log.d(LOG_TAG,"Button Clicked");
        Intent intent = new Intent(this, SecondActivity.class);
        String message = b.editTextMain.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            // Test to make sure the intent reply result was good.
            if (resultCode == RESULT_OK) {
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);

                // Make the reply head visible.
                b.textHeaderReply.setVisibility(View.VISIBLE);

                // Set the reply and make it visible.
                b.textMessageReply.setText(reply);
                b.textMessageReply.setVisibility(View.VISIBLE);
            }
        }
    }
}