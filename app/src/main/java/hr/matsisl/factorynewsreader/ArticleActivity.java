package hr.matsisl.factorynewsreader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import hr.matsisl.factorynewsreader.model.ArticleModel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ArticleActivity extends AppCompatActivity {
    private Context context;
    private ViewPager viewPager;
    private FragmentCollectionAdapter adapter;
    private List<ArticleModel> articles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        setContentView(R.layout.activity_article);
        Intent intent=this.getIntent();
        articles=(ArrayList<ArticleModel>)intent.getSerializableExtra("articles") ;
        viewPager=(ViewPager) findViewById(R.id.article_viewpager);
        List<ArticleModel> m=new ArrayList<>();
        adapter = new FragmentCollectionAdapter(getSupportFragmentManager(),0, articles);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(intent.getIntExtra("id",0)-1);
    }
}
