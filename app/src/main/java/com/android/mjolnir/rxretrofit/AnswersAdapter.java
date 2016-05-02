package com.android.mjolnir.rxretrofit;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.mjolnir.rxretrofit.model.StackAnswers;
import java.util.List;

/**
 * Created by sam_chordas on 4/29/16.
 */
public class AnswersAdapter extends RecyclerView.Adapter<AnswersAdapter.ViewHolder>{

  private List<StackAnswers.Item> items;

  public AnswersAdapter(List<StackAnswers.Item> items){
    this.items = items;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_answer, parent, false);

    return new ViewHolder(itemView);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    StackAnswers.Item item = items.get(position);

    holder.user.setText(item.getOwner().getName());
    holder.isAccepted.setText(String.valueOf(item.getAccepted()));
    holder.score.setText(item.getScore());
  }

  @Override public int getItemCount() {
    return 0;
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    public TextView user;
    public TextView isAccepted;
    public TextView score;
    public ViewHolder(View v){
      super(v);
      user = (TextView) v.findViewById(R.id.user);
      isAccepted = (TextView) v.findViewById(R.id.is_accepted);
      score = (TextView) v.findViewById(R.id.score);
    }
  }

  public void swapList(List<StackAnswers.Item> items){
    this.items = items;
    notifyDataSetChanged();
  }
}
