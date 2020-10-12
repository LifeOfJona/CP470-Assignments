package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "MainActivity";
    private Button LoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(ACTIVITY_NAME, "In onCreate()");

        LoginButton = findViewById(R.id.loginButton);
        LoginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent listItemsActivity = new Intent(MainActivity.this, ListItemsActivity.class);
                startActivityForResult(listItemsActivity, 10);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 10)
        {
            Log.i(ACTIVITY_NAME, "Returned to MainActivity.onActivityResult");
            if(resultCode == Activity.RESULT_OK)
            {
                String messagePassed = data.getStringExtra("Response");
                Toast.makeText(MainActivity.this, "ListItemsActivity passed: " + messagePassed, Toast.LENGTH_SHORT).show();
            }
        }
    }



        @Override
        public void onResume () {
            super.onResume();
            Log.i(ACTIVITY_NAME,"In onResume()");

        }

        @Override
        public void onStart () {
            super.onStart();
            LoginButton = findViewById(R.id.loginButton);
            Log.i(ACTIVITY_NAME,"In onStart()");

        }

        @Override
        public void onPause () {
            super.onPause();
            Log.i(ACTIVITY_NAME,"In onPause()");

        }

        @Override
        public void onStop () {
            super.onStop();
            Log.i(ACTIVITY_NAME,"In onStop()");

        }

        @Override
        public void onDestroy () {
            super.onDestroy();
            Log.i(ACTIVITY_NAME,"In onDestroy()");
        }
    }
