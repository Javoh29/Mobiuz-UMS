package uz.tillo.umsdealer.adapter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import at.blogc.android.views.ExpandableTextView;
import uz.tillo.umsdealer.R;
import uz.tillo.umsdealer.Utils.Functions;
import uz.tillo.umsdealer.Utils.PhoneCodes;


/**
 * Created by Siyavushkhon on 08.04.2018.
 * Developed by Rakhmatillo & Mukhammadyunus 26.09.2018.
 */

public class TariffsAdapter extends RecyclerView.Adapter<TariffsAdapter.ViewHolder> {

    private Context context;
    private String[] tariffsList;
    private Resources resources;
    private Functions functions;

    public TariffsAdapter(Context context) {
        this.context = context;
        resources = context.getResources();
        tariffsList = resources.getStringArray(R.array.tariffs_list);
        functions = new Functions(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams") View v = LayoutInflater.from(context).inflate(R.layout.row_tariff_item, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int tariffId = resources.getIdentifier(tariffsList[position], "array", context.getPackageName());
        String[] tariffInfo = resources.getStringArray(tariffId);
        holder.tariffName.setText(tariffInfo[0]);
        holder.tariffDesc.setText(tariffInfo[1]);
        TypedArray typedArray = resources.obtainTypedArray(tariffId);
        int icon = 3;
        holder.tariffIcon.setImageDrawable(typedArray.getDrawable(icon));
        typedArray.recycle();
    }

    @Override
    public int getItemCount() {
        return tariffsList.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tariffName;
        private ExpandableTextView tariffDesc;
        private ImageView tariffIcon;
        private ImageButton toggleButton;


        public ViewHolder(View itemView) {
            super(itemView);
            tariffName = itemView.findViewById(R.id.rowTariffNameTextView);
            tariffDesc = itemView.findViewById(R.id.rowTariffDescriptionTextView);
            tariffIcon = itemView.findViewById(R.id.rowTariffIconImageView);
            toggleButton = itemView.findViewById(R.id.expand_collapse_tariffBtn);
            TextView tariffChange = itemView.findViewById(R.id.change_tariff);

            tariffChange.setOnClickListener(this);
            toggleButton.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.change_tariff) {

                checkCallPermission();
                int tariffId = resources.getIdentifier(tariffsList[getLayoutPosition()], "array", context.getPackageName());
                String[] tariffInfo = resources.getStringArray(tariffId);
                AlertDialog confirm;
                confirm = functions.createConfirmDialog(tariffInfo[0], tariffInfo[2] + PhoneCodes.encodedHash);
                confirm.show();

            }
            if (view.getId() == R.id.expand_collapse_tariffBtn) {
                if (tariffDesc.isExpanded()) {
                    tariffDesc.collapse();
                    toggleButton.setImageResource(R.drawable.ic_expand_more_black_24dp);
                } else {
                    tariffDesc.expand();
                    toggleButton.setImageResource(R.drawable.ic_expand_less_black_24dp);
                }
            }
        }
    }

    private void checkCallPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!functions.isCallPermissionGranted())
                ((Activity) context).requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1);
        }
    }


}
