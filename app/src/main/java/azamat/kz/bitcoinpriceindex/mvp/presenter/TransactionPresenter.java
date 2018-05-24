package azamat.kz.bitcoinpriceindex.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import azamat.kz.bitcoinpriceindex.App;
import azamat.kz.bitcoinpriceindex.interactor.CurrencyInteractor;
import azamat.kz.bitcoinpriceindex.models.Transaction;
import azamat.kz.bitcoinpriceindex.mvp.view.ITransactionViews;

/**
 * Created by Asus on 23.05.2018.
 */
@InjectViewState
public class TransactionPresenter extends MvpPresenter<ITransactionViews> {

    private static final String TAG = "TransactionPresenter";

    @Inject
    CurrencyInteractor mInteractor;

    public TransactionPresenter() {
        App.applicationComponent().plus().inject(this);
    }

    public void getTransaction() {
        getViewState().showProgressBar();
        mInteractor.getHistoryTransaction()
                .subscribe(this::handlerSuccess, this::handlerError);
    }

    private void handlerSuccess(List<Transaction> list) {
        ArrayList<Transaction> arrList = new ArrayList<>();
        for (Transaction transaction : list) {
            if (arrList.size() < 500) {
                arrList.add(transaction);
            } else {
                break;
            }
        }
        getViewState().setTransaction(arrList);
        getViewState().hideProgressBar();
    }

    private void handlerError(Throwable throwable) {
        getViewState().onError(throwable.getLocalizedMessage());
        getViewState().hideProgressBar();
    }
}
