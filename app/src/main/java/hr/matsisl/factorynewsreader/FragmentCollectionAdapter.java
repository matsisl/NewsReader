package hr.matsisl.factorynewsreader;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import hr.matsisl.factorynewsreader.model.ArticleModel;

public class FragmentCollectionAdapter extends FragmentStatePagerAdapter {
    private List<ArticleModel> articles;
    private List<ArticleFragment> fragments;

    public FragmentCollectionAdapter(@NonNull FragmentManager fm, int behavior, List<ArticleModel> a) {
        super(fm, behavior);
        articles=a;
        fragments=new ArrayList<>();
        createFragments();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (articles.size()==0 || position>=articles.size())
            return null;
        else
            return fragments.get(position);
    }

    @Override
    public int getCount() {
        return articles.size();
    }

    private void createFragments(){
        for (int i =0;i<articles.size(); i++){
            ArticleFragment af=new ArticleFragment(articles.get(i));
            fragments.add(af);
        }
    }
}
