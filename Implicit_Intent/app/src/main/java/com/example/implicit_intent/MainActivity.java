package com.example.implicit_intent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText mWebsiteEditText;
    private  EditText mLocationEditText;
    private  EditText mShareTextEditText;

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

        mWebsiteEditText = findViewById(R.id.website_edittext);
        mLocationEditText = findViewById(R.id.location_edittext);
        mShareTextEditText = findViewById(R.id.share_edittext);
    }

    public void openWebsite(View view) {
        String url = mWebsiteEditText.getText().toString();

        Uri webpage = Uri.parse(url);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(webpage);

        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }else {
            Log.d("ImplicitIntent","Can't handle this intent");
        }
    }

    public void openLocation(View view) {
        String loc = mLocationEditText.getText().toString();

        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(addressUri);

        if (!(intent.resolveActivity(getPackageManager()) == null)){
            startActivity(intent);
        }else {
            Log.d("ImplicitIntent","Can't handle this intent");
        }
    }

    public void shareText(View view) {
        String txt = mShareTextEditText.getText().toString();
        String mimeType = "text/plain";

        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Share this text with: ")
                .setText(txt)
                .startChooser();
    }
}