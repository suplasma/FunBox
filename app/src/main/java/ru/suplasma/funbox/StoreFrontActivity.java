package ru.suplasma.funbox;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.LinkedList;

public class StoreFrontActivity extends AppCompatActivity implements View.OnTouchListener {

    private ViewFlipper flipper = null;
    private float fromPosition;
    private LinkedList<String> names;
    private LinkedList<Integer> prices, quantities;
    private TextView name, price, quantity;
    private int page = 0, maxPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_front);

        LinearLayout mainLayout = findViewById(R.id.main_layout);
        flipper = findViewById(R.id.flip);

        mainLayout.setOnTouchListener(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        flipper.addView(inflater.inflate(R.layout.layout_product, null));

        name = findViewById(R.id.text);
        price = findViewById(R.id.text2);
        quantity = findViewById(R.id.text3);

        names = new LinkedList<>();
        prices = new LinkedList<>();
        quantities = new LinkedList<>();

        read();

        maxPage = quantities.size();

        while (quantities.get(page) == 0) {
            page++;
            if (page == maxPage)
                page = 0;
        }

        refreshScreen();
    }

    private void refreshScreen() {
        name.setText(names.get(page));
        price.setText(String.valueOf(prices.get(page)));
        quantity.setText(String.valueOf(quantities.get(page)));

    }

    private void read() {
        for (int i = 0; i < 10; i++) {
            names.add(i + "");
            prices.add(i);
            quantities.add(i);
        }
    }

    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                fromPosition = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                float toPosition = event.getX();
                if (fromPosition > toPosition) {
                    showNext();
                } else if (fromPosition < toPosition) {
                    showPrevious();
                }
            default:
                break;
        }
        return true;
    }

    private void showNext() {
        if (page + 1 < maxPage)
            page++;
        else
            page = 0;

        while (quantities.get(page) == 0) {
            page++;
            if (page == maxPage)
                page = 0;
        }

        flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.go_next_in));
        flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.go_next_out));
        flipper.showNext();

        refreshScreen();
    }

    private void showPrevious() {
        if (page - 1 >= 0)
            page--;
        else
            page = maxPage - 1;

        while (quantities.get(page) == 0) {
            page--;
            if (page < 0)
                page = maxPage - 1;
        }

        flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.go_prev_in));
        flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.go_prev_out));
        flipper.showPrevious();

        refreshScreen();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn: {
                startActivity(new Intent(this, BackEndActivity.class));
                break;
            }
            case R.id.button: {
                if (quantities.get(page) > 0) {
                    quantities.set(page, quantities.get(page) - 1);
                    quantity.setText(String.valueOf(quantities.get(page)));
                    Toast.makeText(this, R.string.strBought, Toast.LENGTH_SHORT).show();

                    if (quantities.get(page) == 0) {

                        showNext();

                    }
                }
                break;
            }
        }
    }
}