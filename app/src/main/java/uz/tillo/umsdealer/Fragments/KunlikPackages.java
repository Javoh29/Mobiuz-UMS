package uz.tillo.umsdealer.Fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import uz.tillo.umsdealer.R;
import uz.tillo.umsdealer.Utils.Functions;
import uz.tillo.umsdealer.Utils.PhoneCodes;

/**
 * Created by Kholmatov on 23.03.2017.
 * Developed by Rakhmatillo & Mukhammadyunus 26.09.2018.
 */

public class KunlikPackages extends Fragment implements View.OnClickListener {

    Button miniPackageCheck;
    RelativeLayout day50,day100,day200,day300,day500,day1000,day2000,day3000,day5000,day10000;
    Functions functions;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.fragment_mini_packages, null);
        setUpUI(view);
        return  view;
    }


    private void setUpUI(View view) {
        functions = new Functions(getContext());
        checkCallPermission();
        miniPackageCheck = view.findViewById(R.id.miniPackageCheck);
        day50 = view.findViewById(R.id.day50);
        day100 = view.findViewById(R.id.day100);
        day200 = view.findViewById(R.id.day200);
        day300 = view.findViewById(R.id.day300);
        day500 = view.findViewById(R.id.day500);
        day1000 = view.findViewById(R.id.day1000);
        day2000 = view.findViewById(R.id.day2000);
        day3000 = view.findViewById(R.id.day3000);
        day5000 = view.findViewById(R.id.day5000);
        day10000 = view.findViewById(R.id.day10000);


        miniPackageCheck.setOnClickListener(this);

        day50.setOnClickListener(this);
        day100.setOnClickListener(this);
        day200.setOnClickListener(this);
        day300.setOnClickListener(this);
        day500.setOnClickListener(this);
        day1000.setOnClickListener(this);
        day2000.setOnClickListener(this);
        day3000.setOnClickListener(this);
        day5000.setOnClickListener(this);
        day10000.setOnClickListener(this);

        day50.setSelected(true);
        day200.setSelected(true);
        day100.setSelected(true);
        day300.setSelected(true);
        day500.setSelected(true);
        day1000.setSelected(true);
        day2000.setSelected(true);
        day3000.setSelected(true);
        day5000.setSelected(true);
        day10000.setSelected(true);

    }

    private void checkCallPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!functions.isCallPermissionGranted()) requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length <= 0 || grantResults[0] == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(getContext(), R.string.call_permission_warn, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onClick(View view) {
        checkCallPermission();
        AlertDialog dialog;
        switch (view.getId()) {
            case R.id.miniPackageCheck:
                functions.makeUssdCall(PhoneCodes.miniCheck);
                break;
            case R.id.day50:
                dialog = functions.createServiceDialog(PhoneCodes.day50);
                dialog.show();
                break;
            case R.id.day100:
                dialog = functions.createServiceDialog(PhoneCodes.day100);
                dialog.show();
                break;
            case R.id.day200:
                dialog = functions.createServiceDialog(PhoneCodes.day200);
                dialog.show();
                break;
            case R.id.day300:
                dialog = functions.createServiceDialog(PhoneCodes.day300);
                dialog.show();
                break;
            case R.id.day500:
                dialog = functions.createServiceDialog(PhoneCodes.day500);
                dialog.show();
                break;
            case R.id.day1000:
                dialog = functions.createServiceDialog(PhoneCodes.day1000);
                dialog.show();
                break;
            case R.id.day2000:
                dialog = functions.createServiceDialog(PhoneCodes.day2000);
                dialog.show();
                break;
            case R.id.day3000:
                dialog = functions.createServiceDialog(PhoneCodes.day3000);
                dialog.show();
                break;
            case R.id.day5000:
                dialog = functions.createServiceDialog(PhoneCodes.day5000);
                dialog.show();
                break;
            case R.id.day10000:
                dialog = functions.createServiceDialog(PhoneCodes.day10000);
                dialog.show();
                break;
        }
    }
}
