package uz.tillo.umsdealer.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.widget.Toast;

import java.util.Locale;

import uz.tillo.umsdealer.Activities.MainActivity;
import uz.tillo.umsdealer.R;

/**
 * Created by Kholmatov on 22.03.2017.
 * Developed by Rakhmatillo & Mukhammadyunus 26.09.2018.
 */

public class Functions {


    private Context context;

    public Functions(Context con) {
        this.context = con;
    }

    public boolean isCallPermissionGranted() {
        String request = "android.permission.CALL_PHONE";
        int result = this.context.checkCallingOrSelfPermission(request);
        return (result == PackageManager.PERMISSION_GRANTED);
    }

    public void makeUssdCall(String code) {
        Intent intent;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (isCallPermissionGranted()) {
                intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + code));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("com.android.phone.extra.slot", true);
                intent.putExtra("com.android.phone.extra.slot", true);
                context.startActivity(intent);
            } else {
                Toast.makeText(context, context.getResources().getString(R.string.dialog_permission_settings), Toast.LENGTH_SHORT).show();
            }
        } else {
            intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + code));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    public AlertDialog createConfirmDialog(String tariff, final String code) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(R.string.sure_confirm)
                .setMessage(context.getString(R.string.confirm_ask) + " " + tariff + "?")
                .setNegativeButton(context.getResources().getString(R.string.cancel), (dialogInterface, i) -> dialogInterface.dismiss())
                .setPositiveButton(context.getResources().getString(R.string.yes_dialog), (dialogInterface, i) -> makeUssdCall(code));
        return builder.create();
    }

    public AlertDialog createServiceDialog(final String code) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(R.string.sure_confirm)
                .setMessage(context.getString(R.string.confirm_service_ask))
                .setNegativeButton(context.getResources().getString(R.string.cancel), (dialogInterface, i) -> dialogInterface.dismiss())
                .setPositiveButton(context.getResources().getString(R.string.yes_dialog), (dialogInterface, i) -> makeUssdCall(code));
        return builder.create();
    }

    public AlertDialog createCancelServiceDialog(final String code) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(R.string.sure_confirm)
                .setMessage(context.getString(R.string.confirm_cancel_service_ask))
                .setNegativeButton(context.getResources().getString(R.string.cancel), (dialogInterface, i) -> dialogInterface.dismiss())
                .setPositiveButton(context.getResources().getString(R.string.yes_dialog), (dialogInterface, i) -> makeUssdCall(code));
        return builder.create();
    }

    public AlertDialog getLanguageDialog() {
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        final int[] selectedLang = {preferences.getInt("languageId", 0)};
        String[] language = {"Русский", "O'zbek"};
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.choose_language);
        builder.setSingleChoiceItems(language, selectedLang[0], (dialogInterface, i) -> selectedLang[0] = i);
        builder.setCancelable(true);
        builder.setPositiveButton(context.getResources().getString(R.string.yes_dialog), (dialogInterface, i) -> {
            preferences.edit().putBoolean("languageChange", true).apply();
            changeLanguage(selectedLang, true);
        });
        builder.setNegativeButton(context.getResources().getString(R.string.cancel), (dialogInterface, i) -> dialogInterface.dismiss());
        return builder.create();
    }

    public void changeLanguage(int[] selectedLang, boolean ussd) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putInt("languageId", selectedLang[0]).apply();
        Locale myLocale = new Locale((selectedLang[0] == 0) ? "RU" : "UZ");
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(context, MainActivity.class);
        context.startActivity(refresh);
        ((Activity) context).overridePendingTransition(0, 0);
        if (ussd)
            makeUssdCall((selectedLang[0] == 0) ? PhoneCodes.languageChangeRu : PhoneCodes.languageChangeUz);
        ((Activity) context).finish();
    }

    public AlertDialog closeAppDialog(final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(context.getResources().getString(R.string.exit_dialog))
                .setMessage(R.string.exit_confirm_dialog)
                .setNegativeButton(context.getResources().getString(R.string.cancel), (dialogInterface, i) -> dialogInterface.dismiss())
                .setPositiveButton(context.getResources().getString(R.string.yes_dialog), (dialogInterface, i) -> {
                    activity.finish();
                    System.exit(0);
                });
        return builder.create();
    }

    public float getRateApp() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getFloat("appRate", 0.0f);
    }

    public void setRateApp(Float rate) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putFloat("appRate", rate).apply();
    }
}
