package ru.suplasma.funbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BackEndActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_end);

        listView=findViewById(R.id.list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, Progress.names);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Progress.flag = position;
                startActivity(new Intent(BackEndActivity.this, EditActivity.class));
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnStoreFront: {
                finish();

                break;
            }

            case R.id.btnEdit: {
                Progress.flag = -1;
                startActivity(new Intent(this, EditActivity.class));

                break;
            }
        }
    }
}