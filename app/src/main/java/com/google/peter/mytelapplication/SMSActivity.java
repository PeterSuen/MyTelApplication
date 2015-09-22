package com.google.peter.mytelapplication;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class SMSActivity extends Activity {
    private EditText messageEditText;
    private Button sendBtn;
    private Button clearBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        messageEditText = (EditText) findViewById(R.id.messageedittext);
        sendBtn = (Button) findViewById(R.id.sendbtn);
        clearBtn = (Button) findViewById(R.id.clearbtn);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 接收电话号码数据
                Bundle bundle = getIntent().getExtras();
                String phoneNum = bundle.getString("phoneNum");
                // 获取发送的内容
                String message = messageEditText.getText().toString();
                if (phoneNum == null || "".equals(phoneNum)) {
                    Toast.makeText(SMSActivity.this,
                            "Please input SMS Content!", Toast.LENGTH_SHORT)
                            .show();
                    return;
                }
                // 发送短信
                sendSMS(phoneNum, message);
            }
        });

        // 置空message输入框
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageEditText.setText("");
            }
        });
    }

    private void sendSMS(String phoneNum, String message) {
        //初始化发短信SmsManager类
        SmsManager smsManager = SmsManager.getDefault();
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this,
                MainActivity.class), 0);
        //如果短信内容长度超过70则分为若干条发
        if (message.length() > 70) {
            ArrayList<String> msgs = smsManager.divideMessage(message);
            for (String msg : msgs) {
                smsManager.sendTextMessage(phoneNum, null, msg, pi, null);
            }
        } else {
            smsManager.sendTextMessage(phoneNum, null, message, pi, null);
        }
        Toast.makeText(this, "Send Message Success!", Toast.LENGTH_SHORT)
                .show();
    }
}