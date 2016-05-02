package com.android.mjolnir.rxretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.android.mjolnir.rxretrofit.model.StackAnswers;

import java.util.List;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import com.android.mjolnir.rxretrofit.StackService.StackAPI;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

  private AnswersAdapter answersAdapter;
  private List<StackAnswers.Item> items;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


    Retrofit retrofit = new Retrofit.Builder()
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .baseUrl(getString(R.string.API_BASE_URL))
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    answersAdapter = new AnswersAdapter(items);
    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.answers_list);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(answersAdapter);
    StackAPI service = retrofit.create(StackAPI.class);

    Observable<StackAnswers> observable = service.getAnswers("3700499");

    observable
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<StackAnswers>() {
          @Override public void call(StackAnswers stackAnswers) {
            items = stackAnswers.getItems();
            Log.i(getClass().getSimpleName(), "I know I'm crazy but come on");
            swap(items);
          }
        }, new Action1<Throwable>() {
          @Override public void call(Throwable throwable) {
            Log.e(getClass().getSimpleName(), "Error: " + throwable);
          }
        });

  }

  protected void swap(List<StackAnswers.Item> items){
    answersAdapter.swapList(items);
  }
}
