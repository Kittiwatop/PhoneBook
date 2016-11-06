package com.example.phonebook.Adaptor;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.phonebook.R;
import com.example.phonebook.model.Contact;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
public class ContactListAdaptor extends ArrayAdapter<Contact>{



    private Context mContext;
    private int mLayoutResId;
    private ArrayList<Contact> mContactList;

    public ContactListAdaptor(Context context, int resource, ArrayList<Contact> contactList) {
        super(context, resource, contactList);

        this.mContext = context;
        this.mLayoutResId = resource;
        this.mContactList = contactList;

    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View itemLayout =  convertView;

        if(itemLayout == null) {
            itemLayout = View.inflate(mContext, mLayoutResId, null);// à»ç¹ static method
        }

        ImageView imageView =(ImageView) itemLayout.findViewById(R.id.image);
        TextView nameTextView = (TextView)itemLayout.findViewById(R.id.text);
        TextView phoneTextView = (TextView)itemLayout.findViewById(R.id.phone);

        Contact contact = mContactList.get(position);

        String contactName = contact.getName();
        nameTextView.setText(contactName);

        String contactPhone = contact.getPhone();
        phoneTextView.setText(contactPhone);

        String contactImage = contact.getImage();
        AssetManager am = mContext.getAssets();
        try {
            InputStream steam = am.open(contactImage);
            Drawable imageDrawable = Drawable.createFromStream(steam, null);
            imageView.setImageDrawable(imageDrawable); //รูปแบบ android

        } catch (IOException e) {
            Log.e("ContactListAdapter", "Error open image file: "+ contactImage );
            e.printStackTrace();
        }

        /*if("android".equals(contactName)){
            imageView.setImageResource(R.mipmap.ic_launcher);
        }
        else if("ios".equals(contactName)){
            imageView.setImageResource(R.drawable.ios);
        }
        else if("windows".equals(contactName)){
            imageView.setImageResource(R.drawable.windows);
        }
        else if("linux".equals(contactName)){
            imageView.setImageResource(R.drawable.linux);
        }*/

        return itemLayout;
    }

    /*@Override
    public int getCount() {
        return 30;
    }*/

}
