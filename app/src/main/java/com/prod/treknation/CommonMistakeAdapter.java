package com.prod.treknation;

import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CommonMistakeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "TAG";
    private final Context context;
    ArrayList<Item> list;

    public CommonMistakeAdapter(Context context, ArrayList<Item> list) { // you can pass other parameters in constructor
        this.context = context;
        this.list = list;
    }

    private class AdapterItemView extends RecyclerView.ViewHolder {

        private TextView txtItem, txtShortDesc;
        private ImageView downArrow, upArrow, btnComplete;
        private RelativeLayout expandedRelLayout, collapsedRelLayout, parent1;
        private CardView parent;

        AdapterItemView(final View itemView) {
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

        void bind(final Context context , int position) {
            position = getAdapterPosition();
            txtItem.setText(list.get(position).getItemName());
            txtShortDesc.setText(list.get(position).getItemLongDesc());
            txtShortDesc.setMovementMethod(LinkMovementMethod.getInstance());
//        holder.txtLongDesc.setText(items.get(position).getItemLongDesc());

            final int finalPosition = position;
            downArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Item item = list.get(finalPosition);
                    item.setExpanded(!item.isExpanded());
                    if (!list.get(finalPosition).isViewed) {
//                        TransitionManager.beginDelayedTransition(parent);
                        expandedRelLayout.setVisibility(View.VISIBLE);
                        downArrow.setVisibility(View.INVISIBLE);
                        upArrow.setVisibility(View.VISIBLE);
                    }
                }
            });

            upArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Item item = list.get(finalPosition);
                    item.setExpanded(!item.isExpanded());
                    if (!list.get(finalPosition).isViewed) {
//                        TransitionManager.beginDelayedTransition(parent);
                        expandedRelLayout.setVisibility(View.GONE);
                        downArrow.setVisibility(View.VISIBLE);
                        upArrow.setVisibility(View.GONE);
                    }
                }
            });

//            txtShortDesc.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (!list.get(finalPosition).isViewed) {
//                        Intent intent = new Intent(context, LongDesc.class);
//                        intent.putExtra(ITEM_ID_KEY, list.get(finalPosition).getId());
//                        intent.putExtra("Position", finalPosition);
//                        ((HomeActivity) context).startActivityForResult(intent, 1);
//                    }
//                }
//            });

//            collapsedRelLayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(context, LongDesc.class);
//                    intent.putExtra(ITEM_ID_KEY, list.get(finalPosition).getId());
//                    intent.putExtra("Position", finalPosition);
//                    ((HomeActivity) context).startActivityForResult(intent, 1);
//                }
//            });

            if (list.get(position).isViewed) {
                Log.d(TAG, "bind: new Recycler View started purav");
                collapsedRelLayout.setBackgroundColor(context.getResources().getColor(R.color.dark_slate_blue));
                expandedRelLayout.setVisibility(View.GONE);
                list.get(position).setExpanded(false);
                txtItem.setTextColor(context.getResources().getColor(R.color.white));
                btnComplete.setVisibility(View.VISIBLE);
//                txtItem.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (!list.get(finalPosition).isViewed) {
//                            Intent intent = new Intent(context, LongDesc.class);
//                            intent.putExtra(ITEM_ID_KEY, list.get(finalPosition).getId());
//                            intent.putExtra("Position", finalPosition);
//                            ((HomeActivity) context).startActivityForResult(intent, 1);
//                        }
//                    }
//                });
            } else {
                collapsedRelLayout.setBackgroundColor(context.getResources().getColor(R.color.white));
                txtItem.setTextColor(context.getResources().getColor(R.color.dark_slate_blue));
                btnComplete.setVisibility(View.GONE);
                expandedRelLayout.setVisibility(View.GONE);
                if (list.get(position).isExpanded()) {
                    if (!list.get(finalPosition).isViewed) {
                        expandedRelLayout.setVisibility(View.VISIBLE);
                        downArrow.setVisibility(View.INVISIBLE);
                        upArrow.setVisibility(View.VISIBLE);
                    }
                } else {
                    expandedRelLayout.setVisibility(View.GONE);
                    downArrow.setVisibility(View.VISIBLE);
                    upArrow.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AdapterItemView(LayoutInflater.from(context).inflate(R.layout.item_list_mistakes, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((AdapterItemView) holder).bind(context,position);
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}