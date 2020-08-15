package uz.tillo.umsdealer.Activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Objects;

import uz.tillo.umsdealer.R;
import uz.tillo.umsdealer.utils.Functions;
import uz.tillo.umsdealer.adapter.TariffsAdapter;

public class TariffsActivity extends AppCompatActivity {

    Functions functions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarrifs);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setUpUI();
    }

    private void setUpUI() {
        setTitle(getResources().getString(R.string.tariffs));
        RecyclerView recyclerView = findViewById(R.id.activityTariffsRecyclerView);
        TariffsAdapter tariffsAdapter = new TariffsAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(tariffsAdapter);

        functions = new Functions(this);
        checkCallPermission();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
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
                Toast.makeText(this, R.string.call_permission_warn, Toast.LENGTH_LONG).show();
            }
        }
    }

}
