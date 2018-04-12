package com.example.quinatzin.myroute;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by quinatzin on 4/7/2018.
 */
//ArrayAdapter<Customer>
public class CustomerAdapter extends BaseAdapter {
    Context context;
    ArrayList<Customer> customers;


    public CustomerAdapter(Context context, ArrayList<Customer> customers) {
        // super(context, resource, customers);
        this.context = context;
        this.customers = customers;
    }

    @Override
    public int getCount() {
        return customers.size();
    }

    //@Override
    public Object getItem(int pos) {
        return customers.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
            //convertView = getContext().getLayoutInflater().inflate(R.layout.list_item, parent, false);
        }
        TextView nameTextView = convertView.findViewById(R.id.date_id);
        TextView customerTextView = convertView.findViewById(R.id.customer_id);

        final Customer customerInformation = (Customer) this.getItem(position);

        nameTextView.setText(customerInformation.getFirstName());
        customerTextView.setText(customerInformation.getAddress());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetailActivity(customerInformation.getLastName(), customerInformation.getFirstName(), customerInformation.getPhoneNumber(),
                        customerInformation.getEmail(), customerInformation.getPrice(), customerInformation.getDay(),
                        customerInformation.getAddress(), customerInformation.getNotes());
            }
        });


        return convertView;
    }

    private void openDetailActivity(String... details) {
        Intent intent = new Intent(context, CustomerDetail.class);
        intent.putExtra("LAST_NAME_KEY", details[0]);
        intent.putExtra("FIRST_NAME_KEY", details[1]);
        intent.putExtra("PHONE_KEY", details[2]);
        intent.putExtra("EMAIL_KEY", details[3]);
        intent.putExtra("PRICE_KEY", details[4]);
        intent.putExtra("DAY_KEY", details[5]);
        intent.putExtra("ADDRESS_KEY", details[6]);
        intent.putExtra("NOTES_KEY", details[7]);

        context.startActivity(intent);
    }
}
