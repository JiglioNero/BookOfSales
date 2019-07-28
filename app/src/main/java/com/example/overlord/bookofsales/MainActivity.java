package com.example.overlord.bookofsales;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;

import com.example.overlord.bookofsales.Data.DBRequester;
import com.example.overlord.bookofsales.Data.DataBaseHelper;
import com.example.overlord.bookofsales.Model.Bill;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    SearchView search;
    RecyclerView listOfBills;
    DBRequester requester;
    BillsAdapter adapter;
    DataBaseHelper dbHelper;

    Pattern COST_RANGE = Pattern.compile("\\d+ - \\d+");
    Pattern BILL_NUMBER = Pattern.compile("\\d+");
    Pattern ENTERPRISE_NAME = Pattern.compile("[\\w\\s]+");
    Pattern DATE = Pattern.compile("\\d{2}.\\d{2}.\\d{2}");


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initListOfBills();
        initSearch();
        requester = new DBRequester(this);
        dbHelper = requester.getDbHelper();

        adapter.setItems(requester.getBills(null, null,null, null));
    }

    private void initListOfBills(){
        listOfBills = findViewById(R.id.listOfBills);
        listOfBills.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BillsAdapter(this, requester);
        listOfBills.setAdapter(adapter);
    }

    private void initSearch(){
        search = findViewById(R.id.searchView);
        search.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                adapter.clearItems();
                adapter.setItems(requester.getBills(null, null,null, null));
                return true;
            }
        });
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (BILL_NUMBER.matcher(query).matches()){
                    adapter.clearItems();
                    adapter.setItems(requester.getBills(null, null,"number = ?", new String[]{query}));
                    return true;
                }
                if (ENTERPRISE_NAME.matcher(query).matches()){
                    adapter.clearItems();
                    ArrayList<Bill> b1 = requester.getBills("Bills as PL inner join Enterprises as PS on PL.provider_id = PS._id", null,"PS.name = ?", new String[]{query});
                    ArrayList<Bill> b2 = requester.getBills("Bills as PL inner join Enterprises as PS on PL.customer_id = PS._id", null,"PS.name = ?", new String[]{query});
                    b1.addAll(b2);

                    adapter.setItems(b1);
                    return true;
                }
                if (DATE.matcher(query).matches()){
                    adapter.clearItems();
                    adapter.setItems(requester.getBills(null, null,"date = ?", new String[]{query}));
                    return true;
                }
                if (COST_RANGE.matcher(query).matches()){
                    String[] ar = query.split(" - ");
                    int min = Integer.parseInt(ar[0]);
                    int max = Integer.parseInt(ar[1]);
                    adapter.clearItems();
                    ArrayList<Bill> list = requester.getBills(null,null, null, null);
                    ArrayList<Bill> newList = new ArrayList<>();
                    for(Bill bill : list){
                        if (bill.getCost()>=min && bill.getCost()<=max)
                            newList.add(bill);
                    }
                    adapter.setItems(newList);
                    return true;
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

}
