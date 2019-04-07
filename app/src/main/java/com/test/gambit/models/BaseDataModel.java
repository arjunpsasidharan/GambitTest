package com.test.gambit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class BaseDataModel<T> implements Serializable {

    @SerializedName("data")
    @Expose
    private List<T> data = null;
    @SerializedName("meta")
    @Expose
    private MetaModel meta;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public MetaModel getMeta() {
        return meta;
    }

    public void setMeta(MetaModel meta) {
        this.meta = meta;
    }

}

