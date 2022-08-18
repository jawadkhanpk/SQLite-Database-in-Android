package com.example.sqlitedatabaseinandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText etCustomerName, etCustomerAge;
    Switch switchActiveCustomers;
    Button btnAdd, btnViewAll;
    ListView listViewCustomerList;

    ArrayAdapter customerArrayAdapter;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCustomerName = findViewById(R.id.idEtCustomerName);
        etCustomerAge = findViewById(R.id.idEtCustomerAge);
        switchActiveCustomers = findViewById(R.id.idSwitchActiveCustomers);
        btnAdd = findViewById(R.id.idBtnAdd);
        btnViewAll = findViewById(R.id.idBtnViewAll);
        listViewCustomerList = findViewById(R.id.idListViewCustomerList);

        dataBaseHelper = new DataBaseHelper(MainActivity.this);

        ShowCustomersOnListView(dataBaseHelper);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerModel customerModel;
                try {
                    customerModel = new CustomerModel(-1,etCustomerName.getText().toString(),Integer.parseInt(etCustomerAge.getText().toString()),switchActiveCustomers.isChecked());
                    Toast.makeText(MainActivity.this,  customerModel.toString(), Toast.LENGTH_SHORT).show();
                }
                catch(Exception e) {
                    Toast.makeText(MainActivity.this, "Error! Creating Customer..", Toast.LENGTH_SHORT).show();
                    customerModel = new CustomerModel(-1,"error",0,false);
                }
                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                boolean success = dataBaseHelper.addOne(customerModel);
                Toast.makeText(MainActivity.this, "Success: "+success, Toast.LENGTH_SHORT).show();

                ShowCustomersOnListView(dataBaseHelper);


            }
        });

        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);

                ShowCustomersOnListView(dataBaseHelper);


//                Toast.makeText(MainActivity.this, everyone.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        listViewCustomerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CustomerModel clickedCustomer = (CustomerModel) adapterView.getItemAtPosition(i);

                dataBaseHelper.deleteOne(clickedCustomer);

                ShowCustomersOnListView(dataBaseHelper);
                Toast.makeText(MainActivity.this, "Deleted "+clickedCustomer, Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void ShowCustomersOnListView(DataBaseHelper dataBaseHelper2) {
        customerArrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper2.getEveryone());
        listViewCustomerList.setAdapter(customerArrayAdapter);
    }
}