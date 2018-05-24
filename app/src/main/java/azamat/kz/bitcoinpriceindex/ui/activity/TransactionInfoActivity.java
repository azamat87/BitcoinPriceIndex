package azamat.kz.bitcoinpriceindex.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import azamat.kz.bitcoinpriceindex.R;
import azamat.kz.bitcoinpriceindex.Utils;
import azamat.kz.bitcoinpriceindex.models.Transaction;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionInfoActivity extends AppCompatActivity {

    public static final String EXTRA_ITEM = "extra_item";

    @BindView(R.id.tv_id_info)
    TextView tvId;

    @BindView(R.id.tv_date_info)
    TextView tvDate;

    @BindView(R.id.tv_type_info)
    TextView tvType;

    @BindView(R.id.tv_amount_info)
    TextView tvAmount;

    @BindView(R.id.tv_price_info)
    TextView tvPrice;

    @BindView(R.id.tv_total_info)
    TextView tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_info);
        ButterKnife.bind(this);

        getInformationFromIntent();
    }

    private void getInformationFromIntent() {
        if (getIntent().getExtras() != null) {
            Transaction transaction = getIntent().getExtras().getParcelable(EXTRA_ITEM);
            setInfoTransaction(transaction);
        }
    }

    public void setInfoTransaction(Transaction info) {
        tvId.setText("ID: " + info.getTid());
        tvAmount.setText(info.getAmount());
        tvDate.setText(Utils.parseLongDateToString(Long.parseLong(info.getDate()+"000")));
        if (info.getType().equals("1")) {
            tvType.setText("Sell");
        } else {
            tvType.setText("Buy");
        }
        tvPrice.setText(info.getPrice() + " $");
        float total = Float.parseFloat(info.getPrice()) * Float.parseFloat(info.getAmount());
        tvTotal.setText(String.valueOf(total) + " $");
    }
}
