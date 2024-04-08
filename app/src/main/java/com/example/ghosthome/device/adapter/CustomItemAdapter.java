package com.example.ghosthome.device.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ghosthome.R;
import com.example.ghosthome.device.model.Item;

import java.util.List;
public class CustomItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_ITEM = 0;
    private static final int VIEW_TYPE_BUTTON = 1;

    private List<Item> itemList;
    private Context context;
    View.OnClickListener  onClickListener;
    public CustomItemAdapter(Context context, List<Item> itemList, View.OnClickListener onClickListener) {
        this.itemList = itemList;
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.add_device_item_simple, parent, false);
            return new ItemViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.add_device_item_layout, parent, false);
            return new ButtonViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            Item item = itemList.get(position);
            itemViewHolder.imageView.setImageResource(item.getImageResourceId());
            itemViewHolder.textView.setText(item.getText());
        } else if (holder instanceof ButtonViewHolder) {
            ButtonViewHolder buttonViewHolder = (ButtonViewHolder) holder;
            buttonViewHolder.button.setOnClickListener(onClickListener);
//            buttonViewHolder.button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // Handle button click to show Spinner dialog
//                    // Example: MainActivity.showSpinnerDialog()
//
//                }
//            });
        }
    }

    @Override
    public int getItemCount() {
        // Always include the button item
        return itemList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        // Return VIEW_TYPE_BUTTON for the last item
        if (position == itemList.size()) {
            return VIEW_TYPE_BUTTON;
        } else {
            return VIEW_TYPE_ITEM;
        }
    }

    // ViewHolder for regular items
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ivRoom);
            textView = itemView.findViewById(R.id.tvRoom);
        }
    }

    // ViewHolder for button item
    public static class ButtonViewHolder extends RecyclerView.ViewHolder {
        CardView button;

        public ButtonViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.addBtn);
        }
    }
}
