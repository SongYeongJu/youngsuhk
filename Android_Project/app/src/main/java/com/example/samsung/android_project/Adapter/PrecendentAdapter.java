package com.example.samsung.android_project.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.samsung.android_project.DataStruct.Precedent;
import com.example.samsung.android_project.R;

import java.util.ArrayList;

public class PrecendentAdapter extends BaseAdapter {
    private ArrayList<Precedent> list;
    private static LayoutInflater inflater = null;

    // 판례 리스트를 구현해주는 어뎁터
    public PrecendentAdapter(ArrayList<Precedent> temp) {
        list = temp;
    }

    @Override
    public int getCount() {
        if (list != null) return list.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (list != null) return list.get(position);
        return 0;
    }

    @Override
    public long getItemId(int position) {
        if (list != null) return position;
        return 0;
    }

    public Precedent getPre(int pos){
        return (Precedent) getItem(pos);
    }


    private class Holder {
        TextView num;
        TextView name;
        TextView court;
        TextView type1;
        TextView type2;
        TextView date;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int pos, View rowView, ViewGroup parent) {
        // 리스트뷰의 각 아이템을 구현하는 함수
        final int position = pos; // 리스트뷰의 위치를 표시
        final Holder holder = new Holder(); // holder안에 구성해야할 객체들이 들어있음
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.pre_layout, parent, false);
        }
        final View contextView = rowView;

        holder.num = (TextView) contextView.findViewById(R.id.numTextView);
        holder.name = (TextView) contextView.findViewById(R.id.nameTextView);
        holder.court = (TextView) contextView.findViewById(R.id.courtTextView);
        holder.type1 = (TextView) contextView.findViewById(R.id.incidentTypeTextView);
        holder.type2 = (TextView) contextView.findViewById(R.id.resultTypeTextView);
        holder.date = (TextView) contextView.findViewById(R.id.dateTextView);

        try {
            Precedent precedent = (Precedent) getItem(position);
            holder.num.setText("" + position);
//                holder.name.setText(precedent.getPContent(1));
            holder.name.setText(Html.fromHtml(precedent.getPContent(1)));

            holder.court.setText(precedent.getPContent(4));
            holder.type1.setText(precedent.getPContent(6));
            holder.type2.setText(precedent.getPContent(8));
            holder.date.setText(precedent.getPContent(3));
        } catch (Exception e) {
        }
        return rowView;
    }
}
