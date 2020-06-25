package com.example.barcodereader.views;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.barcodereader.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class GenerateFragment  extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.generate_layout, container, false);

        TextView previewText = view.findViewById(R.id.preview_text);
        final ImageView preview = view.findViewById(R.id.preview_image);
        Button saveButton = view.findViewById(R.id.save_button);
        final EditText sizeValue = view.findViewById(R.id.size_value);

        preview.setImageResource(R.drawable.image);
        previewText.setVisibility(View.INVISIBLE);
        preview.setVisibility(View.INVISIBLE);
        saveButton.setVisibility(View.INVISIBLE);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    saveImage(getView(), preview.getDrawable());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        final List<String> sizes = new ArrayList<>();
        sizes.add("50x50");
        sizes.add("150x150");
        sizes.add("250x250");
        sizes.add("500x500");

        final ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, sizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final Spinner sizeSpinner = view.findViewById(R.id.size_spinner);
        sizeSpinner.setAdapter(adapter);

        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button generateButton = view.findViewById(R.id.generate_button);
        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setVisible(getView());

                String url = "https://api.qrserver.com/v1/create-qr-code/?";

                String value = sizeValue.getText().toString();

                int position = sizeSpinner.getSelectedItemPosition();

                String size = sizes.get(position);

                String fullUrl = url + "size="+ size+"&data="+value;

                Log.v("URL", fullUrl);

                Picasso.get().load(fullUrl).into(preview);
            }
        });

        return view;
    }

    private void saveImage(View view, Drawable drawable) throws IOException {
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        File dir = new File("/sdcard" + "/demo/");
        dir.mkdir();
        File file = new File(dir, "qr.png");
        OutputStream outputStream = new FileOutputStream(file);
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        outputStream.flush();
        outputStream.close();
    }

    private void setVisible(View view) {
        view.findViewById(R.id.preview_text).setVisibility(View.VISIBLE);
        view.findViewById(R.id.preview_image).setVisibility(View.VISIBLE);
        view.findViewById(R.id.save_button).setVisibility(View.VISIBLE);
    }
}
