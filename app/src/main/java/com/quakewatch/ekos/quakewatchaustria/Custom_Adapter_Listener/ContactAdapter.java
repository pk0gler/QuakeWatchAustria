package com.quakewatch.ekos.quakewatchaustria.Custom_Adapter_Listener;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quakewatch.ekos.quakewatchaustria.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by philippkogler on 02.01.16.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    OnItemClickListener mItemClickListener;
    private List<ContactInfo> contactList;
    private Context context;
    private int lastPosition = -1;

    public ContactAdapter(List<ContactInfo> contactList, Context context) {
        this.contactList = contactList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {
        ContactInfo ci = contactList.get(i);
        contactViewHolder.placename.setText(ci.placeName);
        //contactViewHolder.imgv.setImageResource(ci.img);
        Picasso.with(context).load(ci.img).resize(642, 429).centerCrop().into(contactViewHolder.imgv);
        setAnimation(contactViewHolder.placeCard, i);
    }

    /**
     * Here is the key method to apply the animation
     */
    private void setAnimation(View viewToAnimate, int position) {
        Log.d("Duration", position + "--" + lastPosition);
        // If the bound view wasn't previously displayed on screen, it's animated
        //if (lastPosition==position) lastPosition = -1;
        if ((position > lastPosition)) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);

            //animation.setDuration(900);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_places, viewGroup, false);

        return new ContactViewHolder(itemView);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public LinearLayout placeHolder;
        public CardView placeCard;
        protected TextView placename;
        protected ImageView imgv;


        public ContactViewHolder(View v) {
            super(v);
            placeHolder = (LinearLayout) itemView.findViewById(R.id.mainHolder);
            placeCard = (CardView) v.findViewById(R.id.placeCard);
            placename = (TextView) v.findViewById(R.id.placeName);
            imgv = (ImageView) v.findViewById(R.id.placeImage);
            placeHolder.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(itemView, getAdapterPosition());
            }
        }
    }
}