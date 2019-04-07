package com.test.gambit.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.test.gambit.R;
import com.test.gambit.adapters.GamesAdapter;
import com.test.gambit.interfaces.GetGamesListener;
import com.test.gambit.managers.GameManager;
import com.test.gambit.models.GamesModel;
import com.test.gambit.utils.CacheManger;
import com.test.gambit.utils.Network;
import com.test.gambit.utils.SpacesItemDecoration;

import java.io.IOException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GamesTab.OnGamesFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GamesTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GamesTab extends Fragment implements GetGamesListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnGamesFragmentInteractionListener mListener;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private GamesAdapter gamesAdapter;
    public GamesTab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GamesTab.
     */
    // TODO: Rename and change types and number of parameters
    public static GamesTab newInstance(String param1, String param2) {
        GamesTab fragment = new GamesTab();
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
        View view=inflater.inflate(R.layout.fragment_games_tab, container, false);
        progressBar=view.findViewById(R.id.progress_bar);
        recyclerView=view.findViewById(R.id.games_recycle);
        recyclerView .setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false));
        recyclerView.addItemDecoration(new SpacesItemDecoration(4));
        gamesAdapter=new GamesAdapter();
        recyclerView.setAdapter(gamesAdapter);
        getGames();
        return view;
    }

    private void getGames() {

            if (getActivity()!=null) {
                if (Network.isNetworkEnabled(getActivity()) && Network.isNetworkAvailable(getActivity())) {
                    progressBar.setVisibility(View.VISIBLE);
                    new GameManager().getGames(GamesTab.this);
                }else {
                checkForGamesCache();
                }
            }

        }

    private void checkForGamesCache() {

        try {

            List<GamesModel>playerModels=(List<GamesModel>) CacheManger.readObject(getActivity(),CacheManger.GAMES_KEY);
            if (playerModels!=null&&playerModels.size()>0){

                progressBar.setVisibility(View.GONE);

                gamesAdapter.updateList(playerModels);
                mListener.onGamesFragmentInteraction(String.valueOf(playerModels.size() ));
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


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnGamesFragmentInteractionListener) {
            mListener = (OnGamesFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnGamesFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onGetGameSuccess(List<GamesModel> gamesModelList) {
        if (getActivity()!=null) {
            progressBar.setVisibility(View.GONE);
            if (gamesModelList != null) {
                gamesAdapter.updateList(gamesModelList);
                try {
                    CacheManger.writeObject(getActivity(),CacheManger.GAMES_KEY,gamesModelList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mListener.onGamesFragmentInteraction(String.valueOf(gamesModelList.size()));
            }
        }

    }

    @Override
    public void onGetGamesError(String error) {

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
    public interface OnGamesFragmentInteractionListener {
        // TODO: Update argument type and name
        void onGamesFragmentInteraction(String counter);
    }
}
