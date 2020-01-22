package hr.matsisl.factorynewsreader;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import hr.matsisl.factorynewsreader.interactor.NewsInteractorImplementation;
import hr.matsisl.factorynewsreader.model.ArticleModel;
import hr.matsisl.factorynewsreader.presenter.NewsPresenter;
import hr.matsisl.factorynewsreader.presenter.NewsPresenterImplementation;
import hr.matsisl.factorynewsreader.view.NewsView;


public class NewsActivity extends AppCompatActivity implements NewsView {
    RecyclerView rv;
    NewsRecyclerAdapter adapter;
    NewsPresenter newsPresenter;
    ProgressBar loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        checkPremission();
        loader=(ProgressBar) findViewById(R.id.loader);
        loader.setVisibility(View.VISIBLE);
        newsPresenter = new NewsPresenterImplementation(new NewsInteractorImplementation(),this);
        try {
            newsPresenter.tryGetNews();
        }catch (Exception e){
            showErrorDialog();
        }

    }

    private void showErrorDialog(){
        AlertDialog.Builder error=new AlertDialog.Builder(this);
        error.setTitle("Greška");
        error.setMessage("Ups, došlo je do pogreške...");
        error.setPositiveButton("OK",null);
    }

    @Override
    public void arrived(List<ArticleModel> newsList) {
        loader.setVisibility(View.GONE);
        rv = (RecyclerView) findViewById(R.id.news_recyclerview);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        rv.setHasFixedSize(true);
        adapter=new NewsRecyclerAdapter(newsList,NewsActivity.this);
        rv.setAdapter(adapter);

    }

    private void checkPremission(){
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED ||
        ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
            System.exit(0);
    }
}
