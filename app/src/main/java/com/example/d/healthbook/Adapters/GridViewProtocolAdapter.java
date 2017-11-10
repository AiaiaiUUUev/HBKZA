package com.example.d.healthbook.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.d.healthbook.Models.DocumentProtocolInfo;
import com.example.d.healthbook.R;


import java.util.List;

import io.realm.Realm;

/**
 * Created by pc on 23.04.2017.
 */

public class GridViewProtocolAdapter extends BaseAdapter {
    private Context context;
    protected List<DocumentProtocolInfo> documentCategoryProtocols;
    LayoutInflater inflater;


    private Realm mRealm;


    public GridViewProtocolAdapter(Context context, List<DocumentProtocolInfo> companiess) {
        this.context = context;
        documentCategoryProtocols = companiess;
        inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_item_protocol_category, null);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.protocolTV);
        textView.setText(documentCategoryProtocols.get(position).getNameRu());

        return convertView;
    }

    @Override
    public int getCount() {
        return documentCategoryProtocols.size();
    }

    @Override
    public Object getItem(int position) {
        return documentCategoryProtocols.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}