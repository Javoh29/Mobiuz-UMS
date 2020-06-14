package uz.tillo.umsdealer.Activities;

import android.annotation.SuppressLint;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Objects;

import uz.tillo.umsdealer.Fragments.InternetSettingsFragment;
import uz.tillo.umsdealer.Fragments.KunlikPackages;
import uz.tillo.umsdealer.Fragments.NightDrive;
import uz.tillo.umsdealer.Fragments.NightPackages;
import uz.tillo.umsdealer.Fragments.PackagesFragment;
import uz.tillo.umsdealer.Fragments.WeekPackages;
import uz.tillo.umsdealer.R;

public class InternetPackagesActivity extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    public static TabLayout tabLayout;
    final int tabCount = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getResources().getString(R.string.internet_packages));
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setContentView(R.layout.activity_internet_packages);
        tabLayout = findViewById(R.id.internetTabs);
        ViewPager viewPager = findViewById(R.id.internetViewPager);

        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }else if (item.getItemId() == R.id.get_settings) {
            InternetSettingsFragment settingsFragment = new InternetSettingsFragment();
            //settingsFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialogTheme);
            settingsFragment.show(getFragmentManager(), "InternetSettings");
        }
        return super.onOptionsItemSelected(item);
    }


  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private class MyAdapter extends FragmentPagerAdapter {

        MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position)
            {
                case 0:
                    return new PackagesFragment();
                case 1:
                    return new NightPackages();
                case 2:
                    return new NightDrive();
                case 3:
                    return new KunlikPackages();
                case 4:
                    return new WeekPackages();
            }
            return null;
        }

        @Override
        public int getCount() {
            return tabCount;
        }

        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.packages);
                case 1:
                    return getString(R.string.night_packages);
                case 2:
                    return getString(R.string.night_drive);
                case 3:
                    return getString(R.string.mini_packages);
                case 4:
                    return getString(R.string.week_packages);
            }
            return null;
        }
    }


}
