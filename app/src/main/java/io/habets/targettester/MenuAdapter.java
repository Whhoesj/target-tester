package io.habets.targettester;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by wouter on 19-1-17.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    @NonNull
    private final OnItemClickListener onItemClickListener;
    private final String[] items;

    public MenuAdapter(@NonNull OnItemClickListener listener, String... menuItems) {
        this.onItemClickListener = listener;
        this.items = menuItems;
    }

    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MenuAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false));
    }

    @Override
    public void onBindViewHolder(MenuAdapter.ViewHolder holder, int position) {
        holder.setItem(items[position]);
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, String name);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView;
            itemView.setOnClickListener(this);
        }

        public void setItem(String name) {
            textView.setText(name);
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            onItemClickListener.onItemClick(position, items[position]);
        }
    }
}
