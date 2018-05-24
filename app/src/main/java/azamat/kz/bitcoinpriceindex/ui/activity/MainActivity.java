package azamat.kz.bitcoinpriceindex.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import azamat.kz.bitcoinpriceindex.R;
import azamat.kz.bitcoinpriceindex.ui.CustomViewPager;
import azamat.kz.bitcoinpriceindex.ui.adapter.ViewPagerAdapter;
import azamat.kz.bitcoinpriceindex.ui.fragment.ConverterFragment;
import azamat.kz.bitcoinpriceindex.ui.fragment.CurrencyFragment;
import azamat.kz.bitcoinpriceindex.ui.fragment.TransactionFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.vp_main)
    CustomViewPager mViewPager;
    @BindView(R.id.main_bottom_nav_menu)
    BottomBar mBottomBar;

    private ViewPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initUi();
    }

    private void initUi() {
        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mPagerAdapter.addFragment(new CurrencyFragment());
        mPagerAdapter.addFragment(new TransactionFragment());
        mPagerAdapter.addFragment(new ConverterFragment());
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mPagerAdapter);
        initNavigation();
    }

    private void initNavigation() {
        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                switch (tabId) {
                    case R.id.action_main:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.action_transaction:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.action_converter:
                        mViewPager.setCurrentItem(2);
                        break;
                }
            }
        });
    }
}
