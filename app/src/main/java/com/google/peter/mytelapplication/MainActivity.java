package com.google.peter.mytelapplication;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
    private EditText inputEditText;
    private Button voiceBtn;
    private Button smsBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputEditText = (EditText) findViewById(R.id.inputnumedittext);
        voiceBtn = (Button) findViewById(R.id.voicebtn);
        smsBtn = (Button) findViewById(R.id.smsbtn);

        // Voice按钮的响应
        voiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNum = inputEditText.getText().toString();
                //判断输入是否为空
                if (phoneNum == null || "".equals(phoneNum)) {
                    Toast.makeText(MainActivity.this,
                            "Please input PhoneNum!", Toast.LENGTH_SHORT)
                            .show();
                    return;
                }
                // 直接连接打电话
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
                        + phoneNum));
                startActivity(intent);
            }
        });

        // SMS按钮的响应
        smsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNum = inputEditText.getText().toString();
                if (phoneNum == null || "".equals(phoneNum)) {
                    Toast.makeText(MainActivity.this,
                            "Please input PhoneNum!", Toast.LENGTH_SHORT)
                            .show();
                    return;
                }
                // 直接连接打电话
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SMSActivity.class);
                // 绑定电话号码数据传递到SMSActivity中
                Bundle mBundle = new Bundle();
                mBundle.putString("phoneNum", phoneNum);
                intent.putExtras(mBundle);

                startActivity(intent);
            }
        });
    }
}