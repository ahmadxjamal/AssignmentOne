package com.example.assigntmentone;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assigntmentone.PCShop.PC;
import com.example.assigntmentone.PCShop.PCDA;

import java.util.ArrayList;

public class Search extends AppCompatActivity {
    private EditText edtsearch;
    private Spinner spnselect;
    private CheckBox ckbr1;
    private CheckBox ckbr2;
    private CheckBox ckbr3;
    private Button btnsearch;
    private ListView lvitems;
    private TextView txtnf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        viewSetUp();
        spnfill();

        btnsearch.setOnClickListener(v -> filterParts());
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
        ArrayList<PC> filteredpart = new ArrayList<>();
        for (PC part : allpart) {
            String comp = part.getCompany().toLowerCase();
            String model = part.getModel().toLowerCase();
            if (part.getCategory().equals(category) && (model.contains(search) || comp.contains(search))) {
                if (price1 && part.getPrice() < 1000)
                    filteredpart.add(part);
                else if (price2 && part.getPrice() >= 1000 && part.getPrice() <= 3000)
                    filteredpart.add(part);
                else if (price3 && part.getPrice() > 3000)
                    filteredpart.add(part);

            } else if (category.equals("Any") && (model.contains(search) || comp.contains(search))) {
                if (price1 && part.getPrice() < 1000)
                    filteredpart.add(part);
                else if (price2 && part.getPrice() >= 1000 && part.getPrice() <= 3000)
                    filteredpart.add(part);
                else if (price3 && part.getPrice() > 3000)
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

    }
}