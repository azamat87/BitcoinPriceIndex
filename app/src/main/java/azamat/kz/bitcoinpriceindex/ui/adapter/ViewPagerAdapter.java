package azamat.kz.bitcoinpriceindex.ui.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import azamat.kz.bitcoinpriceindex.ui.BaseFragment;

/**
 * Created by Asus on 23.05.2018.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList fragments = new ArrayList<BaseFragment>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(BaseFragment fragment) {
        fragments.add(fragment);
    }

    @Override
    public BaseFragment getItem(int position) {
        return (BaseFragment) fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
