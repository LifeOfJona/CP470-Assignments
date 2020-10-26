package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
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

    ArrayList<String> messages;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        chatList = findViewById(R.id.chat_list);
        chatBox = findViewById(R.id.chat_box);
        sendButton = findViewById(R.id.send_button);

        messages = new ArrayList<>();
        messages.clear();

        final ChatAdapter messageAdapter = new ChatAdapter(this);
        chatList.setAdapter(messageAdapter);

        sendButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String currentChat = chatBox.getText().toString().trim();
                messages.add(currentChat);
                messageAdapter.notifyDataSetChanged();
                chatBox.setText("");
            }
        });
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
            return messages.size();
        }
    }
}