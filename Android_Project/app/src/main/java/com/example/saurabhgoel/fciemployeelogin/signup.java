package com.example.saurabhgoel.fciemployeelogin;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class signup extends AppCompatActivity {


    EditText edt1,edt2,edt3,edt4,edt5,edt6;
    String empid,name,pass,phone,email,question,answer,ques;
    Button btn1;
    private Spinner spinner ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        edt1= (EditText) findViewById(R.id.editText);
        edt2= (EditText) findViewById(R.id.editText2);
        edt3= (EditText) findViewById(R.id.editText3);
        edt4= (EditText) findViewById(R.id.editText4);
        edt5= (EditText) findViewById(R.id.editText5);
        edt6= (EditText) findViewById(R.id.editText11);
        spinner = (Spinner) findViewById(R.id.spinner);

        btn1=(Button) findViewById(R.id.button);




        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                empid=edt1.getText().toString();
                name=edt2.getText().toString();
                email= edt3.getText().toString();
                pass=edt4.getText().toString();
                phone = edt5.getText().toString();
                question=String.valueOf(spinner.getSelectedItem());
                answer=edt6.getText().toString();




                pass=MD5(pass);
                pass=MD5(pass);
                pass=MD5(pass);
                pass=MD5(pass);
                pass=MD5(pass);


                boolean inputatrate=email.contains("@");
                boolean inputdot=email.contains(".");
                if((inputatrate) && (inputdot) && (phone.length()==10) && (pass.length()>=8)){

                    new signup.senddata().execute(String.valueOf(empid),String.valueOf(name),String.valueOf(email),String.valueOf(pass),String.valueOf(phone),String.valueOf(question),String.valueOf(answer));}

                else if((phone.length()!=10))
                {
                    Toast.makeText(signup.this,"Invalid Phone number",Toast.LENGTH_LONG).show();
                }

                else if((!inputatrate) || (!inputdot))
                {
                    Toast.makeText(signup.this,"Invalid Email",Toast.LENGTH_LONG).show();
                }
                else if(pass.length()<8)
                {
                    Toast.makeText(signup.this,"Password should contain atleast 8 characters.",Toast.LENGTH_LONG).show();
                }
                else if(answer.equalsIgnoreCase(""))
                {
                    Toast.makeText(signup.this,"Answer cannot be empty.",Toast.LENGTH_LONG).show();
                }


            }
        });


    }


    public void onclicklogin(View view)
    {
        onBackPressed();

    }



    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Go Back");
        builder.setMessage("Any unsaved data will be lost.");
        builder.setPositiveButton("Back", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                signup.super.onBackPressed();
                finish();
                Intent intent = new Intent(signup.this, login.class);
                signup.this.startActivity(intent);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        builder.show();
    }


    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }


    public  class senddata extends AsyncTask<String,String,String> {

        BufferedReader br=null;
        URL url;
        String txt="";
        HttpURLConnection conn;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            try {             //url of your php file
                url = new URL("your php url here");
            } catch (MalformedURLException m1) {

                txt = "exception";
            }
            try {
                //establishing connection
                conn = (HttpURLConnection) url.openConnection();

                conn.setReadTimeout(10000);
                //connection timeout
                conn.setConnectTimeout(15000);
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                // appending the query parameters with key
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("empid", params[0])
                        .appendQueryParameter("name",params[1])
                        .appendQueryParameter("email",params[2])
                        .appendQueryParameter("pass",params[3])
                        .appendQueryParameter("phone",params[4])
                        .appendQueryParameter("question",params[5])
                        .appendQueryParameter("answer",params[6]);
                String query = builder.build().getEncodedQuery();
                //starting output stream to upload datat to server
                OutputStream os = conn.getOutputStream();
                BufferedWriter wr = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                //writing the data
                wr.write(query);
                wr.flush();
                wr.close();

                conn.connect();


            }catch (IOException e1){
                txt = "input";
            }try {

                int response_code = conn.getResponseCode();

                // Check if successful connection is established
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return (result.toString());

                } else {

                    return ("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                txt= "exception";
            }

            finally {
                conn.disconnect();
            }
            return txt;
        }







        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            if(s.equalsIgnoreCase("User Registered"))
            {
                Intent intent = new Intent(signup.this, login.class);
                signup.this.startActivity(intent);
                finish();
            }
            Toast.makeText(signup.this,s,Toast.LENGTH_LONG).show();
        }
    }



}
