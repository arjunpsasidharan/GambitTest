package com.test.gambit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GamesModel implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("home_team_score")
    @Expose
    private Integer homeTeamScore;
    @SerializedName("visitor_team_score")
    @Expose
    private Integer visitorTeamScore;
    @SerializedName("season")
    @Expose
    private Integer season;
    @SerializedName("period")
    @Expose
    private Integer period;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("postseason")
    @Expose
    private Boolean postseason;
    @SerializedName("home_team")
    @Expose
    private HomeTeamModel homeTeam;
    @SerializedName("visitor_team")
    @Expose
    private VisitorTeamModel visitorTeam;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getHomeTeamModelScore() {
        return homeTeamScore;
    }

    public void setHomeTeamModelScore(Integer homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public Integer getVisitorTeamModelScore() {
        return visitorTeamScore;
    }

    public void setVisitorTeamModelScore(Integer visitorTeamScore) {
        this.visitorTeamScore = visitorTeamScore;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getPostseason() {
        return postseason;
    }

    public void setPostseason(Boolean postseason) {
        this.postseason = postseason;
    }

    public HomeTeamModel getHomeTeamModel() {
        return homeTeam;
    }

    public void setHomeTeamModel(HomeTeamModel homeTeam) {
        this.homeTeam = homeTeam;
    }

    public VisitorTeamModel getVisitorTeamModel() {
        return visitorTeam;
    }

    public void setVisitorTeamModel(VisitorTeamModel visitorTeam) {
        this.visitorTeam = visitorTeam;
    }

}
