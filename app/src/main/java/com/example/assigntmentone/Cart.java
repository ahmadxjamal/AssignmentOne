package com.example.assigntmentone;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {

    private ListView lvitems;
    private TextView txtnf;
    private Button btnco;
    private JSONArray jsonArray;
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        lvitems = findViewById(R.id.lvcart);
        txtnf = findViewById(R.id.textView2);
        btnco = findViewById(R.id.btnco);

        if (TempDataHolder.jsonDataArray != null && !TempDataHolder.jsonDataArray.isEmpty()) {
            flag = true;
            try {
                jsonArray = new JSONArray(TempDataHolder.jsonDataArray);
                displayCart(jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(this, "Invalid cart data!", Toast.LENGTH_SHORT).show();
            }
        }

        if (flag) {
            txtnf.setVisibility(View.GONE);
            lvitems.setVisibility(View.VISIBLE);
            try {
                retrieveData(lvitems);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        } else {
            txtnf.setVisibility(View.VISIBLE);
            lvitems.setVisibility(View.GONE);
        }

        checkOut(btnco);
    }

    private void displayCart(JSONArray jsonArray) throws JSONException {
        ArrayList<String> cartDisplay = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject item = jsonArray.getJSONObject(i);
            String name = item.getString("item");
            int qty = item.getInt("qty");
            cartDisplay.add(name + "Quantity 1x" + qty);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cartDisplay);
        lvitems.setAdapter(adapter);
    }

    private void checkOut(Button btn) {
        btn.setOnClickListener(v -> {
            if (jsonArray != null && jsonArray.length() > 0) {
                SharedPreferences prefs = getSharedPreferences("Myapp", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("mydata", jsonArray.toString());
                editor.apply();
                Toast.makeText(this, "Checkout saved!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Cart is empty!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void retrieveData(ListView lv) throws JSONException {
        SharedPreferences prefs = getSharedPreferences("Myapp", MODE_PRIVATE);
        String data = prefs.getString("mydata", null);

        if (data != null) {
            JSONArray savedArray = new JSONArray(data);
            displayCart(savedArray);
        } else {
            flag = false;
        }
    }
}