package com.test.gambit.managers;

import android.content.Context;

import com.test.gambit.interfaces.GetPlayerListener;
import com.test.gambit.models.BaseDataModel;
import com.test.gambit.models.PlayerModel;
import com.test.gambit.restServices.RestClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayerManager {



    public void getPlayers( int pageNumber,final GetPlayerListener listener){



        RestClient.GitApiInterface service = RestClient.getClient();
        Call<BaseDataModel<PlayerModel>> call = service.getPlayers(String.valueOf(pageNumber),"25");

        call.enqueue(new Callback<BaseDataModel<PlayerModel>>() {
            @Override
            public void onResponse(Call<BaseDataModel<PlayerModel>> call, Response<BaseDataModel<PlayerModel>> response) {
                try {
                    if (response.isSuccessful()) {
                        if (response.body()!=null&&response.body().getData()!=null&&response.body().getData().size()>0){
                            listener.onGetPlayersSuccess(response.body().getData(),false);
                        }else {
                            listener.onGetPlayersSuccess(null,false);

                        }

                    }else {
                        listener.onGetPLayersError(""+response.code());
                    }
                } catch (Exception e) {
                    listener.onGetPLayersError(e.getLocalizedMessage());
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<BaseDataModel<PlayerModel>> call, Throwable t) {
                listener.onGetPLayersError(t.getLocalizedMessage());

            }
        });
    }

    public void searchPlayers(String searchTerm,final GetPlayerListener listener){



        RestClient.GitApiInterface service = RestClient.getClient();
        Call<BaseDataModel<PlayerModel>> call = service.searchPlayers("0","25",searchTerm);

        call.enqueue(new Callback<BaseDataModel<PlayerModel>>() {
            @Override
            public void onResponse(Call<BaseDataModel<PlayerModel>> call, Response<BaseDataModel<PlayerModel>> response) {
                try {
                    if (response.isSuccessful()) {
                        if (response.body()!=null&&response.body().getData()!=null&&response.body().getData().size()>0){
                            listener.onGetPlayersSuccess(response.body().getData(),true);
                        }else {
                            listener.onGetPlayersSuccess(null,true);

                        }

                    }else {
                        listener.onGetPLayersError(""+response.code());
                    }
                } catch (Exception e) {
                    listener.onGetPLayersError(e.getLocalizedMessage());
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<BaseDataModel<PlayerModel>> call, Throwable t) {
                listener.onGetPLayersError(t.getLocalizedMessage());

            }
        });
    }


}
