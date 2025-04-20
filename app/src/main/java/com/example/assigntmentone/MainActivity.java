package com.example.assigntmentone;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assigntmentone.PCShop.PC;
import com.example.assigntmentone.PCShop.PCDA;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView lvitems;
    private FloatingActionButton btnsearch;
    private Button btncart;

    private ArrayList<PC> partList;
    private ArrayAdapter<PC> adapter;

    private HashMap<String, Integer> cartMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewSetUp();
        SearchView(btnsearch);
        cartView(btncart);
        showAllPcParts();

        lvitems.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = partList.get(position).toString();

            if (cartMap.containsKey(selectedItem)) {
                cartMap.put(selectedItem, cartMap.get(selectedItem) + 1);
            } else {
                cartMap.put(selectedItem, 1);
            }

            Toast.makeText(MainActivity.this, "Added to cart", Toast.LENGTH_SHORT).show();
        });
    }

    public void viewSetUp() {
        lvitems = findViewById(R.id.lvitems);
        btnsearch = findViewById(R.id.btnsearch);
        btncart = findViewById(R.id.btncart);
    }

    public void cartView(Button btn) {
        btn.setOnClickListener(v -> {
            JSONArray jsonArray = new JSONArray();
            for (Map.Entry<String, Integer> entry : cartMap.entrySet()) {
                JSONObject obj = new JSONObject();
                try {
                    obj.put("item", entry.getKey());
                    obj.put("qty", entry.getValue());
                    jsonArray.put(obj);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            TempDataHolder.jsonDataArray = jsonArray.toString();
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
        partList = new PCDA().getAllPcParts();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, partList);
        lvitems.setAdapter(adapter);
    }
}
