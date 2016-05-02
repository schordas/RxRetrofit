package com.android.mjolnir.rxretrofit;

import com.android.mjolnir.rxretrofit.model.StackAnswers;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by sam_chordas on 4/29/16.
 */
public final class StackService {
  public interface StackAPI {
    @GET("users/{ids}/answers?order=desc&sort=activity&site=stackoverflow")
    Observable<StackAnswers> getAnswers(@Path("ids") String ids);
  }

}
