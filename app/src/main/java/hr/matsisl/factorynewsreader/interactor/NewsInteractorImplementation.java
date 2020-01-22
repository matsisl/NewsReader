package hr.matsisl.factorynewsreader.interactor;

import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;

import hr.matsisl.factorynewsreader.model.BbcNewsModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewsInteractorImplementation implements NewsInteractor {
    NewsInteractorListener newsInteractorListener;
    private static final String filename="/storage/emulated/0/BBC_News/BBC_News.txt";

    @Override
    public void getNews() {
        createFile();
        BbcNewsModel news;
        news=getNewsFromLocal();
        if(news==null || checkTimeOfSave(news.getSaveDate())==true)
            getNewsFromApi();
        else {
            newsInteractorListener.arrivedNews(news.getArticles());
        }
    }

    @Override
    public void setNewsListener(NewsInteractorListener newsListener) {
        newsInteractorListener=newsListener;
    }

    private void getNewsFromApi(){
        CallDefinitions calls = RetrofitRest.getRetrofit().create(CallDefinitions.class);
        //?source=bbc-news&sortBy=top&apiKey=6946d0c07a1c4555a4186bfcade76398
        String source="bbc-news";
        String sortBy="top";
        String apiKey="6946d0c07a1c4555a4186bfcade76398";
        Call<BbcNewsModel> call = calls.getNews(source,sortBy,apiKey);
        call.enqueue(new Callback<BbcNewsModel>() {
            @Override
            public void onResponse(Call<BbcNewsModel> call, Response<BbcNewsModel> response) {
                Date now= Calendar.getInstance().getTime();
                BbcNewsModel news = response.body();
                news.setSaveDate(now);
                for (int i =1;i<news.getArticles().size(); i++){
                    news.getArticles().get(i-1).setIdArticle(i);
                }
                saveNewsToLocal(news);
                newsInteractorListener.arrivedNews(news.getArticles());
            }

            @Override
            public void onFailure(Call<BbcNewsModel> call, Throwable t) {
                Log.d("API-ja",t.getMessage());
            }
        });
    }
    private BbcNewsModel getNewsFromLocal(){
        return readNewsFromLocal();
    }

    private void saveNewsToLocal(BbcNewsModel news){
        Gson gson = new Gson();
        String data = gson.toJson(news);

        try{
            FileOutputStream fos = new FileOutputStream(filename);
            fos.write(data.getBytes());
            fos.flush();
            fos.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private BbcNewsModel readNewsFromLocal(){
        BbcNewsModel news=null;
        try {
            FileInputStream fis=new FileInputStream(filename);
            InputStreamReader isr=new InputStreamReader(fis);
            BufferedReader br=new BufferedReader(isr);
            StringBuilder sb= new StringBuilder();
            String line;
            while ((line=br.readLine())!=null){
                sb.append(line);
            }
            fis.close();
            String json = sb.toString();
            Gson gson=new Gson();
            news = gson.fromJson(json,BbcNewsModel.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return news;
    }

    private boolean checkTimeOfSave(Date saveDate){
        long now = System.currentTimeMillis();
        long saveTime = saveDate.getTime();
        long dif = Math.abs(now-saveTime);
        if(dif>5*60*1000){
            return true;
        }
        else {
            return false;
        }
    }

    private void createFile(){
        File file=new File(Environment.getExternalStorageDirectory(),"BBC_News");
        if(file.exists()==false) {
            file.mkdir();
        }
        File txtFile=new File(file, "BBC_News.txt");
        System.out.println(txtFile.getPath().toString());
        if(txtFile.exists()==false) {
            try {
                txtFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
