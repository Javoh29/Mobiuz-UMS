package uz.tillo.umsdealer.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import uz.tillo.umsdealer.R;

/**
 * Created by Kholmatov on 27.03.2017.
 * Developed by Rakhmatillo & Mukhammadyunus 26.09.2018.
 */

public class InternetInfo extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.fragment_internet_info, null);
        assert getArguments() != null;
        String info = getArguments().getString("info");
        String title = getArguments().getString("title");
        TextView internetInfoTitleTv = view.findViewById(R.id.internetInfoTitleText);
        TextView internetInfoTv = view.findViewById(R.id.internetInfoText);
        internetInfoTitleTv.setText(Html.fromHtml(title));
        internetInfoTv.setText(Html.fromHtml(info));
        return view;
    }
}
