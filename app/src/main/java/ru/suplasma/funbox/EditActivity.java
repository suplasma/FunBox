package ru.suplasma.funbox;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class EditActivity extends AppCompatActivity {

    private EditText name, price, quantity;
    private DataBase data = new DataBase(this);
    private int id;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        name = findViewById(R.id.editText);
        price = findViewById(R.id.editText2);
        quantity = findViewById(R.id.editText3);

        id = getIntent().getIntExtra("id", -1);

        if (id != -1) {
            name.setText(Progress.names.get(id));
            price.setText(String.valueOf(Progress.prices.get(id)));
            quantity.setText(String.valueOf(Progress.quantities.get(id)));
        }

        handler = new Handler() {
            @Override
            public void handleMessage(android.os.Message ms) {
                Toast.makeText(getApplicationContext(), R.string.strSave, Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(),StoreFrontActivity.class));
            }
        };

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSave: {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        data.write(id, name.getText().toString(), price.getText().toString(), quantity.getText().toString());

                        handler.sendEmptyMessage(1);
                    }
                });

                thread.start();

                break;
            }
            case R.id.btnCancel: {
                Toast.makeText(this, R.string.strCancel, Toast.LENGTH_SHORT).show();

                break;
            }
        }
        finish();


    }
}
