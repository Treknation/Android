package ca.treknation.myapplicationclone;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import java.util.ArrayList;

import static ca.treknation.myapplicationclone.LongDesc.ITEM_ID_KEY;


public class itemRecViewAdapter extends RecyclerView.Adapter<itemRecViewAdapter.ViewHolder> {

    private static final String TAG4 = "";
    private Context mContext;
    private static final String TAG = "itemRecViewAdapter started";

    public itemRecViewAdapter(Context mContext) {

        this.mContext = mContext;
    }


    private ArrayList<Item> items = new ArrayList<>();
    private int mExpandedPosition = -1;

    public itemRecViewAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.txtItem.setText(items.get(position).getItemName());
        holder.txtShortDesc.setText(items.get(position).getItemShortDesc());
//        holder.txtLongDesc.setText(items.get(position).getItemLongDesc());

        holder.downArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item item = items.get(position);
                item.setExpanded(!item.isExpanded());
                if (!items.get(position).isViewed) {
                    TransitionManager.beginDelayedTransition(holder.parent);
                    holder.expandedRelLayout.setVisibility(View.VISIBLE);
                    holder.downArrow.setVisibility(View.GONE);
                    holder.upArrow.setVisibility(View.VISIBLE);
                }
            }
        });

        holder.upArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item item = items.get(position);
                item.setExpanded(!item.isExpanded());
                if (!items.get(position).isViewed) {
                    TransitionManager.beginDelayedTransition(holder.parent);
                    holder.expandedRelLayout.setVisibility(View.GONE);
                    holder.downArrow.setVisibility(View.VISIBLE);
                    holder.upArrow.setVisibility(View.GONE);
                }
            }
        });

        holder.txtShortDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!items.get(position).isViewed) {
                    Intent intent = new Intent(mContext, LongDesc.class);
                    intent.putExtra(ITEM_ID_KEY, items.get(position).getId());
                    intent.putExtra("Position", position);
                    ((Dashboard) mContext).startActivityForResult(intent, 1);
                }
            }
        });

        holder.collapsedRelLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, LongDesc.class);
                intent.putExtra(ITEM_ID_KEY, items.get(position).getId());
                intent.putExtra("Position", position);
                ((Dashboard) mContext).startActivityForResult(intent, 1);
            }
        });

        if (items.get(position).isViewed) {
            Log.d(TAG4, "onBindViewHolder: started");
            holder.collapsedRelLayout.setBackgroundColor(mContext.getResources().getColor(R.color.dark_slate_blue));
            holder.expandedRelLayout.setVisibility(View.GONE);
            items.get(position).setExpanded(false);
            holder.txtItem.setTextColor(mContext.getResources().getColor(R.color.white));
            holder.btnComplete.setVisibility(View.VISIBLE);
            holder.txtItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!items.get(position).isViewed) {
                        Intent intent = new Intent(mContext, LongDesc.class);
                        intent.putExtra(ITEM_ID_KEY, items.get(position).getId());
                        intent.putExtra("Position", position);
                        ((Dashboard) mContext).startActivityForResult(intent, 1);
                    }
                }
            });
        } else {
            holder.collapsedRelLayout.setBackgroundColor(mContext.getResources().getColor(R.color.white));
            holder.expandedRelLayout.setVisibility(View.GONE);
            if(items.get(position).isExpanded()){
                holder.downArrow.setVisibility(View.GONE);
                holder.upArrow.setVisibility(View.VISIBLE);
            } else {
                holder.downArrow.setVisibility(View.VISIBLE);
                holder.upArrow.setVisibility(View.GONE);
            }
            holder.txtItem.setTextColor(mContext.getResources().getColor(R.color.dark_slate_blue));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtItem, txtShortDesc;
        private ImageView downArrow, upArrow, btnComplete;
        private RelativeLayout expandedRelLayout, collapsedRelLayout, parent1;
        private CardView parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtItem = itemView.findViewById(R.id.txtItem);
            txtShortDesc = itemView.findViewById(R.id.txtShortDesc);
            downArrow = itemView.findViewById(R.id.btnDownArrow);
            upArrow = itemView.findViewById(R.id.btnUpArrow);
            btnComplete = itemView.findViewById(R.id.btnComplete);
            expandedRelLayout = itemView.findViewById(R.id.expandedRelLayout);
            collapsedRelLayout = itemView.findViewById(R.id.collapsedRelLayout);
            parent1 = itemView.findViewById(R.id.parent1);
            parent = itemView.findViewById(R.id.parent);
        }
    }

}

