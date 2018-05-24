package azamat.kz.bitcoinpriceindex.mvp.view;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

import azamat.kz.bitcoinpriceindex.models.Transaction;

/**
 * Created by Asus on 23.05.2018.
 */

public interface ITransactionViews extends MvpView {

    void setTransaction(ArrayList<Transaction> list);

    void onError(String message);

    void showProgressBar();

    void hideProgressBar();

}
