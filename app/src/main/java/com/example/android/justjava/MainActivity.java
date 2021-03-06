package com.example.android.justjava;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

import java.text.NumberFormat;
import android.media.MediaPlayer;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    int quantity = 0;
    int eachPrice = 5;
    int randomNum = 42;
    String name;
    boolean hasWhippedCream = false;
    boolean hasChocolate = false;
    private MediaPlayer mp;
    //CheckBox whippedCreamBox = (CheckBox) findViewById(R.id.whipped_cream_checkBox);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        mp = MediaPlayer.create(this, R.raw.messenger_sound);

        Button play_button = (Button) this.findViewById(R.id.button2);
        play_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Log.v(TAG, "Playing sound...");
                stopPlaying();
                mp = MediaPlayer.create(MainActivity.this, R.raw.messenger_sound);
                mp.start();
                //submitOrder();

            }
        });
    }

    private void stopPlaying(){
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }
    /**
     * This method is called when the order button is clicked.
     */
//    public void whippedCream(View view) {
//        hasWhippedCream = whippedCreamBox.isChecked();
//        displayPrice(quantity * (eachPrice + 1));
//    }
    private String createOrderSummary() {
        String priceMessage = "Name: " + name;
        //(x > 0)?x:-x
        priceMessage += "\nAdd whipped cream? " + ((hasWhippedCream)?"yes":"no");
        priceMessage += "\nAdd chocolate? " + ((hasChocolate)?"yes":"no");
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + quantity * eachPrice;
        priceMessage += "\n\nThank you!";
        return priceMessage;
    }

    public void switchActivity(int AvitivityName, String priceMessage){

        switch (AvitivityName) {
            case 2:
                Intent myIntent = new Intent(this, ShowOrderActivity.class);
                myIntent.putExtra("ORDER_LIST", priceMessage);
                startActivity(myIntent);
            break;
        }

    }

    public void submitOrder(View v){
        //get hasWhippedCream & hasChocolate
        CheckBox whippedCreamBox = (CheckBox) findViewById(R.id.whipped_cream_checkBox);
        hasWhippedCream = whippedCreamBox.isChecked();
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        hasChocolate = chocolateCheckBox.isChecked();
        if(hasWhippedCream) {
            eachPrice += 1;
        }
        if(hasChocolate){
            eachPrice += 2;
        }
        displayPrice(quantity * (eachPrice));

        //get name
        EditText text = (EditText) findViewById(R.id.name_field);
        name = text.getText().toString();
        //make order list & switch
        switchActivity( 2, createOrderSummary() );
        //reset
        eachPrice = 5;
    }

    public void randomCups(View view) {
        quantity = random_winner();
        display(quantity);
        displayPrice(quantity * eachPrice);
    }

    public void resetCups(View view) {
        quantity = 0;
        display(quantity);
        displayPrice(quantity * eachPrice);
    }

    public void increment(View view) {
        display(++quantity);
        displayPrice(quantity * eachPrice);
    }

    public void decrement(View view) {
        if( quantity > 0 )
            display(--quantity);
        else
            display(quantity);
        displayPrice(quantity * eachPrice);
    }

    public void addPoints(View view) {

        int btnId = view.getId();

        switch (btnId) {

            case R.id.add5pointsBtn:
                quantity += 5;
                break;
            case R.id.add10pointsBtn:
                quantity += 10;
                break;
            case R.id.add20pointsBtn:
                quantity += 20;
                break;
            case R.id.add50pointsBtn:
                quantity += 50;
                break;
        }
        display(quantity);
        displayPrice(quantity * eachPrice);

    }


    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    public int random_winner() {
        return (int) (Math.random() * randomNum + 1);
    }


    public static class MainActivity2 extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main2);
        }
    }
}