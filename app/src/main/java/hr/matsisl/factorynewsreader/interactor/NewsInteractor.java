package hr.matsisl.factorynewsreader.interactor;

public interface NewsInteractor {
    void getNews();
    void setNewsListener(NewsInteractorListener newsListener);
}
