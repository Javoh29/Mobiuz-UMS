package uz.tillo.umsdealer.Activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Objects;

import uz.tillo.umsdealer.Fragments.InternetInfo;
import uz.tillo.umsdealer.R;
import uz.tillo.umsdealer.Utils.Functions;
import uz.tillo.umsdealer.Utils.PhoneCodes;

public class MinutePackages extends AppCompatActivity implements View.OnClickListener {
    Button minuteCheck,smsCheck;
    RelativeLayout minute60, minute120, minute180, minute300, minute900, minute1200, minute1800,sms100,sms300;

    Functions functions;
    boolean infoOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minute_packages);
        setUpUI();
    }

    private void setUpUI() {
        setTitle(getResources().getString(R.string.minute_packages));
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        functions = new Functions(this);
        checkCallPermission();
        minuteCheck = findViewById(R.id.minuteCheck);
        smsCheck = findViewById(R.id.smsCheck);

        minute60 = findViewById(R.id.minute60);
        minute120 = findViewById(R.id.minute120);
        minute180 = findViewById(R.id.minute180);
        minute300 = findViewById(R.id.minute300);
        minute900 = findViewById(R.id.minute900);
        minute1200 = findViewById(R.id.minute1200);
        minute1800 = findViewById(R.id.minute1800);

        sms100 = findViewById(R.id.sms100);
        sms300 = findViewById(R.id.sms300);



        minuteCheck.setOnClickListener(this);
        minute60.setOnClickListener(this);
        minute120.setOnClickListener(this);
        minute180.setOnClickListener(this);
        minute300.setOnClickListener(this);
        minute900.setOnClickListener(this);
        minute1200.setOnClickListener(this);
        minute1800.setOnClickListener(this);

        smsCheck.setOnClickListener(this);
        sms100.setOnClickListener(this);
        sms300.setOnClickListener(this);
    }

    private void checkCallPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!functions.isCallPermissionGranted())
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length <= 0 || grantResults[0] == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(this, R.string.call_permission_warn, Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sms_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else if (item.getItemId() == R.id.sms_info) {
            if (!infoOpen) {
                infoOpen = true;
                Bundle infoString = new Bundle();
                infoString.putString("title", getResources().getString(R.string.minute_title_info));
                infoString.putString("info", getResources().getString(R.string.minute_text_info));
                InternetInfo internetInfo = new InternetInfo();
                internetInfo.setArguments(infoString);
                getSupportFragmentManager().beginTransaction().replace(R.id.minuteInfoContainer, internetInfo).addToBackStack(null).commit();
            } else {
                infoOpen = false;
                getSupportFragmentManager().popBackStack();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        checkCallPermission();
        AlertDialog dialog;
        switch (view.getId()) {
            case R.id.minuteCheck:
                functions.makeUssdCall(PhoneCodes.minuteCheck);
                break;
            case R.id.minute60:
                dialog = functions.createServiceDialog(PhoneCodes.minute60);
                dialog.show();
                break;
            case R.id.minute120:
                dialog = functions.createServiceDialog(PhoneCodes.minute120);
                dialog.show();
                break;
            case R.id.minute180:
                dialog = functions.createServiceDialog(PhoneCodes.minute180);
                dialog.show();
                break;
            case R.id.minute300:
                dialog = functions.createServiceDialog(PhoneCodes.minute300);
                dialog.show();
                break;
            case R.id.minute900:
                dialog = functions.createServiceDialog(PhoneCodes.minute900);
                dialog.show();
                break;
            case R.id.minute1200:
                dialog = functions.createServiceDialog(PhoneCodes.minute1200);
                dialog.show();
                break;
            case R.id.minute1800:
                dialog = functions.createServiceDialog(PhoneCodes.minute1800);
                dialog.show();
                break;
            case R.id.smsCheck:
                functions.makeUssdCall(PhoneCodes.smsCheck);
                break;
            case R.id.sms100:
                dialog = functions.createServiceDialog(PhoneCodes.sms100);
                dialog.show();
                break;
            case R.id.sms300:
                dialog = functions.createServiceDialog(PhoneCodes.sms300);
                dialog.show();
                break;
        }
    }
}
