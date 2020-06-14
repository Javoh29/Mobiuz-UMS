package uz.tillo.umsdealer.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Objects;

import uz.tillo.umsdealer.R;

public class UsefulActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useful);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle(getResources().getString(R.string.useful));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
    public void ums_pro(View view) {
        Intent telegram_intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://telegram.me/umsuzb2"));
        startActivity(telegram_intent);
    }
    public void USSD_click(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=uz.nisd.ussduzbekistan2020ucellbeelineuzmobilemobiuzums")));
    }
    }


