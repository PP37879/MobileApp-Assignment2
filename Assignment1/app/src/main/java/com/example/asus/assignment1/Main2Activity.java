package com.example.asus.assignment1;

import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.util.Locale;


public class Main2Activity extends AppCompatActivity {

    DateFormat fmtDateAndTime = DateFormat.getDateInstance();
    Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView txtname = (TextView) findViewById(R.id.txtname);
        String extra = getIntent().getStringExtra("extra");
        String extra6 = getIntent().getStringExtra("extra6");
        txtname.setText(extra6+" "+extra);

        TextView txtlname = (TextView) findViewById(R.id.txtlastname);
        String extra2 = getIntent().getStringExtra("extra2");
        txtlname.setText(extra2);

        TextView txtemail = (TextView) findViewById(R.id.txtemail);
        String extra3 = getIntent().getStringExtra("extra3");
        txtemail.setText(extra3);

        TextView txtage = (TextView) findViewById(R.id.txtage);
        String extra4 = getIntent().getStringExtra("extra4");
        myCalendar.set(Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH);
        SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy");
        Calendar bd = Calendar.getInstance();
        try {
            bd.setTime(format.parse(extra4));
            Calendar today = Calendar.getInstance();
            today.setTime(format.parse(today.getTime()+""));
            int age = today.get(Calendar.YEAR)-bd.get(Calendar.YEAR);
            txtage.setText(today.get(Calendar.YEAR)+"");
        } catch (ParseException e) {
            e.printStackTrace();
        }


        TextView txtphone = (TextView) findViewById(R.id.txtno);
        String extra5 = getIntent().getStringExtra("extra5");
        txtphone.setText(extra5);

        if (Integer.valueOf(txtage.getText().toString()) < 16 && Integer.valueOf(txtage.getText().toString()) >= 0) {
            ImageView imgView = (ImageView) findViewById(R.id.imgView);
            imgView.setImageResource(R.drawable.child2);
            imgView.getLayoutParams().height = (int) getResources().getDimension(R.dimen.img_h);
            imgView.getLayoutParams().width = (int) getResources().getDimension(R.dimen.img_w);
        }

        if (Integer.valueOf(txtage.getText().toString()) >= 16 && Integer.valueOf(txtage.getText().toString()) < 26) {
            ImageView imgView = (ImageView) findViewById(R.id.imgView);
            imgView.setImageResource(R.drawable.teen);
            imgView.getLayoutParams().height = (int) getResources().getDimension(R.dimen.img_h);
            imgView.getLayoutParams().width = (int) getResources().getDimension(R.dimen.img_w);
        }

        if (Integer.valueOf(txtage.getText().toString()) >= 26 && Integer.valueOf(txtage.getText().toString()) < 61) {
            ImageView imgView = (ImageView) findViewById(R.id.imgView);
            imgView.setImageResource(R.drawable.work);
            imgView.getLayoutParams().height = (int) getResources().getDimension(R.dimen.img_h);
            imgView.getLayoutParams().width = (int) getResources().getDimension(R.dimen.img_w);
        }

        if (Integer.valueOf(txtage.getText().toString()) >= 61 && Integer.valueOf(txtage.getText().toString()) < 151) {
            ImageView imgView = (ImageView) findViewById(R.id.imgView);
            imgView.setImageResource(R.drawable.old);
            imgView.getLayoutParams().height = (int) getResources().getDimension(R.dimen.img_h);
            imgView.getLayoutParams().width = (int) getResources().getDimension(R.dimen.img_w);
        }

    }
}
