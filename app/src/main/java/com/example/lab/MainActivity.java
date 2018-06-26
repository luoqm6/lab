package com.example.lab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    // modules to listen and use on the UI
    private ImageView sysuimg;
    private Button logButton;
    private Button regButton;
    private TextInputLayout EIDText;
    private TextInputLayout keyText;

    // SharedPreferences to save the id and key
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sysuimg = (ImageView) super.findViewById(R.id.sysuimg);
        logButton = (Button) super.findViewById(R.id.logButton);
        regButton = (Button) super.findViewById(R.id.regButton);
        EIDText = (TextInputLayout) super.findViewById(R.id.EID);
        keyText = (TextInputLayout) super.findViewById(R.id.key);
        // get the SharedPreferences which saves the password and id
        sharedPreferences = super.getSharedPreferences("passwordFile", MODE_PRIVATE);
        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String EIDStr = EIDText.getEditText().getText().toString();
                String keyStr = keyText.getEditText().getText().toString();
                // analyze the input id and key
                if (TextUtils.isEmpty(EIDStr)) {
                    Toast.makeText(MainActivity.this, "账号不能为空", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(keyStr)) {
                    Toast.makeText(MainActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    // find id and key from the sharedreference
                    String shareKey = sharedPreferences.getString(EIDStr, "");
                    if (shareKey.isEmpty()) {
                        Toast.makeText(MainActivity.this, "账号不存在", Toast.LENGTH_SHORT).show();
                    } else if (RegisterActivity.digest(keyStr).equals(shareKey)) {
                        // jump to WebActivity
                        Intent intent = new Intent(MainActivity.this, WebActivity.class);
                        startActivityForResult(intent, 1);
                    } else {
                        Toast.makeText(MainActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // jump to RegistActivity
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // set the EditText to empty when returned from the other activity
        EIDText.getEditText().setText("");
        keyText.getEditText().setText("");
    }
}
