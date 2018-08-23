package ru.suplasma.funbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    EditText name, price, quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        name = findViewById(R.id.editText);
        price = findViewById(R.id.editText2);
        quantity = findViewById(R.id.editText3);

        if (Progress.flag != -1) {
            name.setText(Progress.names.get(Progress.flag));
            price.setText(String.valueOf(Progress.prices.get(Progress.flag)));
            quantity.setText(String.valueOf(Progress.quantities.get(Progress.flag)));
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSave: {
                if (Progress.flag == -1) {
                    Progress.names.add(name.getText().toString());
                    Progress.prices.add(Integer.parseInt(price.getText().toString()));
                    Progress.quantities.add(Integer.parseInt(quantity.getText().toString()));
                } else {
                    Progress.names.set(Progress.flag, name.getText().toString());
                    Progress.prices.set(Progress.flag, Integer.parseInt(price.getText().toString()));
                    Progress.quantities.set(Progress.flag, Integer.parseInt(quantity.getText().toString()));
                }

                Toast.makeText(this, R.string.strSave, Toast.LENGTH_SHORT).show();

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
