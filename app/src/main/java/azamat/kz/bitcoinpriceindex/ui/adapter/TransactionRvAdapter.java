package azamat.kz.bitcoinpriceindex.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import azamat.kz.bitcoinpriceindex.R;
import azamat.kz.bitcoinpriceindex.Utils;
import azamat.kz.bitcoinpriceindex.models.Transaction;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Asus on 24.05.2018.
 */

public class TransactionRvAdapter extends RecyclerView.Adapter<TransactionRvAdapter.TViewHolder> {

    private ArrayList<Transaction> mList;

    private OnItemClickListener onItemClickListener;

    public TransactionRvAdapter() {
        mList = new ArrayList<>();
    }

    @NonNull
    @Override
    public TViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaction, parent, false);
        return new TViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TViewHolder holder, int position) {
        Transaction item = mList.get(position);
        holder.tvAmoint.setText(item.getAmount());
        long date = Long.parseLong(item.getDate() + "000");
        holder.tvDate.setText(Utils.parseLongDateToString(date));
        if (item.getType().equals("1")) {
            holder.tvType.setText("Sell");
        } else {
            holder.tvType.setText("Buy");
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addItems(ArrayList<Transaction> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public Transaction getItem(int position) {
        return mList.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public class TViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_date)
        TextView tvDate;

        @BindView(R.id.tv_type)
        TextView tvType;

        @BindView(R.id.tv_amount)
        TextView tvAmoint;

        public TViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v ->
                    onItemClickListener.onItemClicked(v, getAdapterPosition())
            );
        }
    }
}
