package com.test.gambit.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test.gambit.R;
import com.test.gambit.activities.MainActivity;
import com.test.gambit.fragments.GamesTab;
import com.test.gambit.fragments.PlayerTab;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;




public class ViewPagerAdapter extends FragmentStatePagerAdapter {


    SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();
    private String[] tabTitles={"Players","Games"};

    /**
     * Create pager adapter
     *
     * @param fm
     */
    public ViewPagerAdapter( FragmentManager fm) {
        super(fm);


    }
    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if(position == 0){ // if the position is 0 we are returning the First tab
            return new PlayerTab();
        } else {
            return new GamesTab();
        }

    }



    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }

    /**
     * On each Fragment instantiation we are saving the reference of that Fragment in a Map
     * It will help us to retrieve the Fragment by position
     *
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    /**
     * Remove the saved reference from our Map on the Fragment destroy
     *
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }
    /**
     * Get the Fragment by position
     *
     * @param position tab position of the fragment
     * @return
     */
    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }

    public View getTabView(int position, Context context,boolean isCoubterVisible) {
        // Given you have a custom layout in `res/layout/custom_tab.xml` with a TextView and ImageView
        View v = LayoutInflater.from(context).inflate(R.layout.custom_tab_layout, null);
        TextView tv = (TextView) v.findViewById(R.id.title_tv);
        tv.setText(tabTitles[position]);
        RelativeLayout relativeLayout=v.findViewById(R.id.counter_rel);

        if (isCoubterVisible){
            tv.setTextColor(context.getResources().getColor(R.color.tab_title));
        }else {
            tv.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        }

        return v;
    }

}
