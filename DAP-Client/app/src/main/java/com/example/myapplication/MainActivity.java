package com.example.myapplication;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.customDialog.GetScoreDialog;
import com.example.myapplication.ui.dashboard.DashboardFragment;
import com.example.myapplication.ui.notifications.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    private DashboardFragment dash = new DashboardFragment();
    private NotificationsFragment notice = new NotificationsFragment();
    private FragmentManager fm = getSupportFragmentManager();
    private GetScoreDialog dialog;
    SharedPreferences rs;
    private boolean recommandState;
    public static final String RECOMMAND_SETTING = "recommandSetting";
    TextView titleBar;
    ImageButton settingBtn;
    LinearLayout findBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleBar = findViewById(R.id.acitivityTitle);
        findBtn = findViewById(R.id.findCenterBtn);
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.nav_host_fragment, dash).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        titleBar.setText("????????? ?????????");

        rs = getSharedPreferences(RECOMMAND_SETTING,MODE_PRIVATE);
        if (rs == null){
            SharedPreferences.Editor editor = rs.edit();
            editor.putBoolean("recommandState", true );
            editor.commit();
            recommandState = rs.getBoolean("recommandState",true);
        }else{
            recommandState = rs.getBoolean("recommandState",true);
        }

        //???????????? ????????? ?????? ????????? ???????????? ??????.
        if(recommandState) {
            //????????? ??????????????? ??????
            //?????? ?????? ????????? ????????? ??????????????? rcmd =false??? ??????
            openRecommandDialog();
        }
        settingBtn = findViewById(R.id.settingButton);
        settingBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });

        findBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    Log.e("find center button", "click");
                    Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fm.beginTransaction();
            switch (menuItem.getItemId()){
                case R.id.navigation_test_list:
                    transaction.replace(R.id.nav_host_fragment, dash).commitAllowingStateLoss();
                    titleBar.setText("????????? ?????????");
                    break;
                case R.id.navigation_test_result:
                    transaction.replace(R.id.nav_host_fragment, notice).commitAllowingStateLoss();
                    titleBar.setText("?????? ?????????");

                    break;
            }
            return true;
        }
    };

    private void openRecommandDialog(){
        dialog = new GetScoreDialog(MainActivity.this);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
    }
}
