package com.rontrix.android.gmailcone.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rontrix.android.gmailcone.R;
import com.rontrix.android.gmailcone.helper.FlipAnimator;
import com.rontrix.android.gmailcone.model.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ron on 3/31/2018.
 */

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MyViewHolder> {
                private Context mContext;
                private List<Message> messages;
                private MessageAdapterListener listener;
                private SparseBooleanArray selectedItems;

                private SparseBooleanArray animationItemsIndex;
                private boolean reverseAllAnimations = false;

                private static int currentSelectedIndex = -1;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Message message = messages.get(position);

        holder.from.setText(message.getFrom());
        holder.subject.setText(message.getSubject());
        holder.message.setText(message.getMessage());
        holder.timestamp.setText(message.getTimestamp());

        holder.iconText.setText(message.getFrom().substring(0, 1));

        holder.itemView.setActivated(selectedItems.get(position, false));

        applyReadStatus(holder, message);

        applyImportant(holder, message);

        applyIconAnimation(holder, position);

        applyProfilePicture(holder, message);

        applyClickEvents(holder, position);
    }

    private void applyClickEvents(MyViewHolder holder, final int position) {
        holder.iconContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onIconClicked(position);
            }
        });

        holder.iconImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onIconImportantClicked(position);
            }
        });

        holder.messageContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onMessageRowClicked(position);
            }
        });

        holder.messageContainer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onRowLongClicked(position);
                v.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                return true;
            }
        });
    }

    private void applyProfilePicture(MyViewHolder holder, Message message) {
        /*if(!TextUtils.isEmpty(message.getPicture())) {
            Glide.with(mContext).load(message.getPicture())
                    .thumbnail(0.5f)
                    .crossFade()
                    .transform(new CircleTransfrom(mContext))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.imgProfile);
            holder.imgProfile.setColorFilter(null);
            holder.iconText.setVisibility(View.GONE);
        } else {
            holder.imgProfile.setImageResource(R.drawable.bg_circle);
            holder.imgProfile.setColorFilter(message.getColor());
            holder.iconText.setVisibility(View.VISIBLE);
        }*/
        holder.imgProfile.setImageResource(R.drawable.bg_circle);
        holder.imgProfile.setColorFilter(message.getColor());
        holder.iconText.setVisibility(View.VISIBLE);

    }

    private void applyIconAnimation(MyViewHolder holder, int position) {
        if(selectedItems.get(position, false)) {
            holder.iconFront.setVisibility(View.GONE);
            resetIconYAxis(holder.iconBack);
            holder.iconBack.setVisibility(View.VISIBLE);
            holder.iconBack.setAlpha(1);
            if(currentSelectedIndex == position) {
                FlipAnimator.flipView(mContext, holder.iconBack, holder.iconFront, true);
                resetCurrentIndex();
            }
        } else {
            holder.iconBack.setVisibility(View.GONE);
            resetIconYAxis(holder.iconFront);
            holder.iconFront.setVisibility(View.VISIBLE);
            holder.iconFront.setAlpha(1);
            if((reverseAllAnimations && animationItemsIndex.get(position, false)) || currentSelectedIndex == position ){
                FlipAnimator.flipView(mContext, holder.iconBack, holder.iconFront, false);
                resetCurrentIndex();
            }
        }

    }

    private void resetIconYAxis(View view) {
        if(view.getRotationY() != 0) {
            view.setRotationY(0);
        }
    }

    public void resetAnimationIndex() {
        reverseAllAnimations = false;
        animationItemsIndex.clear();
    }

    @Override
    public long getItemId(int position) {
        return messages.get(position).getId();
    }

    private void applyImportant(MyViewHolder holder, Message message) {
        if(message.isImportant()) {
            holder.iconImageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_star));
            holder.iconImageView.setColorFilter(ContextCompat.getColor(mContext, R.color.icon_tint_selected));
        } else {
            holder.iconImageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_star_border));
            holder.iconImageView.setColorFilter(ContextCompat.getColor(mContext, R.color.icon_tint_normal));
        }
    }

    private void applyReadStatus(MyViewHolder holder, Message message) {
        if (message.isRead()) {
            holder.from.setTypeface(null, Typeface.NORMAL);
            holder.subject.setTypeface(null, Typeface.NORMAL);
            holder.timestamp.setTypeface(null, Typeface.NORMAL);
            holder.from.setTextColor(ContextCompat.getColor(mContext, R.color.subject));
            holder.subject.setTextColor(ContextCompat.getColor(mContext, R.color.message));
            holder.timestamp.setTextColor(ContextCompat.getColor(mContext, R.color.message));
        } else {
            holder.from.setTypeface(null, Typeface.BOLD);
            holder.subject.setTypeface(null, Typeface.BOLD);
            holder.timestamp.setTypeface(null, Typeface.BOLD);
            holder.from.setTextColor(ContextCompat.getColor(mContext, R.color.from));
            holder.subject.setTextColor(ContextCompat.getColor(mContext, R.color.subject));
            holder.timestamp.setTextColor(ContextCompat.getColor(mContext, R.color.timestamp));
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

                    public TextView from, subject, message, iconText, timestamp;
                    public ImageView iconImageView, imgProfile;
                    public LinearLayout messageContainer;
                    public RelativeLayout iconContainer, iconBack, iconFront;

                    public MyViewHolder(View itemView) {
                        super(itemView);
                        from = itemView.findViewById(R.id.from);
                        subject = itemView.findViewById(R.id.txt_primary);
                        message = itemView.findViewById(R.id.txt_secondary);
                        iconText = itemView.findViewById(R.id.icon_text);
                        timestamp = itemView.findViewById(R.id.timestamp);
                        iconBack = itemView.findViewById(R.id.icon_back);
                        iconFront = itemView.findViewById(R.id.icon_front);
                        iconImageView = itemView.findViewById(R.id.icon_star);
                        imgProfile = itemView.findViewById(R.id.icon_profile);
                        messageContainer = itemView.findViewById(R.id.message_container);
                        iconContainer = itemView.findViewById(R.id.icon_container);
                        itemView.setOnLongClickListener(this);


                    }

                    @Override
                    public boolean onLongClick(View v) {
                        listener.onRowLongClicked(getAdapterPosition());
                        v.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                        return true;
                    }
                }

                public MessagesAdapter(Context mContext, List<Message> messages, MessageAdapterListener listener) {
                    this.mContext = mContext;
                    this.messages = messages;
                    this.listener = listener;
                    selectedItems = new SparseBooleanArray();
                    animationItemsIndex = new SparseBooleanArray();
                }

                public void toggleSelection(int pos) {
                    currentSelectedIndex = pos;
                    if(selectedItems.get(pos, false)) {
                        selectedItems.delete(pos);
                        animationItemsIndex.delete(pos);
                    } else {
                        selectedItems.put(pos, true);
                        animationItemsIndex.put(pos, true);
                    }
                    notifyItemChanged(pos);
                }

                public void clearSelections() {
                        reverseAllAnimations = true;
                        selectedItems.clear();
                        notifyDataSetChanged();
                }

                public int getSelectedItemCount() {
                    return selectedItems.size();
                }

                public List<Integer> getSelectedItems() {
                    List<Integer> items =
                            new ArrayList<>(selectedItems.size());
                    for(int i = 0; i < selectedItems.size(); i++) {
                        items.add(selectedItems.keyAt(i));
                    }

                    return items;
                }

                public void removeData(int position) {
                    messages.remove(position);
                    resetCurrentIndex();
                }

                private void resetCurrentIndex() {
                    currentSelectedIndex = -1;
                }

                public interface MessageAdapterListener {
                    void onIconClicked(int position);

                    void onIconImportantClicked(int position);

                    void onMessageRowClicked(int position);

                    void onRowLongClicked(int position);
                }
}
