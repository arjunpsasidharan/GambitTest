package com.test.gambit.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import com.test.gambit.R;
import com.test.gambit.adapters.ViewPagerAdapter;
import com.test.gambit.fragments.GamesTab;
import com.test.gambit.fragments.PlayerTab;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements PlayerTab.OnPlayerFragmentInteractionListener, GamesTab.OnGamesFragmentInteractionListener {
    private CoordinatorLayout coordinatorLayout;
    private AppBarLayout appBarLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private String playerCounter="";
    private String gamesCounter="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.parent_rel);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar_layout);
        tabLayout=findViewById(R.id.tabs);
        viewPager=findViewById(R.id.view_pager);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimary));
        tabLayout.setTabTextColors(Color.WHITE,R.color.colorPrimary);

         viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        TabLayout.Tab tab=tabLayout.getTabAt(0);
        Objects.requireNonNull(tab).setCustomView(viewPagerAdapter.getTabView(0,MainActivity.this,false));
        TabLayout.Tab tab2=tabLayout.getTabAt(1);
        Objects.requireNonNull(tab2).setCustomView(viewPagerAdapter.getTabView(1,MainActivity.this,true));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view= tab.getCustomView();
                TextView tv = null;
                if (view != null) {
                    tv = (TextView) view.findViewById(R.id.title_tv);

                tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                RelativeLayout relativeLayout= null;
                    relativeLayout = view.findViewById(R.id.counter_rel);
                    relativeLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view= tab.getCustomView();

                RelativeLayout relativeLayout= null;
                TextView tv = null;
                if (view != null) {
                    tv = (TextView) view.findViewById(R.id.title_tv);

                    tv.setTextColor(getResources().getColor(R.color.tab_title));
                    relativeLayout = view.findViewById(R.id.counter_rel);

                    relativeLayout.setVisibility(View.VISIBLE);
                    TextView counterTv = view.findViewById(R.id.counter_tv);
                    if (tab.getPosition()==0) {

                        counterTv.setText(playerCounter);
                    }else {
                        if (gamesCounter.equalsIgnoreCase("")){
                            relativeLayout.setVisibility(View.GONE);
                        }else {
                            relativeLayout.setVisibility(View.VISIBLE);
                        }
                        counterTv.setText(gamesCounter);
                    }
                }

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.setCurrentItem(0);




    }


    @Override
    public void onGamesFragmentInteraction(String counter) {
        if (counter!=null){
            gamesCounter=counter;

        }

    }

    @Override
    public void onPlayerFragmentInteraction(String counter) {

        if (counter!=null){

            this.playerCounter=counter;        }

    }

}
