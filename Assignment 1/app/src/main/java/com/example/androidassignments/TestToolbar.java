package com.example.androidassignments;

import android.content.DialogInterface;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TestToolbar extends AppCompatActivity
{
    private String menuItemMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);

        menuItemMessage = getResources().getString(R.string.op_1_selected_snack);

        Toolbar toolBar = findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, R.string.floating_button_msg, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        int itemId = menuItem.getItemId();

        switch(itemId)
        {
            case R.id.menu_item_01:
                Log.d("Toolbar", getString(R.string.op_1_selected_toast));
                Snackbar.make(findViewById(R.id.menu_item_01), menuItemMessage, Snackbar.LENGTH_LONG).show();
                break;
            case R.id.menu_item_02:
                new AlertDialog.Builder(TestToolbar.this)
                        .setTitle(R.string.go_back_dialog_title).setMessage(R.string.go_back_dialog_msg)
                        .setNegativeButton(R.string.go_back_dialog_no_choice, null)
                        .setPositiveButton(R.string.go_back_dialog_yes_choice, new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface a, int b)
                            {
                                finish();
                            }
                        }).create().show();
                break;
            case R.id.menu_item_03:
                final EditText msg = new EditText(getApplicationContext());
                msg.setHint(R.string.msg_enter_hint);
                new AlertDialog.Builder(TestToolbar.this)
                        .setTitle(R.string.set_msg_dialog_title).setMessage(R.string.set_msg_dialog_msg)
                        .setView(msg)
                        .setNegativeButton(R.string.set_msg_no_choice, null)
                        .setPositiveButton(R.string.set_msg_yes_choice, new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface a, int b)
                            {
                                menuItemMessage = msg.getText().toString().trim();
                                Toast.makeText(TestToolbar.this, R.string.msg_set_toast, Toast.LENGTH_SHORT).show();
                            }
                        }).create().show();
                break;
            case R.id.menu_item_04:
                Toast.makeText(TestToolbar.this, R.string.part_four_msg, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        return true;
    }
}