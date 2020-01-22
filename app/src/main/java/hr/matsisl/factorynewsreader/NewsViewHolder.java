package hr.matsisl.factorynewsreader;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.matsisl.factorynewsreader.model.ArticleModel;

public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    @BindView(R.id.news_title)
    TextView title;
    @BindView(R.id.news_imageView)
    ImageView image;

    View view;
    List<ArticleModel> articles=new ArrayList<>();
    int id=0;
    public NewsViewHolder(@NonNull View itemView, List<ArticleModel> articles) {
        super(itemView);
        view=itemView;
        ButterKnife.bind(this,itemView);
        view.setOnClickListener(this);
        title=itemView.findViewById(R.id.news_title);
        image=itemView.findViewById(R.id.news_imageView);
        this.articles=articles;
    }

    public void bind(ArticleModel articleModel){
        id= articleModel.getIdArticle();
       title.setText(articleModel.getTitle());
       Picasso.with(view.getContext()).load(articleModel.getUrlToImage()).into(image);
    }

    @OnClick
    public void openArticle(){
        Bundle args=new Bundle();
        args.putSerializable("articles", (Serializable) articles);
        args.putInt("id",id);
        Intent intent=new Intent(view.getContext(),ArticleActivity.class);
        intent.putExtras(args);
        view.getContext().startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        openArticle();
    }
}
