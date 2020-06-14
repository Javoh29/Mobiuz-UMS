package uz.tillo.umsdealer.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;

import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;


import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import android.widget.Toast;

import java.util.Objects;

import uz.tillo.umsdealer.Fragments.BalanceFragment;
import uz.tillo.umsdealer.R;
import uz.tillo.umsdealer.Utils.Functions;
import uz.tillo.umsdealer.Utils.PhoneCodes;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {


    Functions functions;
    public final int CALL_REQUEST_CODE = 1;
    LinearLayout balanceBtn, tariffBtn, internetPackageBtn, serviceBtn, minutePackagesBtn, usefulBtn;
    Button appRateBtn;
    LinearLayout easterEgg;
    @SuppressLint("StaticFieldLeak")
    static RatingBar appRatingBar;
    int easterEggCount = 0;

    public MainActivity() {
    }


    @Override
    protected void onStart() {
        checkIntent();
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpSystemUI();
        functions = new Functions(this);
        checkCallPermission();
        setUpUI();

    }

    private void setUpUI() {
        setTitle(getResources().getString(R.string.app_name_ums));
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (preferences.getInt("languageId", 0) != 0 && !preferences.getBoolean("languageChange", false)) {
            preferences.edit().putBoolean("languageChange", true).apply();
            functions.changeLanguage(new int[]{PreferenceManager.getDefaultSharedPreferences(this).getInt("languageId", 0)}, false);
        } else {
            preferences.edit().putBoolean("languageChange", false).apply();
        }

        balanceBtn = findViewById(R.id.balanceBtn);
        tariffBtn = findViewById(R.id.tariffsBtn);
        internetPackageBtn = findViewById(R.id.internetPackageBtn);
        serviceBtn = findViewById(R.id.serviceBtn);
        minutePackagesBtn = findViewById(R.id.minutePackageBtn);
        usefulBtn = findViewById(R.id.usefulBtn);
        appRateBtn = findViewById(R.id.appRateBtn);
        appRatingBar = findViewById(R.id.appRatingBar);
        appRatingBar.setRating(functions.getRateApp());
        easterEgg = findViewById(R.id.easterEgg);
        easterEgg.setOnClickListener(this);

        balanceBtn.setOnClickListener(this);
        tariffBtn.setOnClickListener(this);
        internetPackageBtn.setOnClickListener(this);
        serviceBtn.setOnClickListener(this);
        minutePackagesBtn.setOnClickListener(this);
        usefulBtn.setOnClickListener(this);
        appRateBtn.setOnClickListener(this);

    }

    private void checkCallPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!functions.isCallPermissionGranted())
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
        }
    }

    private void checkIntent() {
        if (getIntent().hasExtra("new_update")) {
            final String appPackageName = Objects.requireNonNull(getIntent().getExtras()).getString("new_update");//getPackageName(); // getPackageName() from Context or Activity object
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
            }
            getIntent().removeExtra("new_update");
        } else if (getIntent().hasExtra("new_url")) {
            String url = Objects.requireNonNull(getIntent().getExtras()).getString("new_url");
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            getIntent().removeExtra("new_url");
        }
    }

    private void setUpSystemUI() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            AlertDialog closeDialog = functions.closeAppDialog(this);
            closeDialog.show();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_support) {
            functions.makeUssdCall(PhoneCodes.callCentre);
        } else if (id == R.id.nav_language) {
            AlertDialog dialog = functions.getLanguageDialog();
            dialog.show();
        } else if (id == R.id.nav_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_text)); //
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, getString(R.string.share_nav)));
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CALL_REQUEST_CODE) {
            if (grantResults.length <= 0 || grantResults[0] == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(this, R.string.call_permission_warn, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.balanceBtn:
                new BalanceFragment().show(getFragmentManager(), "balanceDialog");
                break;
            case R.id.tariffsBtn:
                intent = new Intent(this, TariffsActivity.class);
                startActivity(intent);
                break;
            case R.id.internetPackageBtn:
                intent = new Intent(this, InternetPackagesActivity.class);
                startActivity(intent);
                break;
            case R.id.serviceBtn:
                intent = new Intent(this, ServiceActivity.class);
                startActivity(intent);
                break;
            case R.id.minutePackageBtn:
                intent = new Intent(this, MinutePackages.class);
                startActivity(intent);
                break;
            case R.id.usefulBtn:
                intent = new Intent(this, UsefulActivity.class);
                startActivity(intent);
                break;
            case R.id.appRateBtn:
                float appRate = appRatingBar.getRating();
                functions.setRateApp(appRate);
                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
                break;
            case R.id.easterEgg:
                if (easterEggCount == 5) {
                    Toast.makeText(this, "Congratulates! You find the EasterEGG :)\nDevelopers: Rakhmatillo & Mukhammadyunus \n", Toast.LENGTH_LONG).show();
                    easterEggCount = 0;
                } else
                    easterEggCount++;

                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.news_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == R.id.news_menu) {
            Intent news_intent = new Intent(this, NewsActivity.class);
            startActivity(news_intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void USSD(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=uz.nisd.ussduzbekistan2020ucellbeelineuzmobilemobiuzums")));

    }
}



