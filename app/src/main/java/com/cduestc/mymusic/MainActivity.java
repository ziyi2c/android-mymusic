package com.cduestc.mymusic;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cduestc.mymusic.ui.login.Login;
import com.cduestc.mymusic.ui.register.Register;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.cduestc.mymusic.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;
    private Button barBtnLeft;
    private Button barBtnRight;
    private Button button;
    private TextView barText;
    private String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViewAndListener();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null){
            username = intent.getStringExtra("username");
            if (!username.equals("")) {
//            Toast.makeText(getApplicationContext(), username, Toast.LENGTH_LONG).show();
                barBtnLeft.setVisibility(View.GONE);
            }

        }
//        Log.i("intent", intent.toString());

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_discover, R.id.navigation_user, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    private void initViewAndListener(){

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); //Enable自定义的View
            actionBar.setCustomView(R.layout.action_bar);  //绑定自定义的布局：actionbar_layout.xml

            /**
             绑定 控件
             */
            barText=(TextView)actionBar.getCustomView().findViewById(R.id.bar_text);
            barBtnLeft=(Button)actionBar.getCustomView().findViewById(R.id.bar_btn_left);
            barBtnRight=(Button)actionBar.getCustomView().findViewById(R.id.bar_btn_right);

        }else {
            Log.e("actionbar","is null");
        }
        barBtnLeft.setOnClickListener((View.OnClickListener) this);
        barBtnRight.setOnClickListener((View.OnClickListener) this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bar_btn_left:
//                barText.setText("登录");
//                startActivityForResult(new Intent(MainActivity.this, LoginActivity.class), 2);
                startActivity(new Intent(MainActivity.this, Login.class));
                break;
            case R.id.bar_btn_right:
                startActivity(new Intent(MainActivity.this, Register.class));
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
//            exit();
            close();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void close(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("退出");//设置标题
        alertDialog.setMessage("是否确定退出?");//提示消息
        alertDialog.setIcon(R.mipmap.ic_launcher);//设置图标
        alertDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            //点击确定按钮执行的事件
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alertDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            //点击取消按钮执行的事件
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.create();//创建对话框
        alertDialog.show();//显示对话框
    }
}