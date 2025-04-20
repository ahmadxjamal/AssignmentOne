package com.example.assigntmentone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assigntmentone.PCShop.PC;
import com.example.assigntmentone.PCShop.PCDA;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Search extends AppCompatActivity {

    private EditText edtsearch;
    private Spinner spnselect;
    private CheckBox ckbr1, ckbr2, ckbr3;
    private Button btnsearch, btncart2;
    private ListView lvitems;
    private TextView txtnf;

    private ArrayList<PC> filteredpart = new ArrayList<>();
    private HashMap<String, Integer> cartMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        viewSetUp();
        spnfill();

        btnsearch.setOnClickListener(v -> filterParts());
        cartView(btncart2);
    }

    public void viewSetUp() {
        edtsearch = findViewById(R.id.edtsearch);
        spnselect = findViewById(R.id.spnselect);
        ckbr1 = findViewById(R.id.ckbr1);
        ckbr2 = findViewById(R.id.ckbr2);
        ckbr3 = findViewById(R.id.ckbr3);
        btnsearch = findViewById(R.id.btnsearch);
        lvitems = findViewById(R.id.lvsitems);
        txtnf = findViewById(R.id.txtnf);
        btncart2 = findViewById(R.id.cbtncart2);
    }

    public void spnfill() {
        String[] options = new PCDA().getCategories();
        ArrayAdapter<String> category = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        spnselect.setAdapter(category);
    }

    public void filterParts() {
        String search = edtsearch.getText().toString().trim().toLowerCase();
        String category = spnselect.getSelectedItem().toString();
        boolean price1 = ckbr1.isChecked();
        boolean price2 = ckbr2.isChecked();
        boolean price3 = ckbr3.isChecked();

        ArrayList<PC> allpart = new PCDA().getAllPcParts();
        filteredpart.clear();

        for (PC part : allpart) {
            String comp = part.getCompany().toLowerCase();
            String model = part.getModel().toLowerCase();

            boolean matchCategory = category.equals("Any") || part.getCategory().equals(category);
            boolean matchSearch = model.contains(search) || comp.contains(search);

            boolean matchPrice = (price1 && part.getPrice() < 1000)
                    || (price2 && part.getPrice() >= 1000 && part.getPrice() <= 3000)
                    || (price3 && part.getPrice() > 3000);

            if (matchCategory && matchSearch && matchPrice) {
                filteredpart.add(part);
            }
        }

        if (filteredpart.isEmpty()) {
            txtnf.setVisibility(View.VISIBLE);
            lvitems.setVisibility(View.GONE);
        } else {
            txtnf.setVisibility(View.GONE);
            lvitems.setVisibility(View.VISIBLE);
        }

        ArrayAdapter<PC> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filteredpart);
        lvitems.setAdapter(adapter);

        lvitems.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = filteredpart.get(position).toString();

            if (cartMap.containsKey(selectedItem)) {
                int qty = cartMap.get(selectedItem);
                cartMap.put(selectedItem, qty + 1);
                Toast.makeText(Search.this, "Increased quantity", Toast.LENGTH_SHORT).show();
            } else {
                cartMap.put(selectedItem, 1);
                Toast.makeText(Search.this, "Item added to cart", Toast.LENGTH_SHORT).show();
            }
        });
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

            Intent intent = new Intent(Search.this, Cart.class);
            startActivity(intent);
        });
    }
}
