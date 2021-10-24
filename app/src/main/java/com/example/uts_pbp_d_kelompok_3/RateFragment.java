package com.example.uts_pbp_d_kelompok_3;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uts_pbp_d_kelompok_3.PN.MyApplication;

import java.util.zip.Inflater;
public class RateFragment extends Fragment{

    public RateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_rate, container, false);
        String[] asal = {"Yogyakarta","Bali","Jakarta","Bandung"};
        String[] tujuan = {"Yogyakarta","Bali","Jakarta","Bandung"};

        Button buttonCek = view.findViewById(R.id.cekTarif);
        TextView textViewTarif = view.findViewById(R.id.hasilTarif);
        AutoCompleteTextView autoCompleteTextView, autoCompleteTextView1;

        ArrayAdapter<String> itemAdapter = new ArrayAdapter<>(getActivity(),R.layout.list_item, asal);
        ArrayAdapter<String> itemAdapter1 = new ArrayAdapter<>(getActivity(),R.layout.list_item, tujuan);

        autoCompleteTextView = view.findViewById(R.id.asal);
        autoCompleteTextView1 = view.findViewById(R.id.tujuan);

        autoCompleteTextView.setAdapter(itemAdapter);
        autoCompleteTextView1.setAdapter(itemAdapter1);
        buttonCek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ongkos = "Rp. 10.000";
                textViewTarif.setText(ongkos);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }


}