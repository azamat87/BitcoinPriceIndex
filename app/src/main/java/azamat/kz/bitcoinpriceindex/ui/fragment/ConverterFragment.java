package azamat.kz.bitcoinpriceindex.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;

import azamat.kz.bitcoinpriceindex.R;
import azamat.kz.bitcoinpriceindex.Utils;
import azamat.kz.bitcoinpriceindex.models.Currency;
import azamat.kz.bitcoinpriceindex.mvp.presenter.ConverterPresenter;
import azamat.kz.bitcoinpriceindex.mvp.view.IConverterView;
import azamat.kz.bitcoinpriceindex.ui.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConverterFragment extends BaseFragment implements IConverterView {

    private static final String TAG = "ConverterFragment";
    @BindView(R.id.ed_btc)
    EditText mEdBtc;

    @BindView(R.id.ed_currency)
    EditText mEdCurrency;

    @BindView(R.id.currency_convert_spinner)
    AppCompatSpinner mCurrencySpinner;
    @BindView(R.id.progress_bar_converter)
    ProgressBar mProgress;


    private String mSelectedCurrency = "";
    private double price = 0;

    @InjectPresenter
    ConverterPresenter mPresenter;

    public ConverterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_converter, container, false);
        ButterKnife.bind(this, view);
        initSpinner();
        initEd();
        return view;
    }

    private void initSpinner() {
        String[] currencyArr = getResources().getStringArray(R.array.array_currency);
        ArrayAdapter adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_selected, currencyArr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCurrencySpinner.setAdapter(adapter);
        mCurrencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSelectedCurrency = currencyArr[position];
                mPresenter.getByCurrencyPrice(mSelectedCurrency);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initEd() {
        mEdBtc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mEdBtc.isFocused()) {
                    if (s.length() != 0) {
                        double inputNum = Double.parseDouble(s.toString());
                        long total = (long) (inputNum * price);
                        mEdCurrency.setText(String.valueOf(total));
                    }else if(s.length() == 0){
                        mEdCurrency.setText("0.0");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEdCurrency.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mEdCurrency.isFocused()) {
                    if (s.length() != 0) {
                        double inputNum = Double.parseDouble(s.toString());
                        long total = (long) (inputNum/price);
                        mEdBtc.setText(String.valueOf(total));
                    } else if (s.length() == 0) {
                        mEdBtc.setText("0.0");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @OnClick(R.id.btn_clear_converter)
    public void onClickClear() {
        mEdBtc.setText("");
        mEdCurrency.setText("");
    }

//    region ==== IConverterView =================================================================

    @Override
    public void onSuccessCurrentPrice(Currency currency) {
        price = currency.getRate_float();
        if (mEdBtc.getText().length() != 0) {
            double count = Double.parseDouble(mEdBtc.getText().toString());
            long total = (long) (count * price);
            mEdCurrency.setText(String.valueOf(total));
        } else {
            mEdCurrency.setText(String.valueOf(price));
        }
    }

    @Override
    public void showProgress() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgress.setVisibility(View.GONE);
    }

    @Override
    public void onError(String m) {
        Utils.showToast(getContext(), m);
    }

//    endregion
}
