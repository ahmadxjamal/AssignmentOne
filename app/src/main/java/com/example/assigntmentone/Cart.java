package com.example.assigntmentone;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;


public class Cart extends AppCompatActivity {
    private ListView lvitems;
    private TextView txtnf;
    private Button btnco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        lvitems = findViewById(R.id.lvcart);
        txtnf = findViewById(R.id.textView2);
        btnco = findViewById(R.id.btnco);
        Intent intent = getIntent();

        String selecteditem = intent.getStringExtra(MainActivity.pcpart);
        System.out.println("qqww" + selecteditem);

        ArrayList<String> cart = new ArrayList<>();

        if (selecteditem != null) {
            cart.add(selecteditem);
            ArrayAdapter<String> adater = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cart);
            adater.notifyDataSetChanged();
            lvitems.setAdapter(adater);
        }

    }
}