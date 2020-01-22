package hr.matsisl.factorynewsreader.interactor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRest {
    private final static String url="https://newsapi.org/v1/";
    private static Retrofit retroFit;

    public static Retrofit getRetrofit(){
        if(retroFit==null)
            retroFit=start();
        return retroFit;
    }

    protected static Retrofit start(){
        OkHttpClient client= new OkHttpClient();
        return new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    private static Gson getGson(){
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }
}
