package com.cduestc.mymusic.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cduestc.mymusic.MainActivity;
import com.cduestc.mymusic.R;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin;
    private EditText loginEmail;
    private EditText loginPwd;
    private SQLiteDatabase db;
    private Integer uid = 0;
    private String name = null;
    private String sex = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        init();
    }
    private void init(){
        db = openOrCreateDatabase("music.db", MODE_PRIVATE, null);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        loginEmail = findViewById(R.id.loginEmail);
        loginPwd = findViewById(R.id.loginPwd);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnLogin){
            if(login()){
                Toast.makeText(getApplicationContext(), "登录成功！欢迎你"+name, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Login.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("username", name);
                bundle.putInt("uid", uid);
                intent.putExtras(bundle);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "登录失败！", Toast.LENGTH_LONG).show();
            }
        }
    }

    @SuppressLint("Range")
    private boolean login(){
        if(uid != 0){
            uid = 0;
            name = null;
        }
        String loginEmails = loginEmail.getText().toString();
        String loginPwds = loginPwd.getText().toString();
//        Toast.makeText(getApplicationContext(), loginEmails, Toast.LENGTH_LONG).show();
//        String sql = "SELECT * FROM user WHERE email = '" + loginEmails +"' and password='" + loginPwds +"'";
//        db.execSQL(sql);
        Cursor cursor = db.query("user", null, "email = ? and password = ?", new String[]{loginEmails, loginPwds}, null, null,null,null);

//        Cursor cursor = db.query("user", null, null, null, null, null,null,null);

        String sb = "";
        while(cursor.moveToNext()) {
            name = cursor.getString(cursor.getColumnIndex("username"));
//            @SuppressLint("Range") String age = cursor.getString(cursor.getColumnIndex("gender"));
            sex = cursor.getString(cursor.getColumnIndex("gender"));
            @SuppressLint("Range") Integer id = cursor.getInt(cursor.getColumnIndex("id"));
//            Log.i("info","查询------->" + "姓名：" + name + " " + "年龄：" + age + " " + "性别：" + sex);
            Log.i("info", id.toString());
            sb = "查询------->" + "姓名：" + name + " " + "年龄：" + " " + "性别：" + sex;
            uid = id;
        }
        cursor.close();
        return uid != 0;
//        Toast.makeText(getApplicationContext(), sb, Toast.LENGTH_LONG).show();
//        if ()
//        return true;
    }
}