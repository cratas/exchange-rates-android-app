package com.example.netactivity;

import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CNBAdapter extends ArrayAdapter<Entry>{

    Context context;
    int layoutResourceId;   
    List<Entry> data = null;
    
   
    public CNBAdapter(Context context, int layoutResourceId, List<Entry> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        EntryHolder holder = null;
       
        if(row == null)
        {
        	LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );      	
            row = inflater.inflate(layoutResourceId, parent, false);
           
            holder = new EntryHolder();
            holder.txtKod = (TextView)row.findViewById(R.id.txtKod);
            holder.txtCountry = (TextView)row.findViewById(R.id.txtCountry);
            holder.txtRate = (TextView)row.findViewById(R.id.txtRate);
            holder.flagImage = (ImageView) row.findViewById(R.id.flagImage);
            
            row.setTag(holder);
        }
        else
        {
            holder = (EntryHolder)row.getTag();
        }
       
        Entry entry = data.get(position);
        holder.txtKod.setText(entry.kod);
        holder.txtRate.setText(entry.rate);
        holder.txtCountry.setText(entry.country);

        holder.flagImage.setImageResource(this.getContext()
                        .getResources()
                        .getIdentifier("flag_" + entry.kod.toLowerCase(Locale.ROOT)
                                , "drawable"
                                , context.getPackageName()));

        return row;
    }
   
    static class EntryHolder
    {
        ImageView flagImage;
        TextView txtKod;
        TextView txtRate;
        TextView txtCountry;
    }
}
