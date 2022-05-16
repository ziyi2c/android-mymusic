package com.cduestc.mymusic.ui.register;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cduestc.mymusic.R;
import com.cduestc.mymusic.data.model.User;
import com.cduestc.mymusic.ui.login.Login;
import com.cduestc.mymusic.ui.login.LoginActivity;

import java.io.Serializable;

public class Register extends AppCompatActivity implements View.OnClickListener,View.OnTouchListener {

    private EditText regUserName;
    private EditText regPassword;
    private EditText regPassword2;
    private EditText regEmail;
    private EditText regBirth;
    private TextView regPrefers;
    private RadioGroup radioGender;
    private RadioButton radioGenderButton;
    private Spinner regPrefer;
    private String strings = "";
    private ArrayAdapter<String> adapter;
    private AlertDialog.Builder dialog;
    private String birthday = "";
    final String[] preferList = {"民谣","纯音乐","流行","爱情"};
    final boolean[] check = {false,false,false,false};
    private User user;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViewAndListener();
    }
    @SuppressLint("ClickableViewAccessibility")
    private void initViewAndListener(){
        db = openOrCreateDatabase("music.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS user (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE," +
                "username VARCHAR (255) NOT NULL," +
                "password VARCHAR (255) NOT NULL," +
                "email VARCHAR (255)," +
                "birth VARCHAR (255)," +
                "gender VARCHAR (255)," +
                "prefers VARCHAR (255)" +
                ");");
        regUserName = findViewById(R.id.regUsername);
        regPassword = findViewById(R.id.regPassword);
        regPassword2 = findViewById(R.id.regPassword2);
        regEmail = findViewById(R.id.regEmailAddress);
        regBirth = findViewById(R.id.regBirth);
        Button regSubmit = findViewById(R.id.reg_submit);
        regPrefer = findViewById(R.id.prefer);
        radioGender = findViewById(R.id.radioGender);
        regPrefer = findViewById(R.id.prefer);
        regPrefers = findViewById(R.id.multiplyPrefer);
        regBirth = findViewById(R.id.regBirth);
//        strings = new String[]{"流行", "乡村", "纯音乐"};
//        adapter = new ArrayAdapter<String>(Register.this, android.R.layout.simple_list_item_1, strings);
//        regPrefer.setAdapter(adapter);
        regBirth.setInputType(InputType.TYPE_NULL);
        regPrefer.requestFocus();
        regPrefers.setInputType(InputType.TYPE_NULL);
        regPrefer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String s1=adapter.getItem(i);//根据下标从适配器中获取内容

//                Toast.makeText(Register .this, "s1"+s1+"s2"+s2, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        regSubmit.setOnClickListener(this);
//        regBirth.setOnTouchListener(this);
        regBirth.setOnClickListener(this);
//        regPrefer.setOnClickListener(this);
        regPrefers.setOnClickListener(this);

//        regSubmit.setOnClickListener((View.OnClickListener) this);
    }

    private void updateValues (){
        radioGenderButton = findViewById(radioGender.getCheckedRadioButtonId());
    }
    private boolean getRegInfos() {
        String pwd = regPassword.getText().toString();
        String pwd2 = regPassword2.getText().toString();
        String username = regUserName.getText().toString();
        String birth = regBirth.getText().toString();
        String gender = radioGenderButton.getText().toString();
        String email = regEmail.getText().toString();
        String prefers = regPrefers.getText().toString();

        if(pwd.equals(pwd2)){
            user = new User(username, pwd, gender, email, prefers, birth);
            Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_LONG).show();
//            create table if not exists music_user(_id integer primary key autoincrement, name text not null, gender text, birth text, prefers text, email text)"
            String sql = "insert into user(username, password, gender, birth, prefers, email) values('"+username+"','"+pwd+"','"+gender+"','"+birth+"','"+prefers+"','"+email+"')";
            db.execSQL(sql);
            return true;
        } else {
            Toast.makeText(getApplicationContext(), "两次密码不一致", Toast.LENGTH_LONG).show();
            return false;
        }
    }
    @SuppressLint({"NonConstantResourceId", "ClickableViewAccessibility", "ResourceType"})
    @Override
    /**
     *
     * */
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.regBirth:
                Calendar calendar = Calendar.getInstance();
//                Toast.makeText(getApplicationContext(), "生日选择", Toast.LENGTH_LONG).show();

                new DatePickerDialog(Register.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        birthday = i + "-" + (i1+1) + "-" + i2;
                        regBirth.setText(birthday);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.reg_submit:
                updateValues();
//                Toast.makeText(getApplicationContext(), gender, Toast.LENGTH_LONG).show();
                if(getRegInfos()){
//                    Toast.makeText(getApplicationContext(), "注册成功："+user.getUsername(), Toast.LENGTH_LONG).show();


                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("user", (Serializable) user);

                    Intent intent = new Intent(Register.this, Login.class);
//                    intent.putExtras(bundle);
                    startActivity(intent);
                }
//                User test = (User) reg.getSerializable("user");
                break;
            case R.id.multiplyPrefer:
//                AlertDialog.Builder dialog5 = new AlertDialog.Builder (this);
//                dialog5.setTitle("测试");
//                dialog5.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.dismiss();
//                    }
//                });
//                dialog5.show();
                showMultiDialog();
                break;
//            case R.id.multiplyPrefer:
//                showMultiDialog();
//                break;
        }
    }

    @SuppressLint({"ClickableViewAccessibility", "NonConstantResourceId"})
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (view.getId()) {
        }
        return true;
    }
    private void showMultiDialog() {
        AlertDialog.Builder dialog5 = new AlertDialog.Builder (this);
        dialog5.setTitle("测试");
        dialog5.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialog5.setTitle ("请选择你的听歌偏好")
                .setMultiChoiceItems (preferList, check, new DialogInterface.OnMultiChoiceClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        Log.e("Log",preferList[which]);
                        check[which] = isChecked;
//                        strings += preferList[which] ;
                    }
                })
                .setPositiveButton ("确定", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
                        strings = "";
                        for (int n=0;n<preferList.length;n++){
                            if(check[n]){
                                strings += preferList[n] + ";";
                                regPrefers.setText(strings);
//                                Toast.makeText (Register.this, strings,Toast.LENGTH_LONG).show ();
                            }
                        }

                    }
                });
        dialog5.create();
        dialog5.show();
    }
}