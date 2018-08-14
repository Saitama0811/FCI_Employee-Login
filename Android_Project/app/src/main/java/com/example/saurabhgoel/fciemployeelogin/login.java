package com.example.saurabhgoel.fciemployeelogin;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

public class login extends AppCompatActivity {


    int response_code;
    String username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Exit");
        builder.setMessage("Do you want to Exit? ");
        builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                login.super.onBackPressed();
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        builder.show();
    }



    public void singup1(View view) {
        Intent intent = new Intent(login.this, signup.class);
        login.this.startActivity(intent);
        finish();

    }

    public void checkLogin(View args0) {

        EditText username1= (EditText) findViewById(R.id.editText7);
        EditText password1= (EditText) findViewById(R.id.editText8);

        // Get text from email and password field
        username = username1.getText().toString();
        password =password1.getText().toString();
        password=MD5(password);
        password=MD5(password);
        password=MD5(password);
        password=MD5(password);
        password=MD5(password);


        // Initialize  AsyncLogin() class with email and password
        new AsyncLogin().execute(String.valueOf(username),String.valueOf(password));

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




    public void forgetpass(View view) {

        Intent intent = new Intent(login.this, forgetpass.class);
        login.this.startActivity(intent);
        finish();
    }





    private class AsyncLogin extends AsyncTask<String, String, String>
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
                        .appendQueryParameter("username", params[0])
                        .appendQueryParameter("password", params[1]);
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

            if(s.equalsIgnoreCase("success"))
            {
                Intent intent = new Intent(login.this, viewdetail.class);
                intent.putExtra("employeeid",username);
                login.this.startActivity(intent);
                finish();
            }
            else if(s.equalsIgnoreCase("Wrong credentials") || s.equalsIgnoreCase("unsuccessful"))
            {

                Toast.makeText(login.this,"Invalid Employee ID/Password",Toast.LENGTH_LONG).show();
            }

            else if(s.equalsIgnoreCase("fail") || s.equalsIgnoreCase("connection problem"))
            {
                Toast.makeText(login.this,"Network Problem",Toast.LENGTH_LONG).show();
            }


        }

    }

}
