package com.example.lab;

import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final AlertDialog.Builder alterDialog = new AlertDialog.Builder(this);
        alterDialog.setTitle("上传头像").setItems(new String []{"拍摄","从相册中选择"},
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which==0){
                            Toast.makeText(getApplicationContext(),"您选择了[拍摄]",Toast.LENGTH_SHORT).show();
                        }
                        else if(which==1){
                            Toast.makeText(getApplicationContext(),"您选择了[从相册选择]",Toast.LENGTH_SHORT).show();
                        }
                    }
                }).setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"您选择了[取消]",Toast.LENGTH_SHORT).show();
                    }
                }
        ).create();
        ImageView sysu = (ImageView) findViewById(R.id.sysu);
        if(sysu != null) {
            sysu.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    alterDialog.show();
                }
            });
        }

    }
    public void clickRB1(View target){
        Snackbar.make(target,"您选择了学生",Snackbar.LENGTH_LONG).setAction("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Snackbar 的确定按钮被点击了",Toast.LENGTH_SHORT).show();
            }
        }).show();
    }
    public void clickRB2(View target){
        Snackbar.make(target,"您选择了教职工",Snackbar.LENGTH_LONG).setAction("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Snackbar 的确定按钮被点击了",Toast.LENGTH_SHORT).show();
            }
        }).show();
    }
}
