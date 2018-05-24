package azamat.kz.bitcoinpriceindex.ui.fragment;


import android.app.Dialog;
import android.net.ParseException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.anychart.anychart.AnyChart;
import com.anychart.anychart.AnyChartView;
import com.anychart.anychart.Cartesian;
import com.anychart.anychart.CartesianSeriesLine;
import com.anychart.anychart.DataEntry;
import com.anychart.anychart.EnumsAnchor;
import com.anychart.anychart.Mapping;
import com.anychart.anychart.MarkerType;
import com.anychart.anychart.Set;
import com.anychart.anychart.Stroke;
import com.anychart.anychart.TooltipPositionMode;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import azamat.kz.bitcoinpriceindex.ProgressDialog;
import azamat.kz.bitcoinpriceindex.R;
import azamat.kz.bitcoinpriceindex.models.Currency;
import azamat.kz.bitcoinpriceindex.mvp.presenter.CurrencyPresenter;
import azamat.kz.bitcoinpriceindex.mvp.view.ICurrencyView;
import azamat.kz.bitcoinpriceindex.ui.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrencyFragment extends BaseFragment implements ICurrencyView {

    @BindView(R.id.currency_spinner)
    AppCompatSpinner mSpinner;
    @BindView(R.id.period_spinner)
    AppCompatSpinner mSpinnerPeriod;

    @BindView(R.id.tv_current_price)
    TextView mTvPrice;
    @BindView(R.id.any_chart_view)
    AnyChartView mChart;

    @InjectPresenter
    CurrencyPresenter mPresenter;

    private String mSelectedCurrency = "";

    private Dialog mDialog;
    private boolean isUpdateChart = false;

    public CurrencyFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currency, container, false);
        ButterKnife.bind(this, view);
        initUi();
        return view;
    }

    private void initUi() {
        initSpinnerCurrency();
        initSpinnerPeriod();
    }

    private void initChart(ArrayList<DataEntry> seriesData) {
        Cartesian cartesian = AnyChart.line();
        cartesian.setAnimation(true);
        cartesian.setPadding(10d, 20d, 5d, 20d);
        cartesian.getCrosshair().setEnabled(true);
        cartesian.getCrosshair()
                .setYLabel(true)
                .setYStroke((Stroke) null, null, null, null, null);

        cartesian.getTooltip().setPositionMode(TooltipPositionMode.POINT);
        cartesian.getXAxis().getLabels().setPadding(5d, 5d, 5d, 5d);

        Set set = new Set(seriesData);
        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");

        CartesianSeriesLine series1 = cartesian.line(series1Mapping);
        series1.setName("BTC");
        series1.getHovered().getMarkers().setEnabled(true);
        series1.getHovered().getMarkers()
                .setType(MarkerType.CIRCLE)
                .setSize(4d);
        series1.getTooltip()
                .setPosition("right")
                .setAnchor(EnumsAnchor.LEFT_CENTER)
                .setOffsetX(5d)
                .setOffsetY(5d);

        cartesian.getLegend().setEnabled(true);
        cartesian.getLegend().setFontSize(13d);
        cartesian.getLegend().setPadding(0d, 0d, 10d, 0d);

        mChart.setChart(cartesian);
    }

    private void initSpinnerCurrency() {
        String[] currencyArr = getResources().getStringArray(R.array.array_currency);
        ArrayAdapter adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_selected, currencyArr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSelectedCurrency = currencyArr[position];
                mPresenter.getByCurrencyPrice(mSelectedCurrency);
                if (isUpdateChart) {
                    getHistoryChart(mSpinnerPeriod.getSelectedItemPosition());
                } else {
                    isUpdateChart = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void initSpinnerPeriod() {
        String[] periodArr = getResources().getStringArray(R.array.array_period);
        ArrayAdapter adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_selected, periodArr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerPeriod.setAdapter(adapter);
        mSpinnerPeriod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getHistoryChart(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getHistoryChart(int position) {
        Map<String, String> map = new HashMap<>();
        map.put("currency", mSelectedCurrency);
        switch (position) {
            case 0:
                String date7 = pastDate(7);
                map.put("start", date7);
                break;
            case 1:
                String date31 = pastDate(31);
                map.put("start", date31);
                break;

            case 2:
                String date360 = pastDate(360);
                map.put("start", date360);
                break;
        }
        String current = parseLongDateToString(System.currentTimeMillis());
        map.put("end", current);
        mPresenter.getHistory(map);
    }


//    region === ICurrencyView ==================================================================

    @Override
    public void onSuccessCurrentPrice(Currency currency) {
        mTvPrice.setText(getResources().getText(R.string.txt_current_price)  + " " +  currency.getRate() + " " + currency.getCode());
    }

    @Override
    public void onSuccessHistory(ArrayList<DataEntry> seriesData) {
        initChart(seriesData);
    }

    @Override
    public void showProgress() {
        if (mDialog == null) {
            mDialog = new ProgressDialog().showProgressDialog(getContext());
        } else {
            mDialog.show();
        }
    }

    @Override
    public void hideProgress() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

//    endregion

    private String pastDate(int days) {
        Calendar c = Calendar.getInstance();
        long toDay = System.currentTimeMillis();
        Date date = new Date(toDay);
        c.setTime(date);
        c.add(Calendar.DATE,  -days);
        return parseLongDateToString(c.getTimeInMillis());
    }

    private String parseLongDateToString(long date){
        String dateString = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date netDate = new  Date(date);
            dateString = sdf.format(netDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateString;
    }
}
