package com.example.overlord.bookofsales.Data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import com.example.overlord.bookofsales.Model.Bill;
import com.example.overlord.bookofsales.Model.Enterprise;
import com.example.overlord.bookofsales.Model.Order;
import com.example.overlord.bookofsales.Model.Product;

import java.util.ArrayList;

public class DBRequester {
    private DataBaseHelper dbHelper;
    private SQLiteDatabase db;
    private Context context;

    public DBRequester(Context context) {
        this.context = context;
        dbHelper = new DataBaseHelper(context);
        dbHelper.openDataBase();
        db = dbHelper.getReadableDatabase();
    }

    public DataBaseHelper getDbHelper() {
        return dbHelper;
    }

    public Bill completeBill(Bill bill) {
        if (bill.isPartialInit()) {
            Cursor cursor = db.query("Bills", new String[]{"order_ids"}, "_id = ?", new String[]{String.valueOf(bill.getId())}, null, null, null);
            cursor.moveToFirst();
            String[] ids = cursor.getString(0).split(", ");
            ArrayList<Order> orders = new ArrayList<>();
            for (String id : ids) {
                orders.add(getOrdersById(Integer.parseInt(id)));
            }
            bill.setOrders(orders);
            cursor.close();
        }
        return bill;
    }


    public ArrayList<Bill> getBills(@Nullable String table, @Nullable String[] columns, @Nullable String selection, @Nullable String[] selectionArgs) {
        ArrayList<Bill> bills = new ArrayList<>();
        if(table==null || table.isEmpty())
            table = "Bills";
        Cursor cursor = db.query(table, columns, selection, selectionArgs, null, null, "number DESC");

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            int number = cursor.getInt(1);
            int providerId = cursor.getInt(2);
            int customerId = cursor.getInt(3);
            String orderIds = cursor.getString(4);
            String date = cursor.getString(5);

            Bill bill = new Bill(id);
            bill.setNumber(number);
            bill.setProvider(getEnterpriseById(providerId));
            bill.setCustomer(getEnterpriseById(customerId));
            int cost = 0;
            for (String s : orderIds.split(", ")) {
                cost += getOrderCostById(Integer.parseInt(s));
            }
            bill.setCost(cost);
            bill.setDate(date);
            bill.setPartialInit(true);

            bills.add(bill);

            cursor.moveToNext();
        }
        cursor.close();

        return bills;
    }

    public Order getOrdersById(int id) {
        Cursor cursor = db.query("Orders", null, "_id = ?", new String[]{String.valueOf(id)}, null, null, null);

        cursor.moveToFirst();
        Product product = getProductById(cursor.getInt(1));
        String units = cursor.getString(2);
        int count = cursor.getInt(3);

        Order order = new Order(id);
        order.setProduct(product);
        order.setUnits(units);
        order.setCount(count);

        cursor.close();

        return order;
    }

    public Product getProductById(int id) {
        Cursor cursor = db.query("Products", null, "_id = ?", new String[]{String.valueOf(id)}, null, null, null);
        cursor.moveToFirst();

        String name = cursor.getString(1);
        String okpd = cursor.getString(2);
        int cost = cursor.getInt(3);
        int costExcise = cursor.getInt(4);
        int nds = cursor.getInt(5);

        Product product = new Product(id);
        product.setName(name);
        product.setOkpd(okpd);
        product.setCost(cost);
        product.setCostExcise(costExcise);
        product.setNds(nds);

        cursor.close();

        return product;
    }

    public int getOrderCostById(int id) {
        Cursor cursorOrder = db.query("Orders", new String[]{"product_id", "count"}, "_id = ?", new String[]{Integer.toString(id)}, null, null, null);
        cursorOrder.moveToFirst();
        int productId = cursorOrder.getInt(0);
        int count = cursorOrder.getInt(1);
        cursorOrder.close();

        Cursor cursorProduct = db.query("Products", new String[]{"cost"}, "_id = ?", new String[]{Integer.toString(productId)}, null, null, null);
        cursorProduct.moveToFirst();
        int fullCost = cursorProduct.getInt(0);
        cursorProduct.close();

        return fullCost * count;
    }


    public Enterprise getEnterpriseById(int id) {
        Cursor cursor = db.query("Enterprises", null, "_id = ?", new String[]{Integer.toString(id)}, null, null, null);
        Enterprise enterprise = new Enterprise(id);

        cursor.moveToFirst();

        if(!cursor.isAfterLast()) {
            String name = cursor.getString(1);
            String adress = cursor.getString(2);
            String phone = cursor.getString(3);
            String checkingAccount = cursor.getString(4);
            String city = cursor.getString(5);
            String tin = cursor.getString(6);
            String okonx = cursor.getString(7);
            String okpo = cursor.getString(8);


            enterprise.setName(name);
            enterprise.setAdress(adress);
            enterprise.setPhone(phone);
            enterprise.setCheckingAccount(checkingAccount);
            enterprise.setCity(city);
            enterprise.setTin(tin);
            enterprise.setOkonx(okonx);
            enterprise.setOkpo(okpo);
        }

        cursor.close();

        return enterprise;
    }
}
