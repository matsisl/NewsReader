package hr.matsisl.factorynewsreader.model;

import com.google.gson.annotations.Expose;

import java.util.Date;
import java.util.List;

public class BbcNewsModel {
    @Expose
    private String status;
    @Expose
    private String source;
    @Expose
    private String sortBy;
    @Expose
    private List<ArticleModel> articles;
    @Expose
    private Date saveDate;

    public Date getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(Date saveDate) {
        this.saveDate = saveDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public List<ArticleModel> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleModel> articles) {
        this.articles = articles;
    }
}
