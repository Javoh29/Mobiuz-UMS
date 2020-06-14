package uz.tillo.umsdealer.Fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import uz.tillo.umsdealer.R;
import uz.tillo.umsdealer.Utils.Functions;
import uz.tillo.umsdealer.Utils.PhoneCodes;

/**
 * Created by Kholmatov on 22.03.2017.
 * Developed by Rakhmatillo & Mukhammadyunus 26.09.2018.
 */

public class InternetSettingsFragment extends DialogFragment implements View.OnClickListener {

    Button getSettings, turnOnMobileNet, turnOffMobileNet, turnOffOnNet;
    Functions functions;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.internet_settings_dialog, null);
        functions = new Functions(getActivity().getApplicationContext());
        getSettings = view.findViewById(R.id.settingsGetBtn);
        turnOnMobileNet = view.findViewById(R.id.turnOnMobileNet);
        turnOffMobileNet = view.findViewById(R.id.turnOffMobileNet);
        turnOffOnNet = view.findViewById(R.id.onNetTurnOff);

        getSettings.setTransformationMethod(null);
        turnOnMobileNet.setTransformationMethod(null);
        turnOffMobileNet.setTransformationMethod(null);
        turnOffOnNet.setTransformationMethod(null);

        getSettings.setOnClickListener(this);
        turnOnMobileNet.setOnClickListener(this);
        turnOffMobileNet.setOnClickListener(this);
        turnOffOnNet.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        checkCallPermission();
        switch (view.getId()) {
            case R.id.settingsGetBtn:
                functions.makeUssdCall(PhoneCodes.getSettings);
                break;
            case R.id.turnOnMobileNet:
                functions.makeUssdCall(PhoneCodes.turnOnMobileNet);
                break;
            case R.id.turnOffMobileNet:
                functions.makeUssdCall(PhoneCodes.turnOffMobileNet);
                break;
            case R.id.onNetTurnOff:
                functions.makeUssdCall(PhoneCodes.turnOffOnNet);
                break;

        }
    }

    private void checkCallPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!functions.isCallPermissionGranted()) requestPermissions(new String[]{Manifest.permission.CALL_PHONE},1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length <= 0 || grantResults[0] == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(getActivity().getApplicationContext(), R.string.call_permission_warn, Toast.LENGTH_LONG).show();
            }
        }
    }
}
