package com.example.a5_tarasenko;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton btnCamera = findViewById(R.id.btnCamera);
        ImageButton btnWeb = findViewById(R.id.btnWeb);
        ImageButton btnCall = findViewById(R.id.btnCall);
        ImageButton btnMap = findViewById(R.id.btnMap);
        ImageButton btnPhotos = findViewById(R.id.btnPhotos);

        // 1. Camera Intent
        btnCamera.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(intent);
        });

        // 2. Web Browser Intent (Constructor: Intent(String action, Uri uri))
        btnWeb.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://developer.android.com"));
            startActivity(intent);
        });

        // 3. Map Intent (Constructor: Intent(), then setAction and setData)
        btnMap.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("geo:55.754283,37.62002"));
            startActivity(intent);
        });

        // 4. Call / Dialer Intent (Constructor: Intent(String action), then setData)
        btnCall.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:+74952705055"));
            startActivity(intent);
        });

        // 5. Google Photos / Gallery Intent (Implicit Intent with Intent Filter handling)
        btnPhotos.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.parse("content://media/external/images/media"), "image/*");
            startActivity(intent);
        });
    }
}