package com.sendi.pickmeup.me.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sendi.pickmeup.R;
import com.sendi.pickmeup.entity.Passenger;
import com.sendi.pickmeup.entity.VehicleOwn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by lizheng on 2018/6/8.
 */

public class VehicleOwnAdapter extends RecyclerView.Adapter<VehicleOwnAdapter.VehicleOwnViewHolder> {
    private Context mContext;
    private List<Passenger>  mVehicleOwnList;

    public VehicleOwnAdapter(Context mContext, List<Passenger> mVehicleOwnList) {
        this.mContext = mContext;
        this.mVehicleOwnList = mVehicleOwnList;
    }

    @Override
    public VehicleOwnViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.vehicle_own_item,null);
        return new VehicleOwnViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VehicleOwnViewHolder holder, int position) {
        final Passenger passenger = mVehicleOwnList.get(position);

        holder.txtStartTime.setText(strToDate(passenger.getStart_time()));
        holder.txtStartPlace.setText(passenger.getFrom_place());
        holder.txtEndPlace.setText(passenger.getTo_place());
        holder.txtPrice.setText(passenger.getPrice());
        holder.txtPassengerName.setText(passenger.getU_name());
        holder.txtPassengerPhone.setText(passenger.getU_phone());
    }


    @Override
    public int getItemCount() {
        if(mVehicleOwnList == null){
            return 0;
        }
       return  mVehicleOwnList.size();
    }

     class VehicleOwnViewHolder extends RecyclerView.ViewHolder {
         TextView txtStartTime;
         TextView txtStartPlace;
         TextView txtPrice;
         TextView txtEndPlace;
         TextView txtPassengerName;
         TextView txtPassengerPhone;
         public VehicleOwnViewHolder(View itemView) {
             super(itemView);
             txtStartTime = itemView.findViewById(R.id.txt_start_time);
             txtStartPlace = itemView.findViewById(R.id.txt_start_place);
             txtEndPlace = itemView.findViewById(R.id.txt_end_place);
             txtPrice = itemView.findViewById(R.id.txt_price);
             txtPassengerName = itemView.findViewById(R.id.txt_passenger_name);
             txtPassengerPhone = itemView.findViewById(R.id.txt_passenger_phone);
         }
     }

    public static String strToDate(Long ldate){
        Date date = new Date(ldate);
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        String strDate = formatter.format(date);
        return strDate;
    }
}
