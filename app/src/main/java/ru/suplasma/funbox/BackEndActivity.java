package ru.suplasma.funbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BackEndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_end);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn: {
                finish();

                break;
            }

            case R.id.button2: {
                Progress.flag = -1;
                startActivity(new Intent(this, EditActivity.class));

                break;
            }
        }
    }
}