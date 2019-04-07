package com.test.gambit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.gambit.R;
import com.test.gambit.models.GamesModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GamesAdapter extends RecyclerView.Adapter  {
    private List<GamesModel> gamesModels;
    private Context mCOntext;



    @Override
    public int getItemCount() {
        if (gamesModels==null){
            return 0;
        }else {

            return gamesModels.size();
        }
    }

    public GamesAdapter(){
        gamesModels=new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView;
        this.mCOntext=parent.getContext();
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.games_layout, parent, false);
                return new PlayerHolder(itemView);



    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        GamesModel gamesModel=gamesModels.get(position);
         ((PlayerHolder)holder).bind(gamesModel);

    }


    public class PlayerHolder extends RecyclerView.ViewHolder{
        private TextView leftTeamShortTv,leftTeamBigTv,rightTeamShortTv,righTeamBigTv;
        public PlayerHolder(@NonNull View itemView) {
            super(itemView);
            righTeamBigTv=itemView.findViewById(R.id.right_team_big_tv);
            rightTeamShortTv=itemView.findViewById(R.id.right_team_short_tv);
            leftTeamBigTv=itemView.findViewById(R.id.left_team_big_tv);
            leftTeamShortTv=itemView.findViewById(R.id.left_team_short_tv);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        public void bind(final GamesModel gamesModel) {

            leftTeamShortTv.setText(gamesModel.getHomeTeamModel().getAbbreviation());
            leftTeamBigTv.setText(gamesModel.getHomeTeamModel().getFullName());
            rightTeamShortTv.setText(gamesModel.getVisitorTeamModel().getAbbreviation());
            righTeamBigTv.setText(gamesModel.getVisitorTeamModel().getFullName());

        }
    }


    public void updateList(List<GamesModel> gamesModels){

        this.gamesModels=gamesModels;

        notifyDataSetChanged();
    }

}
