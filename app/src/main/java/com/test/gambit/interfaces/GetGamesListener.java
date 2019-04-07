package com.test.gambit.interfaces;

import com.test.gambit.managers.GameManager;
import com.test.gambit.models.GamesModel;

import java.util.List;

public interface GetGamesListener {
    void onGetGameSuccess(List<GamesModel>gamesModelList);
    void onGetGamesError(String error);
}
