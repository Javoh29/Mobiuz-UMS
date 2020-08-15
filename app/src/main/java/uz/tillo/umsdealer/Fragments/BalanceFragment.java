package uz.tillo.umsdealer.Fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import uz.tillo.umsdealer.R;
import uz.tillo.umsdealer.utils.Functions;
import uz.tillo.umsdealer.utils.PhoneCodes;

/**
 * Created by Kholmatov S on 22.03.2017.
 * Developed by Rakhmatillo & Mukhammadyunus 26.09.2018.
 */

public class BalanceFragment extends DialogFragment implements View.OnClickListener {

    Button balanceBtn, lastPaymentBtn, myDisChargeBtn, myNumberBtn, allMyNumbersBtn,RemainTrafBtn,CheckActServ;
    Functions functions;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.balance_dialog, null);
        functions = new Functions(getActivity().getApplicationContext());
        balanceBtn = view.findViewById(R.id.balanceCheckBtn);
        lastPaymentBtn = view.findViewById(R.id.lastPaymentBtn);
        myDisChargeBtn = view.findViewById(R.id.myDisChargeBtn);
        RemainTrafBtn= view.findViewById(R.id.RemainTrafBtn);
        myNumberBtn = view.findViewById(R.id.myNumberBtn);
        allMyNumbersBtn = view.findViewById(R.id.allMyNumbersBtn);
        CheckActServ= view.findViewById(R.id.CheckActServ);

        balanceBtn.setOnClickListener(this);
        lastPaymentBtn.setOnClickListener(this);
        myDisChargeBtn.setOnClickListener(this);
        RemainTrafBtn.setOnClickListener(this);
        myNumberBtn.setOnClickListener(this);
        allMyNumbersBtn.setOnClickListener(this);
        CheckActServ.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        checkCallPermission();
        switch (view.getId()) {
            case R.id.balanceCheckBtn:
                int lang = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).getInt("languageId",0);
                functions.makeUssdCall((lang==0)?PhoneCodes.balanceUssdRu:PhoneCodes.balanceUssdUz);
                break;
            case R.id.lastPaymentBtn:
                functions.makeUssdCall(PhoneCodes.lastPayment);
                break;
            case R.id.myDisChargeBtn:
                functions.makeUssdCall(PhoneCodes.myDisCharge);
                break;
            case R.id.RemainTrafBtn:
                functions.makeUssdCall(PhoneCodes.RemainTrafBtn);
                break;
            case R.id.myNumberBtn:
                functions.makeUssdCall(PhoneCodes.myNumber);
                break;
            case R.id.allMyNumbersBtn:
                functions.makeUssdCall(PhoneCodes.allMyNumbers);
                break;
            case R.id.CheckActServ:
                functions.makeUssdCall(PhoneCodes.CheckActServ);
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
