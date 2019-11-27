package com.example.listagem.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.listagem.R;
import com.example.listagem.model.TvShow;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

public class ShowOverviewActivity extends AppCompatActivity {
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_moviepage);

        Bundle bundle = this.getIntent().getExtras();
        TvShow tvShow = null;
        if(bundle != null){
            tvShow = (TvShow) bundle.getSerializable("tvShow");
        }

        TextView txtName;
        TextView txtOverview;
        ImageView poster;

        txtName = findViewById(R.id.text_name);
        txtOverview = findViewById(R.id.overview);
        poster = findViewById(R.id.poster);

        txtName.setText(tvShow.getOriginal_name());
        txtOverview.setText(tvShow.getOverview());

        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttp3Downloader(this));
        builder.build().load("https://image.tmdb.org/t/p/original" + tvShow.getPoster_path())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(poster);
    }
}

