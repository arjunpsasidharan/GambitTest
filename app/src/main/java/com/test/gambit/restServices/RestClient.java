package com.test.gambit.restServices;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.gambit.models.BaseDataModel;
import com.test.gambit.models.GamesModel;
import com.test.gambit.models.PlayerModel;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RestClient {
    private static final String GET_PLAYERS = "players";
    private static final String GET_GAMES = "games";

        private static GitApiInterface gitApiInterface ;

        public static Retrofit client;


        public static GitApiInterface getClient() {
                    Gson gson = new GsonBuilder()
                            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                            .create();
                    OkHttpClient okClient = new OkHttpClient.Builder()
                            .connectTimeout(10, TimeUnit.SECONDS)
                            .writeTimeout(20,TimeUnit.SECONDS)
                            .readTimeout(20,TimeUnit.SECONDS)
                            .build();

                    client = new Retrofit.Builder()
                            .baseUrl("https://www.balldontlie.io/api/v1/")
                            .client(okClient)
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();
                    gitApiInterface = client.create(GitApiInterface.class);

                    return gitApiInterface ;
                }





        public static Retrofit retrofit(){
            return client;
        }

        public interface GitApiInterface {
            @GET(GET_PLAYERS)
            Call<BaseDataModel<PlayerModel>> getPlayers(@Query("page") String page,@Query("per_page") String perPage);
            @GET(GET_PLAYERS)
            Call<BaseDataModel<PlayerModel>> searchPlayers(@Query("page") String page,@Query("per_page") String perPage,@Query("search") String search);
            @GET(GET_GAMES)
            Call<BaseDataModel<GamesModel>> getGames();


        }



    }
