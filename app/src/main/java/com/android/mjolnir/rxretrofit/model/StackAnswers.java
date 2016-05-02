package com.android.mjolnir.rxretrofit.model;

import java.util.List;

/**
 * Created by sam_chordas on 4/29/16.
 */
public class StackAnswers {
  private List<Item> items;

  public List<Item> getItems(){
    return items;
  }

  public void setItems(List<Item> items){
    this.items = items;
  }
  public static class Item{

    private int score;
    private boolean is_accepted;
    private Owner owner;

    public int getScore(){
      return score;
    }

    public Owner getOwner(){
      return owner;
    }

    public boolean getAccepted(){
      return is_accepted;
    }

    public void setScore(int score){
      this.score = score;
    }

    public void setAccepted(boolean isAccepted){
      this.is_accepted = isAccepted;
    }

    public void setOwner(Owner owner){
      this.owner = owner;
    }

    public static class Owner{
      private String display_name;

      public String getName(){
        return display_name;
      }

      public void setName(String display_name){
        this.display_name = display_name;
      }
    }
  }
}
