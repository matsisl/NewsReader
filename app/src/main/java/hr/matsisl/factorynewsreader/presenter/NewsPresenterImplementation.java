package hr.matsisl.factorynewsreader.presenter;

import java.util.List;

import hr.matsisl.factorynewsreader.interactor.NewsInteractor;
import hr.matsisl.factorynewsreader.interactor.NewsInteractorListener;
import hr.matsisl.factorynewsreader.model.ArticleModel;
import hr.matsisl.factorynewsreader.view.NewsView;

public class NewsPresenterImplementation implements NewsPresenter, NewsInteractorListener {
    NewsInteractor ni;
    NewsView nv;

    public NewsPresenterImplementation(NewsInteractor ni, NewsView nv) {
        this.ni = ni;
        this.nv = nv;
        this.ni.setNewsListener(this);
    }

    @Override
    public void tryGetNews() {
        ni.getNews();
    }

    @Override
    public void arrivedNews(List<ArticleModel> newsList) {
        nv.arrived(newsList);
    }

}
