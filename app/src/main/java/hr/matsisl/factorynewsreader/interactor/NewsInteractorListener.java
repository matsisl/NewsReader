package hr.matsisl.factorynewsreader.interactor;

import java.util.List;

import hr.matsisl.factorynewsreader.model.ArticleModel;

public interface NewsInteractorListener {
    void arrivedNews(List<ArticleModel> newsList);
}
