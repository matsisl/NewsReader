package hr.matsisl.factorynewsreader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import hr.matsisl.factorynewsreader.model.ArticleModel;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsViewHolder> {
    List<ArticleModel> newsList;
    Context context;

    public NewsRecyclerAdapter(List<ArticleModel> newsList, Context context) {
        this.newsList = newsList;
        this.context = context;
    }


    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_item,parent,false);
        return new NewsViewHolder(view, newsList);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.bind(newsList.get(position));
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
}
