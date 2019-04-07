package com.test.gambit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.test.gambit.adapters.PlayerAdapter;

import java.io.Serializable;

public class PlayerModel implements Serializable {



    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("height_feet")
    @Expose
    private Integer heightFeet;
    @SerializedName("height_inches")
    @Expose
    private Integer heightInches;
    @SerializedName("weight_pounds")
    @Expose
    private Integer weightPounds;
    @SerializedName("team")
    @Expose
    private TeamModel team;

    private int type= PlayerAdapter.PLAYER;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getHeightFeet() {
        return heightFeet;
    }

    public void setHeightFeet(Integer heightFeet) {
        this.heightFeet = heightFeet;
    }

    public Integer getHeightInches() {
        return heightInches;
    }

    public void setHeightInches(Integer heightInches) {
        this.heightInches = heightInches;
    }

    public Integer getWeightPounds() {
        return weightPounds;
    }

    public void setWeightPounds(Integer weightPounds) {
        this.weightPounds = weightPounds;
    }

    public TeamModel getTeamModel() {
        return team;
    }

    public void setTeamModel(TeamModel team) {
        this.team = team;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}