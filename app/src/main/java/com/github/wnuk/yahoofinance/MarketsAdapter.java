package com.github.wnuk.yahoofinance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.github.wnuk.yahoofinance.data.Market;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Recycle list adapter
 */
public class MarketsAdapter extends RecyclerView.Adapter<MarketsAdapter.MarketsAdapterViewHolder> {

    ArrayList<Market> data;

    public void setData(ArrayList<Market> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    private final MarketAdapterOnClickHandler mClickHandler;

    public MarketsAdapter(MarketAdapterOnClickHandler mClickHandler) {
        this.mClickHandler = mClickHandler;
    }

    public interface MarketAdapterOnClickHandler {
        void onClick(Market market);
    }

    public class MarketsAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final TextView mMarketTextView;

        public MarketsAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            mMarketTextView = (TextView) itemView.findViewById(R.id.tv_market_data);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPostition = getAdapterPosition();
            mClickHandler.onClick(data.get(adapterPostition));
        }
    }

    @NonNull
    @Override
    public MarketsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.market_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        return new MarketsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MarketsAdapterViewHolder holder, int position) {
        holder.mMarketTextView.setText(data.get(position).toString());
    }

    @Override
    public int getItemCount() {
        if(data == null)
            return 0;
        return data.size();
    }
}
