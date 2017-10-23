package com.example.asus.assignment1;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.content.Context;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    DateFormat fmtDateAndTime = DateFormat.getDateInstance();
    Calendar myCalendar = Calendar.getInstance();
    public static final String PREFS_NAME = "myPrefFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText name = (EditText) findViewById(R.id.editname);
        final EditText lname = (EditText) findViewById(R.id.editlastname);
        final EditText mail = (EditText) findViewById(R.id.editemail);
        final TextView dobText = (TextView) findViewById(R.id.dobText);
        final EditText phone = (EditText) findViewById(R.id.editno);
        final Button submit = (Button) findViewById(R.id.submit);
        final Spinner spinner = (Spinner)findViewById(R.id.spinner);
        final Button dobButton = (Button)findViewById(R.id.setDOB);
        final RadioButton prefBtn = (RadioButton)findViewById(R.id.pref_radio);
        final RadioButton fileBtn = (RadioButton)findViewById(R.id.file_radio);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.NameTitle,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        dobButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                new DatePickerDialog(MainActivity.this,d,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        updateLabel();
        prefBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                SharedPreferences shared_pref = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
                SharedPreferences.Editor editor = shared_pref.edit();
                editor.putString("title",spinner.getSelectedItem().toString());
                editor.putString("name",name.getText().toString());
                editor.putString("lastname",lname.getText().toString());
                editor.putString("dobText",dobText.getText().toString());
                editor.putString("mail",mail.getText().toString());
                editor.putString("phone",phone.getText().toString());
                editor.commit();
            }
        });
        fileBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String fileName = "personalInformation.txt";
                File file = new File(getExternalFilesDir(null),fileName);
                FileOutputStream outputStream;
                String titleString  = spinner.getSelectedItem().toString();
                String nameString = name.getText().toString();
                String lnameString = lname.getText().toString();
                String dobTextString = dobText.getText().toString();
                String mailString = mail.getText().toString();
                String phoneText = phone.getText().toString();
                String info = titleString+"\n"+nameString+"\n"+lnameString+"\n"+dobTextString+"\n"+mailString+"\n"+phoneText;
                try{
                    outputStream = openFileOutput(fileName,MODE_PRIVATE);
                    outputStream.write(info.getBytes());
                    outputStream.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                String value6 = spinner.getSelectedItem().toString();
                String value = name.getText().toString();
                String value2 = lname.getText().toString();
                String value4 = dobText.getText().toString();
                String value3 = mail.getText().toString();
                String regexCheckMail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                if(!value3.matches(regexCheckMail) || value3.length() == 0) {
                    mail.setError("Email format is not valid");
                }
                String value5 = phone.getText().toString();
                if (phone.getText().length() != 10) {
                    phone.setError("Invalid format");
                }
                if (!(Character.isUpperCase(value.charAt(0)) && Character.isUpperCase(value2.charAt(0)))) {
                    name.setError("First letter should be capital letter");
                    lname.setError("First letter should be capital letter");
                }
                String regex = "^\\d{10}$";
                if (value5.matches(regex) && Character.isUpperCase(value.charAt(0))
                        && Character.isUpperCase(value2.charAt(0)) && value3.matches(regexCheckMail) || value3.length() == 0) {
                    i.putExtra("extra", value);
                    i.putExtra("extra2", value2);
                    i.putExtra("extra3", value3);
                    i.putExtra("extra4",value4);
                    i.putExtra("extra5", value5);
                    i.putExtra("extra6",value6);
                    startActivity(i);
                }
            }

        });

    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener(){
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
            myCalendar.set(Calendar.YEAR,year);
            myCalendar.set(Calendar.MONTH,monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            Calendar today = Calendar.getInstance();
            if(myCalendar.before(today)){
                updateLabel();
            }
        }
    };
    private void updateLabel(){
        TextView dob = (TextView)findViewById(R.id.dobText);
        fmtDateAndTime = new SimpleDateFormat("dd-MM-yyyy");
        dob.setText(fmtDateAndTime.format(myCalendar.getTime()));
        Log.d("",dob.getText().toString());
        }
    }