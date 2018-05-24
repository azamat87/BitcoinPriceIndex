package azamat.kz.bitcoinpriceindex.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;

import azamat.kz.bitcoinpriceindex.R;
import azamat.kz.bitcoinpriceindex.Utils;
import azamat.kz.bitcoinpriceindex.models.Transaction;
import azamat.kz.bitcoinpriceindex.mvp.presenter.TransactionPresenter;
import azamat.kz.bitcoinpriceindex.mvp.view.ITransactionViews;
import azamat.kz.bitcoinpriceindex.ui.BaseFragment;
import azamat.kz.bitcoinpriceindex.ui.activity.TransactionInfoActivity;
import azamat.kz.bitcoinpriceindex.ui.adapter.TransactionRvAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;


public class TransactionFragment extends BaseFragment implements ITransactionViews {


    @BindView(R.id.progress_bar)
    ProgressBar mProgress;

    @BindView(R.id.rv_transaction)
    RecyclerView mRvTransaction;

    private TransactionRvAdapter mAdapter;

    @InjectPresenter
    TransactionPresenter mPresenter;


    public TransactionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transaction, container, false);
        ButterKnife.bind(this, view);
        initUi();

        return view;
    }

    private void initUi() {
        initRv();
        mPresenter.getTransaction();
    }

    private void initRv() {
        mRvTransaction.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new TransactionRvAdapter();
        mRvTransaction.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((item, position) -> {
            Transaction transaction = mAdapter.getItem(position);
            Intent intent = new Intent(getContext(), TransactionInfoActivity.class);
            intent.putExtra(TransactionInfoActivity.EXTRA_ITEM, transaction);
            startActivity(intent);
        });
    }


//    region ==== ITransactionViews ==============================================================

    @Override
    public void setTransaction(ArrayList<Transaction> list) {
        mAdapter.addItems(list);
    }

    @Override
    public void onError(String message) {
        Utils.showToast(getContext(), message);
    }

    @Override
    public void showProgressBar() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgress.setVisibility(View.GONE);
    }


//    endregion
}
