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

public class NightPackages extends Fragment implements View.OnClickListener {

    Button checkInternetPackage;
    RelativeLayout night1000, night2000, night3000, night5000, night10000, night20000, night50000;
    Functions functions;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.fragment_night_packages, null);
        setUpUI(view);
        return view;
    }

    private void setUpUI(View view) {
        functions = new Functions(getContext());
        checkCallPermission();
        checkInternetPackage =  view.findViewById(R.id.checkNightPackage);
        night1000 = view.findViewById(R.id.night1000);
        night2000 =  view.findViewById(R.id.night2000);
        night3000 =  view.findViewById(R.id.night3000);
        night5000 =  view.findViewById(R.id.night5000);
        night10000 =  view.findViewById(R.id.night10000);
        night20000 = view.findViewById(R.id.night20000);
        night50000 = view.findViewById(R.id.night50000);

        view.findViewById(R.id.nightPackageInfo).setOnClickListener(view1 -> {
            Bundle infoString = new Bundle();
            infoString.putString("info", getResources().getString(R.string.nightPackage_text_info));
            infoString.putString("title", getResources().getString(R.string.nightPackage_title_info));
            InternetInfo internetInfo = new InternetInfo();
            internetInfo.setArguments(infoString);
            assert getFragmentManager() != null;
            getFragmentManager().beginTransaction().replace(R.id.internetInfoContainer, internetInfo).addToBackStack(null).commit();
        });

        checkInternetPackage.setOnClickListener(this);
        night1000.setOnClickListener(this);
        night2000.setOnClickListener(this);
        night3000.setOnClickListener(this);
        night5000.setOnClickListener(this);
        night10000.setOnClickListener(this);
        night20000.setOnClickListener(this);
        night50000.setOnClickListener(this);

        night1000.setSelected(true);
        night2000.setSelected(true);
        night3000.setSelected(true);
        night5000.setSelected(true);
        night10000.setSelected(true);
        night20000.setSelected(true);
        night50000.setSelected(true);
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
            case R.id.checkNightPackage:
                functions.makeUssdCall(PhoneCodes.nightCheck);
                break;
            case R.id.night1000:
                dialog = functions.createServiceDialog(PhoneCodes.night1000);
                dialog.show();
                break;
            case R.id.night2000:
                dialog = functions.createServiceDialog(PhoneCodes.night2000);
                dialog.show();
                break;
            case R.id.night3000:
                dialog = functions.createServiceDialog(PhoneCodes.night3000);
                dialog.show();
                break;
            case R.id.night5000:
                dialog = functions.createServiceDialog(PhoneCodes.night5000);
                dialog.show();
                break;
            case R.id.night10000:
                dialog = functions.createServiceDialog(PhoneCodes.night10000);
                dialog.show();
                break;
            case R.id.night20000:
                dialog = functions.createServiceDialog(PhoneCodes.night20000);
                dialog.show();
                break;
            case R.id.night50000:
                dialog = functions.createServiceDialog(PhoneCodes.night50000);
                dialog.show();
                break;

        }
    }
}
