package com.quakewatch.ekos.quakewatchaustria.Custom_Adapter_Listener;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.quakewatch.ekos.quakewatchaustria.R;

import java.util.List;

/**
 * Created by philippkogler on 02.01.16.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<ContactInfo> contactList;

    public ContactAdapter(List<ContactInfo> contactList) {
        this.contactList = contactList;
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {
        ContactInfo ci = contactList.get(i);
        contactViewHolder.placename.setText(ci.placeName);
        contactViewHolder.imgv.setImageResource(ci.img);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_places, viewGroup, false);

        return new ContactViewHolder(itemView);
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        protected TextView placename;
        protected ImageView imgv;


        public ContactViewHolder(View v) {
            super(v);
            placename =  (TextView) v.findViewById(R.id.placeName);
            imgv = (ImageView) v.findViewById(R.id.placeImage);
        }
    }
}