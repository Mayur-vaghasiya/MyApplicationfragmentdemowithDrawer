package com.example.peacock.myapplicationfragmentdemo.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.peacock.myapplicationfragmentdemo.R;
import com.example.peacock.myapplicationfragmentdemo.model.DashBoarditem;
import com.example.peacock.myapplicationfragmentdemo.util.CommonUtility;

import java.util.ArrayList;



/**
 * Created by peacock on 25/11/17.
 */

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.viewHolder> {

    public final static int TYPE_HEADER = 0;
    public final static int TYPE_MENU = 1;
    Context context;

    private ArrayList<DashBoarditem> itemlist;
    private OnItemsClickListener onItemsClickListener;

    private OnItemSelecteListener mListener;

    public NavigationAdapter(Context context, ArrayList<DashBoarditem> itemlist, OnItemsClickListener onItemsClickListener) {
        this.context = context;
        this.itemlist = itemlist;
        this.onItemsClickListener = onItemsClickListener;


    }



    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        if (viewType == TYPE_HEADER) {

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_header, parent, false);

        } else {

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_navigation_drawer, parent, false);
        }

        // return new viewHolder(view);
        return new viewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, final int position) {

        final DashBoarditem i;

        // i = itemlist.get(position);
        //holder.Name.setText(i.getItenname());
        // holder.Image.setText(i.getImgfont());

        if (position == 0) {



        } else {
            holder.Name.setText(itemlist.get(position - 1).getItenname());
            holder.Image.setText(itemlist.get(position - 1).getImgfont());
            CommonUtility.setappfont(context, holder.Image);
        }

    }

    @Override
    public int getItemCount() {

        return itemlist.size() + 1;

    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_MENU;
    }

    public void setOnItemClickLister(OnItemSelecteListener mListener) {
        this.mListener = mListener;
    }

    public interface OnItemsClickListener {

        void onClick(String itemid);

    }

    public interface OnItemSelecteListener {
        public void onItemSelected(View v, int position);
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView Name, Image, tv_userprofile,actv_user_fname;
        LinearLayout ll_layout;


        public viewHolder(View itemView) {
            super(itemView);

            Image = (AppCompatTextView) itemView.findViewById(R.id.img_icon);
            Name = (AppCompatTextView) itemView.findViewById(R.id.actv_name);
            ll_layout = (LinearLayout) itemView.findViewById(R.id.ll_layout);

            ll_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    ll_layout.findViewById(R.id.img_icon).performClick();
                    ll_layout.findViewById(R.id.actv_name).performClick();

                    onItemsClickListener.onClick(itemlist.get(position).getID());

                }
            });
        }

        public viewHolder(final View itemView, int viewType) {
            super(itemView);

            if (viewType == 0) {


            } else {
                Image = (AppCompatTextView) itemView.findViewById(R.id.img_icon);
                Name = (AppCompatTextView) itemView.findViewById(R.id.actv_name);
                ll_layout = (LinearLayout) itemView.findViewById(R.id.ll_layout);


                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = getAdapterPosition();

                        itemView.findViewById(R.id.img_icon).performClick();
                        itemView.findViewById(R.id.actv_name).performClick();

                        onItemsClickListener.onClick(itemlist.get(position - 1).getID());

                    }
                });

            }

        }

    }
}
