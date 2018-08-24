package ru.suplasma.funbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class BackEndActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_end);

        listView = findViewById(R.id.list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, Progress.names);


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