package com.example.barcodereader.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.barcodereader.R;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

public class ReadFragment extends Fragment {

    TextView barcodeResult;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.read_layout, container, false);

        barcodeResult = view.findViewById(R.id.barcode_result);
        Button readQr = view.findViewById(R.id.read_qr_button);
        readQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BarcodeScan.class);
                startActivityForResult(intent, 0);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 0){
            if (resultCode == CommonStatusCodes.SUCCESS){
                if (data!= null){
                    Barcode barcode = data.getParcelableExtra("barcode");
                    barcodeResult.setText(barcode.displayValue);
                }else{
                    barcodeResult.setText("No barcode found!");
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
