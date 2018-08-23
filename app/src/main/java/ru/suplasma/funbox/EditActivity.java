package ru.suplasma.funbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    private EditText name, price, quantity;
    private DataBase data = new DataBase(this);
    private int id;

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
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSave: {
                if (id == -1) {
                    Progress.names.add(name.getText().toString());
                    Progress.prices.add(Integer.parseInt(price.getText().toString()));
                    Progress.quantities.add(Integer.parseInt(quantity.getText().toString()));
                } else {
                    Progress.names.set(id, name.getText().toString());
                    Progress.prices.set(id, Integer.parseInt(price.getText().toString()));
                    Progress.quantities.set(id, Integer.parseInt(quantity.getText().toString()));
                }

                Toast.makeText(this, R.string.strSave, Toast.LENGTH_SHORT).show();

                data.write(id, name.getText().toString(), price.getText().toString(), quantity.getText().toString());

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
