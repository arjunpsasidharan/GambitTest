package com.test.gambit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.gambit.R;
import com.test.gambit.models.PlayerModel;

import java.util.ArrayList;
import java.util.Collections;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class PlayerAdapter extends RecyclerView.Adapter  {
    private  List<PlayerModel> playerModels;
    private Context mCOntext;
    public static final int SHOWCASE=2;
    public static final int PLAYER=3;


    @Override
    public int getItemCount() {
        if (playerModels==null){
            return 0;
        }else {

            return playerModels.size();
        }
    }

    public PlayerAdapter(){
        playerModels=new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       final View itemView;
       this.mCOntext=parent.getContext();
        switch (viewType){

            case SHOWCASE:

                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.showcase_layout, parent, false);
                return new ShowCaseHolder(itemView);
            case PLAYER:

                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.player_layout, parent, false);
                return new PlayerHolder(itemView);

        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PlayerModel playerModel=playerModels.get(position);
        switch (playerModel.getType()){

            case SHOWCASE:
                break;
            default:

                ((PlayerHolder)holder).bind(playerModel);

                break;

        }


    }

    @Override
    public int getItemViewType(int position) {
        switch (playerModels.get(position).getType()){


            case SHOWCASE:
                return SHOWCASE;

            default:return PLAYER;

        }
    }



    public class ShowCaseHolder extends RecyclerView.ViewHolder{
   ;

        public ShowCaseHolder(@NonNull View itemView) {
            super(itemView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }




    public class PlayerHolder extends RecyclerView.ViewHolder{
        private TextView firstnameTv,lastNameTv,teamTV,teamIdTv,cityTv,positionTv,teamFullTv;
        public PlayerHolder(@NonNull View itemView) {
            super(itemView);
            firstnameTv=itemView.findViewById(R.id.first_name_tv);
            lastNameTv=itemView.findViewById(R.id.last_name_tv);
            teamTV=itemView.findViewById(R.id.team_name_tv);
            teamIdTv=itemView.findViewById(R.id.team_id_tv);
            cityTv=itemView.findViewById(R.id.team_city_name_tv);
            positionTv=itemView.findViewById(R.id.position_tv);
            teamFullTv=itemView.findViewById(R.id.team_full_name_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        public void bind(final PlayerModel playerModel) {
           firstnameTv.setText(playerModel.getFirstName());

           lastNameTv.setText(playerModel.getLastName());
           cityTv.setText(playerModel.getTeamModel().getCity());

           teamTV.setText(playerModel.getTeamModel().getAbbreviation());
           teamFullTv.setText(playerModel.getTeamModel().getFullName() );
           teamIdTv.setText("#"+playerModel.getTeamModel().getId());
           positionTv.setText("Position - "+playerModel.getPosition());

        }
    }


    public void updateList(List<PlayerModel> playerModelList){

            playerModels=playerModelList;

       notifyDataSetChanged();
    }


}
