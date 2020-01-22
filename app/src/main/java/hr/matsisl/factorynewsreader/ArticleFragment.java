package hr.matsisl.factorynewsreader;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import hr.matsisl.factorynewsreader.model.ArticleModel;

public class ArticleFragment extends Fragment {
    @BindView(R.id.article_image)
            ImageView articleImage;
    @BindView(R.id.article_title)
            TextView title;
    @BindView(R.id.article_author)
            TextView author;
    @BindView(R.id.article_description)
            TextView description;
    @BindView(R.id.article_publishedAt)
            TextView publishedAt;
    @BindView(R.id.article_url)
            TextView url;

    ArticleModel article;
    public ArticleFragment(ArticleModel article) {
        this.article=article;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.acticle_item,container,false);
        articleImage = view.findViewById(R.id.article_image);
        title = view.findViewById(R.id.article_title);
        author = view.findViewById(R.id.article_author);
        description = view.findViewById(R.id.article_description);
        url = view.findViewById(R.id.article_url);
        publishedAt = view.findViewById(R.id.article_publishedAt);
        bindData();
        return view;
    }

    private void bindData(){
        title.setText(article.getTitle());
        description.setText(article.getDescription());
        author.setText(article.getAuthor());
        publishedAt.setText(article.getPublishedAt().toString());
        url.setText(article.getUrl());
        Picasso.with(this.getContext()).load(article.getUrlToImage()).into(articleImage);
    }

}
