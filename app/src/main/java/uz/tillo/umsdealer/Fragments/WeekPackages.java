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
import android.widget.RelativeLayout;
import android.widget.Toast;

import uz.tillo.umsdealer.R;
import uz.tillo.umsdealer.Utils.Functions;
import uz.tillo.umsdealer.Utils.PhoneCodes;


/**
 * Created by Kholmatov on 23.03.2017.
 * Developed by Rakhmatillo & Mukhammadyunus 26.09.2018.
 */

public class WeekPackages extends Fragment implements View.OnClickListener {

    /*Button checkInternetPackage;*/
    RelativeLayout week100, week200, week300;
    Functions functions;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.fragment_week_packages, null);
        setUpUI(view);
        return view;
    }

    private void setUpUI(View view) {
        functions = new Functions(getContext());
        checkCallPermission();
        /*checkInternetPackage = view.findViewById(R.id.checkNightPackage);*/
        week100 = view.findViewById(R.id.week100);
        week200 = view.findViewById(R.id.week200);
        week300 = view.findViewById(R.id.week300);




        /*checkInternetPackage.setOnClickListener(this);*/
        week100.setOnClickListener(this);
        week200.setOnClickListener(this);
        week300.setOnClickListener(this);

        week100.setSelected(true);
        week200.setSelected(true);
        week300.setSelected(true);


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
                Toast.makeText(getContext(), R.string.call_permission_warn, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onClick(View view) {
        checkCallPermission();
        AlertDialog dialog;
        switch (view.getId()) {
/*            case R.id.checkNightPackage:
                functions.makeUssdCall(PhoneCodes.nightCheck);
                break;*/
            case R.id.week100:
                dialog = functions.createServiceDialog(PhoneCodes.week100);
                dialog.show();
                break;
            case R.id.week200:
                dialog = functions.createServiceDialog(PhoneCodes.week200);
                dialog.show();
                break;
            case R.id.week300:
                dialog = functions.createServiceDialog(PhoneCodes.week300);
                dialog.show();
                break;
        }
    }
}
