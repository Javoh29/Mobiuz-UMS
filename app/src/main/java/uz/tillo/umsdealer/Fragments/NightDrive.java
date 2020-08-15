package uz.tillo.umsdealer.Fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import uz.tillo.umsdealer.R;
import uz.tillo.umsdealer.utils.Functions;
import uz.tillo.umsdealer.utils.PhoneCodes;

/**
 * Created by Kholmatov on 23.03.2017.
 * Developed by Rakhmatillo & Mukhammadyunus 26.09.2018.
 */

public class NightDrive extends Fragment implements View.OnClickListener {

    RelativeLayout drive1,drive7,drive30;
    Functions functions;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.fragment_night_drive, null);
        setUpUI(view);
        return view;
    }

    private void setUpUI(View view) {
        functions = new Functions(getContext());
        checkCallPermission();
        drive1 = view.findViewById(R.id.drive1);
        drive7 = view.findViewById(R.id.drive7);
        drive30 = view.findViewById(R.id.drive30);


        view.findViewById(R.id.nightDrivePackageInfo).setOnClickListener(view1 -> {
            Bundle infoString = new Bundle();
            infoString.putString("info",getResources().getString(R.string.nightDrivePackage_text_info));
            infoString.putString("title",getResources().getString(R.string.nightDrivePackage_title_info));
            InternetInfo internetInfo = new InternetInfo();
            internetInfo.setArguments(infoString);
            assert getFragmentManager() != null;
            getFragmentManager().beginTransaction().replace(R.id.internetInfoContainer, internetInfo).addToBackStack(null).commit();
        });

        drive1.setOnClickListener(this);
        drive7.setOnClickListener(this);
        drive30.setOnClickListener(this);

        drive1.setSelected(true);
        drive7.setSelected(true);
        drive30.setSelected(true);
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
            case R.id.drive1:
                dialog = functions.createServiceDialog(PhoneCodes.drive1);
                dialog.show();
                break;
            case R.id.drive7:
                dialog = functions.createServiceDialog(PhoneCodes.drive7);
                dialog.show();
                break;
            case R.id.drive30:
                dialog = functions.createServiceDialog(PhoneCodes.drive30);
                dialog.show();
                break;
        }
    }
}
