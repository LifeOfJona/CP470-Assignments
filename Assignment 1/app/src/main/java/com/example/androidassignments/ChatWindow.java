package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class ChatWindow extends AppCompatActivity
{
    private ListView chatList;
    private EditText chatBox;
    private Button sendButton;

    ChatDatabaseHelper datasource;
    ArrayList<String> messages;
    ChatAdapter messageAdapter;
    SQLiteDatabase db2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        datasource = new ChatDatabaseHelper(this);
        db2 = datasource.getWritableDatabase();

        chatList = findViewById(R.id.chat_list);
        chatBox = findViewById(R.id.chat_box);
        sendButton = findViewById(R.id.send_button);
        messages = new ArrayList<>();

        Cursor c = db2.rawQuery("SELECT KEY_MESSAGE FROM chatTable", null);
        c.moveToFirst();
        while(!c.isAfterLast() ) {
            Log.i("ChatWindow", "SQL MESSAGE:" + c.getString(c.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE)));
            messages.add(c.getString(c.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE)));
            c.moveToNext();
        }
        Log.i("ChatWindow", "Cursor’s  column count =" + c.getColumnCount() );
        for(int i = 0; i < c.getColumnCount(); i++){
            Log.i("ChatWindow","table name column: "+c.getColumnName(i));
        }
        c.close();


        messageAdapter =new ChatAdapter( this );
//        messages.clear();

        chatList.setAdapter(messageAdapter);
        sendButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String currentChat = chatBox.getText().toString().trim();
                ContentValues valueSql = new ContentValues();
                valueSql.put(ChatDatabaseHelper.KEY_MESSAGE, currentChat);
                db2.insert(ChatDatabaseHelper.TABLE_NAME, null, valueSql);
                messages.add(currentChat);
                messageAdapter.notifyDataSetChanged();
                chatBox.setText("");
            }
        });
    }


    @Override
    protected void onDestroy(){
        super.onDestroy();
        db2.close();
    }

    class ChatAdapter extends ArrayAdapter<String>
    {
        public ChatAdapter(Context context)
        {
            super(context, 0);
        }

        public String getItem(int position)
        {
            return messages.get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View v = null;
            TextView message;

            if(position % 2 == 0)
            {
                v = inflater.inflate(R.layout.chat_row_incoming, null);
                message = v.findViewById(R.id.message_text_in);
            }
            else
            {
                v = inflater.inflate(R.layout.chat_row_outgoing, null);
                message = v.findViewById(R.id.message_text_out);
            }

            message.setText(getItem(position));
            return v;
        }

        public int getCount()
        {
            int counting = messages.size();
            return counting;
        }
    }
}