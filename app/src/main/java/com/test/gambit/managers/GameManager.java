package com.test.gambit.managers;

import com.test.gambit.interfaces.GetGamesListener;
import com.test.gambit.models.BaseDataModel;
import com.test.gambit.models.GamesModel;
import com.test.gambit.restServices.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameManager {

    public void getGames( final GetGamesListener listener){



        RestClient.GitApiInterface service = RestClient.getClient();
        Call<BaseDataModel<GamesModel>> call = service.getGames();

        call.enqueue(new Callback<BaseDataModel<GamesModel>>() {
            @Override
            public void onResponse(Call<BaseDataModel<GamesModel>> call, Response<BaseDataModel<GamesModel>> response) {
                try {
                    if (response.isSuccessful()) {
                        if (response.body()!=null&&response.body().getData()!=null&&response.body().getData().size()>0){
                            listener.onGetGameSuccess(response.body().getData());
                        }else {
                            listener.onGetGameSuccess(null);

                        }

                    }else {
                        listener.onGetGamesError(""+response.code());
                    }
                } catch (Exception e) {
                    listener.onGetGamesError(e.getLocalizedMessage());
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<BaseDataModel<GamesModel>> call, Throwable t) {
                listener.onGetGamesError(t.getLocalizedMessage());

            }
        });
    }

}
