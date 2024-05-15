package com.example.ghosthome.ui.addroom.device.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ghosthome.R;
import com.example.ghosthome.ui.addroom.device.model.SpinnerItem;

public class CustomSpinnerAdapter extends ArrayAdapter<SpinnerItem> {

    private LayoutInflater inflater;

    public CustomSpinnerAdapter(Context context, int resource) {
        super(context, resource);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.dropdown_add_device, parent, false);
        }

        SpinnerItem item = getItem(position);
        if (item != null) {
            TextView textView = view.findViewById(R.id.text);
            ImageView imageView = view.findViewById(R.id.icon);

            textView.setText(item.getText());
            imageView.setImageResource(item.getImageResourceId());
        }

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
