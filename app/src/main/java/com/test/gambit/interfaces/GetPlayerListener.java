package com.test.gambit.interfaces;

import com.test.gambit.models.PlayerModel;

import java.util.List;

public interface GetPlayerListener {
    void onGetPlayersSuccess(List<PlayerModel> playerModelList,boolean isFromSearch);
    void onGetPLayersError(String error);
}
