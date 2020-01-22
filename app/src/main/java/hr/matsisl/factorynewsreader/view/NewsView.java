package hr.matsisl.factorynewsreader.view;

import java.util.List;

import hr.matsisl.factorynewsreader.model.ArticleModel;

public interface NewsView {
    void arrived(List<ArticleModel> newsList);
}
