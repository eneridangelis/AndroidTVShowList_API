package com.example.listagem.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.listagem.R;
import com.example.listagem.repository.network.moviedb.RetrofitClientInstance;
import com.example.listagem.ui.adapters.TvShowListAdapter;
import com.example.listagem.model.TvShow;
import com.example.listagem.model.TvShowList;
import com.example.listagem.repository.network.moviedb.GetDataService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TvShowListAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showProgress();

        loadPhotos();
    }

    private void showProgress() {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage(getString(R.string.loading));
        progressDialog.show();
    }

    private void loadPhotos() {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<TvShowList> call = service.getAllTvShows();
        call.enqueue(new Callback<TvShowList>(){
            @Override
            public void onResponse(Call<TvShowList> call, Response<TvShowList> response) {
                progressDialog.dismiss();
                generateDataList(response.body());
            }


            @Override
            public void onFailure(Call<TvShowList> call, Throwable t){
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, R.string.load_photos_error, Toast.LENGTH_SHORT).show();
                Log.i("Eneri", Log.getStackTraceString(t));
            }
        });
    }

    private void generateDataList(TvShowList photoList){
        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new TvShowListAdapter(this, photoList.getResults());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setListener(new TvShowListAdapter.Listener() {
            @Override
            public void onItemClick(TvShow currItem) {
                callActivity(currItem);
            }
        });
    }

    private void callActivity(TvShow currItem){
        Bundle bundle = new Bundle();
        bundle.putSerializable("retroPhoto", currItem);
        Intent cartIntent = new Intent(this, ShowOverviewActivity.class);
        cartIntent.putExtras(bundle);
        startActivity(cartIntent);
    }
}
