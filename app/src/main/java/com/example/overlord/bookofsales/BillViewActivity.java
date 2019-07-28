package com.example.overlord.bookofsales;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.overlord.bookofsales.Data.DBRequester;
import com.example.overlord.bookofsales.Data.DataBaseHelper;
import com.example.overlord.bookofsales.Model.Bill;
import com.example.overlord.bookofsales.Model.Order;

import java.util.ArrayList;

public class BillViewActivity extends AppCompatActivity {
    Bill bill;
    DBRequester requester;
    TableRow.LayoutParams params = new TableRow.LayoutParams();
    {
        params.bottomMargin = 2;
        params.topMargin = 2;
        params.leftMargin = 2;
        params.rightMargin = 2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill_view);
        requester = new DBRequester(this);
        requester.getDbHelper();

        Intent intent = getIntent();
        bill = (Bill) intent.getSerializableExtra("bill");
        requester.completeBill(bill);

        ((TextView)findViewById(R.id.number)).setText(bill.getNumber()+"");
        ((TextView)findViewById(R.id.date)).setText(bill.getDate());

        ((TextView)findViewById(R.id.provider_name)).setText(bill.getProvider().getName());
        ((TextView)findViewById(R.id.provider_adres)).setText(bill.getProvider().getAdress());
        ((TextView)findViewById(R.id.provider_checking_account)).setText(bill.getProvider().getCheckingAccount());
        ((TextView)findViewById(R.id.provider_city)).setText(bill.getProvider().getCity());
        ((TextView)findViewById(R.id.provider_okonx)).setText(bill.getProvider().getOkonx());
        ((TextView)findViewById(R.id.provider_okpo)).setText(bill.getProvider().getOkpo());
        ((TextView)findViewById(R.id.provider_phone)).setText(bill.getProvider().getPhone());
        ((TextView)findViewById(R.id.provider_tin)).setText(bill.getProvider().getTin());

        ((TextView)findViewById(R.id.customer_name)).setText(bill.getCustomer().getName());
        ((TextView)findViewById(R.id.customer_adres)).setText(bill.getCustomer().getAdress());
        ((TextView)findViewById(R.id.customer_checking_account)).setText(bill.getCustomer().getCheckingAccount());
        ((TextView)findViewById(R.id.customer_city)).setText(bill.getCustomer().getCity());
        ((TextView)findViewById(R.id.customer_okonx)).setText(bill.getCustomer().getOkonx());
        ((TextView)findViewById(R.id.customer_okpo)).setText(bill.getCustomer().getOkpo());
        ((TextView)findViewById(R.id.customer_phone)).setText(bill.getCustomer().getPhone());
        ((TextView)findViewById(R.id.customer_tin)).setText(bill.getCustomer().getTin());

        ((TextView)findViewById(R.id.sender_name)).setText(bill.getProvider().getName());
        ((TextView)findViewById(R.id.sender_adres)).setText(bill.getProvider().getAdress());
        ((TextView)findViewById(R.id.getter_name)).setText(bill.getCustomer().getName());
        ((TextView)findViewById(R.id.getter_adres)).setText(bill.getCustomer().getAdress());

        ((TextView)findViewById(R.id.all_cost)).setText(bill.getCost()+"");

        initOrdersTable();
    }

    private void initOrdersTable(){
        TableLayout table = findViewById(R.id.order_table);
        table.setStretchAllColumns(true);
        table.setShrinkAllColumns(true);

        TableRow header = new TableRow(this);

        TextView name = new TextView(this);
        TextView okdp = new TextView(this);
        TextView units = new TextView(this);
        TextView count = new TextView(this);
        TextView cost = new TextView(this);
        TextView costExcise = new TextView(this);
        TextView sum = new TextView(this);
        TextView sumExcise = new TextView(this);
        TextView nds = new TextView(this);
        TextView costNds = new TextView(this);
        TextView sumNds = new TextView(this);

        name.setText("Наименование товара");
        okdp.setText("Код по ОКДП");
        units.setText("Ед. изм.");
        count.setText("Кол-во");
        cost.setText("Цена");
        costExcise.setText("в т.ч. акциз");
        sum.setText("Сумма");
        sumExcise.setText("в т.ч. акциз");
        nds.setText("Ставка НДС");
        costNds.setText("Сумма НДС");
        sumNds.setText("Всего НДС");

        header.addView(name, params);
        header.addView(okdp, params);
        header.addView(units, params);
        header.addView(count, params);
        header.addView(cost, params);
        header.addView(costExcise, params);
        header.addView(sum, params);
        header.addView(sumExcise, params);
        header.addView(nds, params);
        header.addView(costNds, params);
        header.addView(sumNds, params);

        table.addView(header, params);

        for (Order order : bill.getOrders()){
            table.addView(getOrderRow(order), params);
        }
    }

    private TableRow getOrderRow(Order order){
        TableRow row = new TableRow(this);

        TextView name = new TextView(this);
        TextView okdp = new TextView(this);
        TextView units = new TextView(this);
        TextView count = new TextView(this);
        TextView cost = new TextView(this);
        TextView costExcise = new TextView(this);
        TextView sum = new TextView(this);
        TextView sumExcise = new TextView(this);
        TextView nds = new TextView(this);
        TextView costNds = new TextView(this);
        TextView sumNds = new TextView(this);

        name.setText(order.getProduct().getName());
        okdp.setText(order.getProduct().getOkpd());
        units.setText(order.getUnits());
        count.setText(order.getCount()+"");
        cost.setText(order.getProduct().getCost()+"");
        costExcise.setText(order.getProduct().getCostExcise()+"");
        sum.setText(order.getProduct().getCost() * order.getCount()+"");
        sumExcise.setText(order.getProduct().getCostExcise() * order.getCount()+"");
        nds.setText(order.getProduct().getNds()+"");
        costNds.setText((int) (order.getProduct().getCost() * (order.getProduct().getNds() / 100.0))+"");
        sumNds.setText((int) (order.getProduct().getCost() * order.getCount() * (order.getProduct().getNds() / 100.0))+"");

        row.addView(name, params);
        row.addView(okdp, params);
        row.addView(units, params);
        row.addView(count, params);
        row.addView(cost, params);
        row.addView(costExcise, params);
        row.addView(sum, params);
        row.addView(sumExcise, params);
        row.addView(nds, params);
        row.addView(costNds, params);
        row.addView(sumNds, params);

        return row;
    }

}
