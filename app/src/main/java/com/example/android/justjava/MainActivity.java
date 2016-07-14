package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    int quantity = 0;
    int eachPrice = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void randomCups(View view) {
        quantity = random_winner();
        display(quantity);
        displayPrice(quantity * eachPrice);
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void increment(View view) {
        display(++quantity);
        displayPrice(quantity * eachPrice);
    }
    public void decrement(View view) {
        display(--quantity);
        displayPrice(quantity * eachPrice);
    }
    public void addPoints(View view){

        int btnId = view.getId();

        switch (btnId){

            case R.id.add5pointsBtn:
                quantity+=5;
                break;
            case R.id.add10pointsBtn:
                quantity+=10;
                break;
            case R.id.add20pointsBtn:
                quantity+=20;
                break;
            case R.id.add50pointsBtn:
                quantity+=50;
                break;
        }
        display(quantity);
        displayPrice(quantity * eachPrice);

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    public int random_winner() {
        return (int)(Math.random()*42+1);
    }



}