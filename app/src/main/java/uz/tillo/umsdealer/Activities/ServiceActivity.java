package uz.tillo.umsdealer.Activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;


import java.util.Objects;

import at.blogc.android.views.ExpandableTextView;
import uz.tillo.umsdealer.R;
import uz.tillo.umsdealer.Utils.Functions;
import uz.tillo.umsdealer.Utils.PhoneCodes;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {
    Functions functions;
    LinearLayout btn2_purchase, btn3_purchase, btn4_purchase, btn5_purchase, btn6_purchase, btn7_purchase, btn8_purchase, btn9_purchase, btn10_purchase;
    LinearLayout btn3_cancel, btn4_cancel, btn5_cancel, btn6_cancel;

    ImageButton btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10;
    ExpandableTextView expandableTextView2, expandableTextView3, expandableTextView4, expandableTextView5,
            expandableTextView6, expandableTextView7, expandableTextView8, expandableTextView9, expandableTextView10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        setUpUI();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpUI() {
        setTitle(getResources().getString(R.string.service));
        functions = new Functions(this);
        checkCallPermission();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btn2_purchase = findViewById(R.id.btn2_purchase);
        btn3_purchase = findViewById(R.id.btn3_purchase);
        btn4_purchase = findViewById(R.id.btn4_purchase);
        btn5_purchase = findViewById(R.id.btn5_purchase);
        btn6_purchase = findViewById(R.id.btn6_purchase);
        btn7_purchase = findViewById(R.id.btn7_purchase);
        btn8_purchase = findViewById(R.id.btn8_purchase);
        btn9_purchase = findViewById(R.id.btn9_purchase);
        btn10_purchase= findViewById(R.id.btn10_purchase);


        btn3_cancel = findViewById(R.id.btn3_cancel);
        btn4_cancel = findViewById(R.id.btn4_cancel);
        btn5_cancel = findViewById(R.id.btn5_cancel);
        btn6_cancel = findViewById(R.id.btn6_cancel);


        expandableTextView2 = findViewById(R.id.expandableTextView2);
        expandableTextView3 = findViewById(R.id.expandableTextView3);
        expandableTextView4 = findViewById(R.id.expandableTextView4);
        expandableTextView5 = findViewById(R.id.expandableTextView5);
        expandableTextView6 = findViewById(R.id.expandableTextView6);
        expandableTextView7 = findViewById(R.id.expandableTextView7);
        expandableTextView8 = findViewById(R.id.expandableTextView8);
        expandableTextView9 = findViewById(R.id.expandableTextView9);
        expandableTextView10 = findViewById(R.id.expandableTextView10);


        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn10 = findViewById(R.id.btn10);


        //expandable layout actions
        expandableTextView2.setInterpolator(new OvershootInterpolator());
        expandableTextView3.setInterpolator(new OvershootInterpolator());
        expandableTextView4.setInterpolator(new OvershootInterpolator());
        expandableTextView5.setInterpolator(new OvershootInterpolator());
        expandableTextView6.setInterpolator(new OvershootInterpolator());
        expandableTextView7.setInterpolator(new OvershootInterpolator());
        expandableTextView8.setInterpolator(new OvershootInterpolator());
        expandableTextView9.setInterpolator(new OvershootInterpolator());
        expandableTextView10.setInterpolator(new OvershootInterpolator());


        btn2.setOnClickListener(this::btnClick);
        btn3.setOnClickListener(this::btnClick);
        btn4.setOnClickListener(this::btnClick);
        btn5.setOnClickListener(this::btnClick);
        btn6.setOnClickListener(this::btnClick);
        btn7.setOnClickListener(this::btnClick);
        btn8.setOnClickListener(this::btnClick);
        btn9.setOnClickListener(this::btnClick);
        btn10.setOnClickListener(this::btnClick);


        btn2_purchase.setOnClickListener(this);
        btn3_purchase.setOnClickListener(this);
        btn4_purchase.setOnClickListener(this);
        btn5_purchase.setOnClickListener(this);
        btn6_purchase.setOnClickListener(this);
        btn7_purchase.setOnClickListener(this);
        btn8_purchase.setOnClickListener(this);
        //family.setOnClickListener(this);
        btn9_purchase.setOnClickListener(this);
        btn10_purchase.setOnClickListener(this);

        btn3_cancel.setOnClickListener(this::cancelService);
        btn4_cancel.setOnClickListener(this::cancelService);
        btn5_cancel.setOnClickListener(this::cancelService);
        btn6_cancel.setOnClickListener(this::cancelService);

    }

    private void cancelService(View view) {
        checkCallPermission();
        AlertDialog dialog;
        switch (view.getId()) {
            case R.id.btn3_cancel:
                dialog = functions.createCancelServiceDialog(PhoneCodes.holdCallCancel);
                dialog.show();
                break;
            case R.id.btn4_cancel:
                dialog = functions.createCancelServiceDialog(PhoneCodes.missedCallCancel);
                dialog.show();
                break;
            case R.id.btn5_cancel:
                dialog = functions.createCancelServiceDialog(PhoneCodes.antiAONCancel);
                dialog.show();
                break;
            case R.id.btn6_cancel:
                dialog = functions.createCancelServiceDialog(PhoneCodes.lte4GCancel);
                dialog.show();
        }

    }

    private void btnClick(View view) {
        switch (view.getId()) {
            // btn1 mutlaqo cheksiz edi o'chirib tashlangan
            case R.id.btn2:
                if (expandableTextView2.isExpanded()) {
                    expandableTextView2.collapse();
                    btn2.setImageResource(R.drawable.ic_expand_more_black_24dp);
                } else {
                    expandableTextView2.expand();
                    btn2.setImageResource(R.drawable.ic_expand_less_black_24dp);
                }
                break;
            case R.id.btn3:
                if (expandableTextView3.isExpanded()) {
                    expandableTextView3.collapse();
                    btn3.setImageResource(R.drawable.ic_expand_more_black_24dp);
                } else {
                    expandableTextView3.expand();
                    btn3.setImageResource(R.drawable.ic_expand_less_black_24dp);
                }
                break;
            case R.id.btn4:
                if (expandableTextView4.isExpanded()) {
                    expandableTextView4.collapse();
                    btn4.setImageResource(R.drawable.ic_expand_more_black_24dp);
                } else {
                    expandableTextView4.expand();
                    btn4.setImageResource(R.drawable.ic_expand_less_black_24dp);
                }
                break;
            case R.id.btn5:
                if (expandableTextView5.isExpanded()) {
                    expandableTextView5.collapse();
                    btn5.setImageResource(R.drawable.ic_expand_more_black_24dp);
                } else {
                    expandableTextView5.expand();
                    btn5.setImageResource(R.drawable.ic_expand_less_black_24dp);
                }
                break;
            case R.id.btn6:
                if (expandableTextView6.isExpanded()) {
                    expandableTextView6.collapse();
                    btn6.setImageResource(R.drawable.ic_expand_more_black_24dp);
                } else {
                    expandableTextView6.expand();
                    btn6.setImageResource(R.drawable.ic_expand_less_black_24dp);
                }
                break;
            case R.id.btn7:
                if (expandableTextView7.isExpanded()) {
                    expandableTextView7.collapse();
                    btn7.setImageResource(R.drawable.ic_expand_more_black_24dp);
                } else {
                    expandableTextView7.expand();
                    btn7.setImageResource(R.drawable.ic_expand_less_black_24dp);
                }
                break;
            case R.id.btn8:
                if (expandableTextView8.isExpanded()) {
                    expandableTextView8.collapse();
                    btn8.setImageResource(R.drawable.ic_expand_more_black_24dp);
                } else {
                    expandableTextView8.expand();
                    btn8.setImageResource(R.drawable.ic_expand_less_black_24dp);
                }
                break;
            case R.id.btn9:
                if (expandableTextView9.isExpanded()) {
                    expandableTextView9.collapse();
                    btn9.setImageResource(R.drawable.ic_expand_more_black_24dp);
                } else {
                    expandableTextView9.expand();
                    btn9.setImageResource(R.drawable.ic_expand_less_black_24dp);
                }
                break;
            case R.id.btn10:
                if (expandableTextView10.isExpanded()) {
                    expandableTextView10.collapse();
                    btn10.setImageResource(R.drawable.ic_expand_more_black_24dp);
                } else {
                    expandableTextView10.expand();
                    btn10.setImageResource(R.drawable.ic_expand_less_black_24dp);
                }
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
                Toast.makeText(this, R.string.call_permission_warn, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onClick(View view) {
        checkCallPermission();
        AlertDialog dialog;
        switch (view.getId()) {
            /*case R.id.btn1_purchase:
                dialog = functions.createServiceDialog(PhoneCodes.mutlaqo_cheksiz);
                dialog.show();
                break;*/
            case R.id.btn2_purchase:
                dialog = functions.createServiceDialog(PhoneCodes.promisePayment);
                dialog.show();
                break;
            case R.id.btn3_purchase:
                dialog = functions.createServiceDialog(PhoneCodes.holdCall);
                dialog.show();
                break;
            case R.id.btn4_purchase:
                dialog = functions.createServiceDialog(PhoneCodes.missedCall);
                dialog.show();
                break;
            case R.id.btn5_purchase:
                dialog = functions.createServiceDialog(PhoneCodes.antiAON);
                dialog.show();
                break;
            case R.id.btn6_purchase:
                dialog = functions.createServiceDialog(PhoneCodes.lte4G);
                dialog.show();
                break;
            case R.id.btn7_purchase:
                functions.makeUssdCall(PhoneCodes.activeServices);
                break;
            case R.id.btn8_purchase:
                dialog = functions.createServiceDialog(PhoneCodes.internationalCall);
                dialog.show();
                break;
            case R.id.btn9_purchase:
                dialog = functions.createServiceDialog(PhoneCodes.super0);
                dialog.show();
                break;
            case R.id.btn10_purchase:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ip.ums.uz/selfcare/"));
                startActivity(intent);
                finish();
                break;
        }
    }
}
