package com.example.assigntmentone;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assigntmentone.PCShop.PCDA;
import com.example.assigntmentone.PCShop.PC;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String pcpart = "PCPART";
    private ListView lvitems;
    private FloatingActionButton btnsearch;
    private Button btncart;
    private ArrayList<PC> part;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewSetUp();
        SearchView(btnsearch);
        cartView(btncart);
        showAllPcParts();

        lvitems.setOnItemClickListener((parent, view, position, id) -> {
            String selecteditem = String.valueOf(part.get(position));
//            System.out.println("qqww"+selecteditem);
            Intent intent = new Intent(MainActivity.this, Cart.class);
            intent.putExtra(pcpart, selecteditem);
            startActivity(intent);

            Toast.makeText(MainActivity.this, "Item added to cart", Toast.LENGTH_SHORT).show();
        });

    }

    public void viewSetUp() {
        lvitems = findViewById(R.id.lvitems);
        btnsearch = findViewById(R.id.btnsearch);
        btncart = findViewById(R.id.btncart);
    }

    public void cartView(Button btn) {
        btn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Cart.class);
            startActivity(intent);
        });
    }

    public void SearchView(FloatingActionButton btn) {
        btn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Search.class);
            startActivity(intent);
        });
    }

    private void showAllPcParts() {
        part = new PCDA().getAllPcParts();
        ArrayAdapter<PC> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, part);
        lvitems.setAdapter(adapter);
    }

}
