package com.test.gambit.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.test.gambit.R;
import com.test.gambit.adapters.PlayerAdapter;
import com.test.gambit.interfaces.GetPlayerListener;
import com.test.gambit.managers.PlayerManager;
import com.test.gambit.models.PlayerModel;
import com.test.gambit.utils.CacheManger;
import com.test.gambit.utils.Network;
import com.test.gambit.utils.SpacesItemDecoration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PlayerTab.OnPlayerFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlayerTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayerTab extends Fragment implements GetPlayerListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private PlayerAdapter playerAdapter;
    private List<PlayerModel>playerModelListMain;
    private int pageNumber=0;
    private ProgressBar progressBar;
    private EditText searchEdit;
    private TextView cleaTv;


    private OnPlayerFragmentInteractionListener mListener;

    public PlayerTab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlayerTab.
     */
    // TODO: Rename and change types and number of parameters
    public static PlayerTab newInstance(String param1, String param2) {
        PlayerTab fragment = new PlayerTab();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_player_tab, container, false);
        recyclerView=view.findViewById(R.id.player_recycler);
        progressBar=view.findViewById(R.id.progress_bar);
        searchEdit=view.findViewById(R.id.search_et);
        cleaTv=view.findViewById(R.id.clear_tv);

        recyclerView .setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false));
        recyclerView.addItemDecoration(new SpacesItemDecoration(4));
        playerAdapter=new PlayerAdapter();
        recyclerView.setAdapter(playerAdapter);
        playerModelListMain=new ArrayList<>();
        getPlayers();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                  if (cleaTv.getVisibility()!=View.VISIBLE) {
                      pageNumber++;
                      getPlayers();
                  }
                }
            }
        });

        cleaTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cleaTv.setVisibility(View.GONE);
                searchEdit.setText("");
                if (playerAdapter!=null){
                    if (playerModelListMain!=null){
                        playerAdapter.updateList(playerModelListMain);
                        int min=0;
                        if (playerModelListMain.size()>5){
                          min=1;
                        }
                        mListener.onPlayerFragmentInteraction(String.valueOf(playerModelListMain.size()-min));
                    }
                }
            }
        });
        searchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>=3) {
                    searchPlayer(s);
                    cleaTv.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return view;
    }

    private void searchPlayer(CharSequence s) {
        if (getActivity()!=null) {
            if (Network.isNetworkEnabled(getActivity()) && Network.isNetworkAvailable(getActivity())) {
                progressBar.setVisibility(View.VISIBLE);
                new  PlayerManager().searchPlayers(s.toString(),PlayerTab.this);
            }else {
                progressBar.setVisibility(View.GONE);
            }
        }
    }

    private void getPlayers() {
        if (getActivity()!=null) {
            if (Network.isNetworkEnabled(getActivity()) && Network.isNetworkAvailable(getActivity())) {
                progressBar.setVisibility(View.VISIBLE);
               new  PlayerManager().getPlayers(pageNumber,PlayerTab.this);
            }else {
                checkForPlayerCache();
            }
        }

    }

    private void checkForPlayerCache() {

        try {
            int minus=0;
            List<PlayerModel>playerModels=(List<PlayerModel>)CacheManger.readObject(getActivity(),CacheManger.PLAYER_KEY);
            if (playerModels!=null&&playerModels.size()>0){
                if (playerModels.size() > 5) {
                    minus = 1;
                }
                progressBar.setVisibility(View.GONE);

                playerAdapter.updateList(playerModels);
                mListener.onPlayerFragmentInteraction(String.valueOf(playerModels.size() - minus));
            }else {
                progressBar.setVisibility(View.GONE);
            }
        } catch (IOException e) {
            progressBar.setVisibility(View.GONE);

            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            progressBar.setVisibility(View.GONE);

            e.printStackTrace();
        }
    }

    // TODO: Rename method, update argument and hook method into UI event



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPlayerFragmentInteractionListener) {
            mListener = (OnPlayerFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnPlayerFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onGetPlayersSuccess(List<PlayerModel> playerModelList,boolean isFromSearch) {
        progressBar.setVisibility(View.GONE);
            int minus = 0;
            if (getActivity() != null) {
                if (playerModelList != null) {
                    if (playerAdapter != null) {
                        if (!isFromSearch) {

                            boolean isAddthrid = false;
                        if (playerModelListMain.size() == 0) {
                            isAddthrid = true;
                        }
                        playerModelListMain.addAll(playerModelList);
                        if (isAddthrid && playerModelListMain.size() > 5) {
                            minus = 1;
                            PlayerModel playerModel = new PlayerModel();
                            playerModel.setType(PlayerAdapter.SHOWCASE);

                            playerModelListMain.add(3, playerModel);
                        }
                        if (minus == 0 && playerModelListMain.size() > 5) {
                            minus = 1;
                        }
                        playerAdapter.updateList(playerModelListMain);
                            try {
                                CacheManger.writeObject(getActivity(),CacheManger.PLAYER_KEY,playerModelListMain);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            mListener.onPlayerFragmentInteraction(String.valueOf(playerModelListMain.size() - minus));
                    }  else {
                            playerAdapter.updateList(playerModelList);

                            mListener.onPlayerFragmentInteraction(String.valueOf(playerModelList.size() ));

                        }

                }
            }
        }
    }



    @Override
    public void onGetPLayersError(String error) {
        if (getActivity()!=null) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "" + error, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnPlayerFragmentInteractionListener {
        // TODO: Update argument type and name
        void onPlayerFragmentInteraction(String counter);
    }
}
