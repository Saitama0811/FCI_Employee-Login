package com.example.saurabhgoel.fciemployeelogin;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class forgetpass extends AppCompatActivity {

    EditText edt1,edt2,edt3,edt4,edt5;
    Button btn1,btn2;
    TextView textView1;
    int response_code;
    String empid,email,phone,answer,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpass);
        textView1=(TextView)findViewById(R.id.textView3);
    }



    public void checkDetails(View args0) {

        edt1= (EditText) findViewById(R.id.editText6);
        edt2= (EditText) findViewById(R.id.editText9);
        edt3= (EditText) findViewById(R.id.editText10);


        empid = edt1.getText().toString();
        email =edt2.getText().toString();
        phone =edt3.getText().toString();



        // Initialize  AsyncLogin() class with employeeid, email and phone
        new AsyncLogin2().execute(String.valueOf(empid),String.valueOf(email),String.valueOf(phone));

    }

    public void changepass(View view) {
        edt4= (EditText) findViewById(R.id.editText12);
        edt5= (EditText) findViewById(R.id.editText13);

        answer=edt4.getText().toString();
        password=edt5.getText().toString();
        empid=edt1.getText().toString();
        password=MD5(password);
        password=MD5(password);
        password=MD5(password);
        password=MD5(password);
        password=MD5(password);


        new AsyncLogin1().execute(String.valueOf(empid), String.valueOf(answer),String.valueOf(password));
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



    private class AsyncLogin1 extends AsyncTask<String, String, String>
    {

        HttpURLConnection conn;
        URL url = null;
        BufferedReader br=null;
        String txt="";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            //this method will be running on UI thread

        }
        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your php file resides
                url = new URL("your php url here");


            } catch (MalformedURLException e) {
                txt="exception1";
            }
            try {
                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection)url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);



                // setDoInput and setDoOutput method depict handling of both send and receive
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");

                // Append parameters to URL
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("empid", params[0])
                        .appendQueryParameter("answer", params[1])
                        .appendQueryParameter("password", params[2]);

                String query = builder.build().getEncodedQuery();

                // Open connection for sending data
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                //os.close();
                conn.connect();


            } catch (IOException e1) {
                // TODO Auto-generated catch block
                txt="exception2";
            }

            try {

                response_code = conn.getResponseCode();

                // Check if successful connection made
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
                    return(result.toString());

                }else{

                    return("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                txt="exception3";
            } finally {
                conn.disconnect();
            }
            return txt;


        }

        @Override
        protected void onPostExecute(String s) {

            //this method will be running on UI thread
            super.onPostExecute(s);

            if(s.equalsIgnoreCase("Password Changed"))
            {
                Intent intent = new Intent(forgetpass.this, login.class);
                forgetpass.this.startActivity(intent);
                finish();
            }


            else if(s.equalsIgnoreCase("Incorrect security answer") || s.equalsIgnoreCase("unsuccessful"))
            {

                Toast.makeText(forgetpass.this,"Invalid answer",Toast.LENGTH_LONG).show();
            }

            else if(s.equalsIgnoreCase("fail") || s.equalsIgnoreCase("connection problem"))
            {
                Toast.makeText(forgetpass.this,"Network Problem",Toast.LENGTH_LONG).show();
            }






        }

    }


    private class AsyncLogin2 extends AsyncTask<String, String, String>
    {

        HttpURLConnection conn;
        URL url = null;
        BufferedReader br=null;
        String txt="";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            //this method will be running on UI thread

        }
        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your php file resides
                url = new URL("your php url here");


            } catch (MalformedURLException e) {
                txt="exception1";
            }
            try {
                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection)url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);



                // setDoInput and setDoOutput method depict handling of both send and receive
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");

                // Append parameters to URL
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("empid", params[0])
                        .appendQueryParameter("email", params[1])
                        .appendQueryParameter("phone", params[2]);

                String query = builder.build().getEncodedQuery();

                // Open connection for sending data
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                //os.close();
                conn.connect();


            } catch (IOException e1) {
                // TODO Auto-generated catch block
                txt="exception2";
            }

            try {

                response_code = conn.getResponseCode();

                // Check if successful connection made
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
                    return(result.toString());

                }else{

                    return("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                txt="exception3";
            } finally {
                conn.disconnect();
            }
            return txt;


        }

        @Override
        protected void onPostExecute(String s) {

            //this method will be running on UI thread
            super.onPostExecute(s);




            if(s.equalsIgnoreCase("Wrong credentials") || s.equalsIgnoreCase("unsuccessful"))
            {

                Toast.makeText(forgetpass.this,"Invalid Employee ID/Email/Phone",Toast.LENGTH_LONG).show();
            }

            else if(s.equalsIgnoreCase("fail") || s.equalsIgnoreCase("connection problem"))
            {
                Toast.makeText(forgetpass.this,"Network Problem",Toast.LENGTH_LONG).show();
            }
            else
            {
                textView1.setText(s);
            }





        }

    }
}
