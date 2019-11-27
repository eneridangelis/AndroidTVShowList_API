package com.example.listagem.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TvShow implements Serializable {

    @SerializedName("original_name")
    private String original_name;
    @SerializedName("first_air_date")
    private String first_air_date;
    @SerializedName("backdrop_path")
    private String backdrop_path;
    @SerializedName("poster_path")
    private String poster_path;
    @SerializedName("overview")
    private String overview;
    @SerializedName("vote_average")
    private Float vote_average;

    public TvShow(String original_name, String first_air_date, String backdrop_path, String poster_path, String overview, Float vote_average){
        this.original_name = original_name;
        this.first_air_date = first_air_date;
        this.backdrop_path = backdrop_path;
        this.poster_path = poster_path;
        this.overview = overview;
        this.vote_average = vote_average;
    }

    public String getOriginal_name(){
        return original_name;
    }

    public void setOriginal_name(String original_name){
        this.original_name = original_name;
    }

    public String getFirst_air_date(){
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date){
        this.first_air_date = first_air_date;
    }

    public String getBackdrop_path(){
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path){
        this.backdrop_path = backdrop_path;
    }

    public String getPoster_path(){
        return poster_path;
    }

    public void setPoster_path(String poster_path){
        this.poster_path = poster_path;
    }

    public String getOverview(){
        return overview;
    }

    public void setOverview(String overview){
        this.overview = overview;
    }

    public Float getVote_average(){
        return vote_average;
    }

    public void setVote_average(Float vote_average){
        this.vote_average = vote_average;
    }
}
