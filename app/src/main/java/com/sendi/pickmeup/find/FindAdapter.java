package com.sendi.pickmeup.find;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sendi.pickmeup.R;
import com.sendi.pickmeup.entity.Journey;

import java.util.List;

/**
 * Created by sendi on 2018/6/7.
 */

public class FindAdapter extends RecyclerView.Adapter<FindAdapter.FindViewHolder> {

    private Context mContext;
    private List<Journey> mJourneyList;

    public FindAdapter(Context context, List<Journey> journeyList) {
        mContext = context;
        mJourneyList = journeyList;
    }

    @Override
    public FindViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = View.inflate(mContext, R.layout.find_item, null);

        return new FindViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FindViewHolder holder, int position) {
        Journey journey=mJourneyList.get(position);

        holder.txtStartTime.setText(journey.getStart_time());
        holder.txtStartPlace.setText(journey.getFrom_place());
        holder.txtEndPlace.setText(journey.getTo_place());
        holder.txtPrice.setText(journey.getPrice());
    }

    @Override
    public int getItemCount() {
        if (mJourneyList == null)
            return 0;
        return mJourneyList.size();
    }

    class FindViewHolder extends RecyclerView.ViewHolder {

        TextView txtStartTime;
        TextView txtStartPlace;
        TextView txtPrice;
        TextView txtEndPlace;

        public FindViewHolder(View itemView) {
            super(itemView);

            txtStartTime = itemView.findViewById(R.id.txt_start_time);
            txtStartPlace = itemView.findViewById(R.id.txt_start_place);
            txtEndPlace = itemView.findViewById(R.id.txt_end_place);
            txtPrice = itemView.findViewById(R.id.txt_price);

        }
    }
}
