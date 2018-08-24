package ru.suplasma.funbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class BackEndActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_end);

        listView = findViewById(R.id.list);

        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        HashMap<String, String> map;

        for (int i = 0; i < Progress.names.size(); i++) {
            map = new HashMap<>();
            map.put("Name", Progress.names.get(i));
            map.put("Quantity", String.valueOf(Progress.quantities.get(i)) + " шт.");
            arrayList.add(map);
        }


        SimpleAdapter adapter = new SimpleAdapter(this, arrayList, R.layout.latout_list_item,
                new String[]{"Name", "Quantity"},
                new int[]{R.id.text1, R.id.text2});


        try {
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    startActivity(new Intent(BackEndActivity.this, EditActivity.class).putExtra("id", position));
                }
            });
        } catch (NullPointerException e) {
            finish();
        }

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnStoreFront: {
                finish();

                break;
            }

            case R.id.btnEdit: {
                startActivity(new Intent(this, EditActivity.class).putExtra("id", -1));

                break;
            }
        }
    }
}