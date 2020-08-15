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
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import uz.tillo.umsdealer.R;
import uz.tillo.umsdealer.utils.Functions;
import uz.tillo.umsdealer.utils.PhoneCodes;

/**
 * Created by Kholmatov on 23.03.2017.
 * Developed by Rakhmatillo & Mukhammadyunus 26.09.2018.
 */

public class PackagesFragment extends Fragment implements View.OnClickListener {
    Button checkInternetPackage;
    TextView packageInfo;
    RelativeLayout package300, package500, package1000, package2000, package3000, package5000, package10000, package20000, package30000, package50000;
    Functions functions;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.fragment_packages, null);
        setUpUI(view);
        return view;
    }

    private void setUpUI(View view) {
        functions = new Functions(getContext());
        checkCallPermission();
        checkInternetPackage =view.findViewById(R.id.checkInternetPackage);
        package300 = view.findViewById(R.id.package300);
        package500 = view.findViewById(R.id.package500);
        package1000 =  view.findViewById(R.id.package1000);
        package2000 = view.findViewById(R.id.package2000);
        package3000 = view.findViewById(R.id.package3000);
        package5000 = view.findViewById(R.id.package5000);
        package10000 = view.findViewById(R.id.package10000);
        package20000 =  view.findViewById(R.id.package20000);
        package30000 = view.findViewById(R.id.package30000);
        package50000 = view.findViewById(R.id.package50000);
        packageInfo =view.findViewById(R.id.packageInfo);

        packageInfo.setOnClickListener(view1 -> {
            Bundle infoString = new Bundle();
            infoString.putString("info", getResources().getString(R.string.package_text_info));
            infoString.putString("title", getResources().getString(R.string.package_title_info));
            InternetInfo internetInfo = new InternetInfo();
            internetInfo.setArguments(infoString);
            assert getFragmentManager() != null;
            getFragmentManager().beginTransaction().replace(R.id.internetInfoContainer, internetInfo).addToBackStack(null).commit();
        });
        checkInternetPackage.setOnClickListener(this);
        package300.setOnClickListener(this);
        package500.setOnClickListener(this);
        package1000.setOnClickListener(this);
        package2000.setOnClickListener(this);
        package3000.setOnClickListener(this);
        package5000.setOnClickListener(this);
        package10000.setOnClickListener(this);
        package20000.setOnClickListener(this);
        package30000.setOnClickListener(this);
        package50000.setOnClickListener(this);

        package300.setSelected(true);
        package500.setSelected(true);
        package1000.setSelected(true);
        package2000.setSelected(true);
        package3000.setSelected(true);
        package5000.setSelected(true);
        package10000.setSelected(true);
        package20000.setSelected(true);
        package30000.setSelected(true);
        package50000.setSelected(true);
    }


    @Override
    public void onClick(View view) {
        checkCallPermission();
        AlertDialog dialog;
        switch (view.getId()) {
            case R.id.checkInternetPackage:
                functions.makeUssdCall(PhoneCodes.packageCheck);
                break;
            case R.id.package300:
                dialog = functions.createServiceDialog(PhoneCodes.package300);
                dialog.show();
                break;
            case R.id.package500:
                dialog = functions.createServiceDialog(PhoneCodes.package500);
                dialog.show();
                break;
            case R.id.package1000:
                dialog = functions.createServiceDialog(PhoneCodes.package1000);
                dialog.show();
                break;
            case R.id.package2000:
                dialog = functions.createServiceDialog(PhoneCodes.package2000);
                dialog.show();
                break;
            case R.id.package3000:
                dialog = functions.createServiceDialog(PhoneCodes.package3000);
                dialog.show();
                break;
            case R.id.package5000:
                dialog = functions.createServiceDialog(PhoneCodes.package5000);
                dialog.show();
                break;
            case R.id.package10000:
                dialog = functions.createServiceDialog(PhoneCodes.package10000);
                dialog.show();
                break;
            case R.id.package20000:
                dialog = functions.createServiceDialog(PhoneCodes.package20000);
                dialog.show();
                break;
            case R.id.package30000:
                dialog = functions.createServiceDialog(PhoneCodes.package30000);
                dialog.show();
                break;
            case R.id.package50000:
                dialog = functions.createServiceDialog(PhoneCodes.package50000);
                dialog.show();
                break;
        }
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

}
