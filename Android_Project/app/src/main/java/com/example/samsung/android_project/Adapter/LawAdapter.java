package com.example.samsung.android_project.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.samsung.android_project.DataStruct.Law;
import com.example.samsung.android_project.R;

import java.util.ArrayList;

public class LawAdapter extends BaseAdapter {
    private ArrayList<Law> list;
    private static LayoutInflater inflater = null;

    public LawAdapter(ArrayList<Law> temp) {
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

    public Law getLaw(int pos){
        return (Law) getItem(pos);
    }


    public class Holder {
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
        final int position = pos;
        final LawAdapter.Holder holder = new LawAdapter.Holder();
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.law_layout, parent, false);
        }
        final View contextView = rowView;

        holder.num = (TextView) contextView.findViewById(R.id.numTextView1);
        holder.name = (TextView) contextView.findViewById(R.id.nameTextView1);
        holder.court = (TextView) contextView.findViewById(R.id.courtTextView1);
        holder.type1 = (TextView) contextView.findViewById(R.id.incidentTypeTextView1);
        holder.type2 = (TextView) contextView.findViewById(R.id.resultTypeTextView1);
        holder.date = (TextView) contextView.findViewById(R.id.dateTextView1);

        try {
            Law law = (Law) getItem(position);
            holder.num.setText("" + position);
//                holder.name.setText(precedent.getPContent(1));
            //287 10 11
            holder.name.setText(Html.fromHtml(law.getContent(2)));

            holder.court.setText(law.getContent(8));
            holder.type1.setText(law.getContent(7));
            holder.type2.setText(law.getContent(10));
            holder.date.setText(law.getContent(11));
        } catch (Exception e) {
        }
        return rowView;
    }

}
