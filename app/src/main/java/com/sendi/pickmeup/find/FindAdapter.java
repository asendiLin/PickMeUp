package com.sendi.pickmeup.find;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sendi.pickmeup.R;
import com.sendi.pickmeup.entity.Journey;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by sendi on 2018/6/7.
 */

public class FindAdapter extends RecyclerView.Adapter<FindAdapter.FindViewHolder> {

    private Context mContext;
    private List<Journey> mJourneyList;
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public FindAdapter(Context context, List<Journey> journeyList) {
        mContext = context;
        mJourneyList = journeyList;
    }

    @Override
    public FindViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

//        View view = View.inflate(mContext, R.layout.find_item, null);
        View view=LayoutInflater.from(mContext).inflate(R.layout.find_item,parent,false);
        return new FindViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FindViewHolder holder, int position) {
        final Journey journey = mJourneyList.get(position);

        holder.txtStartTime.setText(strToDate(journey.getStart_time()));
        holder.txtStartPlace.setText(journey.getFrom_place());
        holder.txtEndPlace.setText(journey.getTo_place());
        holder.txtPrice.setText(journey.getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null)
                    mOnItemClickListener.onClick(journey);
            }
        });
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

    interface OnItemClickListener {
        void onClick(Journey journey);
    }
    public static String strToDate(Long ldate){
        Date date = new Date(ldate);
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        String strDate = formatter.format(date);
        return strDate;
    }
}
