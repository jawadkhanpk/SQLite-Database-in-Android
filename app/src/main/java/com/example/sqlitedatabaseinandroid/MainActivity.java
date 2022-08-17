package com.example.sqlitedatabaseinandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etCustomerName, etCustomerAge;
    Switch switchActiveCustomers;
    Button btnAdd, btnViewAll;
    ListView listViewCustomerList;

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


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "add working", Toast.LENGTH_SHORT).show();
            }
        });

        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "view working", Toast.LENGTH_SHORT).show();
            }
        });
    }
}