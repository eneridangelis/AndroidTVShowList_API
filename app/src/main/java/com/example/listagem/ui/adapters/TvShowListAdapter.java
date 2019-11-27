package com.example.listagem.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listagem.R;
import com.example.listagem.model.TvShow;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import java.util.List;

public class TvShowListAdapter extends RecyclerView.Adapter<TvShowListAdapter.CustomViewHolder> {

    private List<TvShow> dataList;
    private Context context;
    private Listener listener;

    public TvShowListAdapter(Context context, List<TvShow> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    public interface Listener{
        void onItemClick(TvShow currItem);
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{

        public final View mView;

        TextView txtName;
        TextView txtVote;
        private ImageView backdrop;

        CustomViewHolder(View itemView){
            super(itemView);
            mView = itemView;

            txtName = mView.findViewById(R.id.text_name);
            txtVote = mView.findViewById(R.id.text_vote);
            backdrop = mView.findViewById(R.id.backdrop);
        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.show_element_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, final int position) {
        holder.txtName.setText(dataList.get(position).getOriginal_name());
        holder.txtVote.setText(dataList.get(position).getVote_average()+"");

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(dataList.get(position));
            }
        });

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context))
        .build().load("https://image.tmdb.org/t/p/original" + dataList.get(position).getBackdrop_path())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.backdrop);
    }

    @Override
    public int getItemCount(){
        return dataList.size();
    }
}
