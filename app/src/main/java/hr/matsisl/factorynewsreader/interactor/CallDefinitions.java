package hr.matsisl.factorynewsreader.interactor;

import hr.matsisl.factorynewsreader.model.BbcNewsModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CallDefinitions {
    //?source=bbc-news&sortBy=top&apiKey=6946d0c07a1c4555a4186bfcade76398
    @GET("articles")
    Call<BbcNewsModel> getNews(@Query("source") String source, @Query("sortBy") String sortBy, @Query("apiKey") String apiKey);
}
