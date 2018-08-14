package com.example.saurabhgoel.fciemployeelogin;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
import java.util.StringTokenizer;

public class viewdetail extends AppCompatActivity {


    String username;
    int response_code;
    String str1,str2,str3,str4,str5,str6,str7,str8,str9,str10,str11;
    TextView edt1,edt2,edt3,edt4,edt5,edt6,edt7,edt8,edt9,edt10,edt11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdetail);

        Intent intent = getIntent();
        username=intent.getStringExtra("employeeid");
        new viewdetail.AsyncLogin().execute(String.valueOf(username));
    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Exit");
        builder.setMessage("Do you really want to exit?");
        builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                viewdetail.super.onBackPressed();
                finish();
                Intent intent = new Intent(viewdetail.this, login.class);
                viewdetail.this.startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        builder.show();
    }

    public void view1detail(View view) {

        Intent i = getIntent();
        username=i.getStringExtra("employeeid");
        Intent intent = new Intent(viewdetail.this, viewdetail1.class);
        intent.putExtra("employeeid",username);
        viewdetail.this.startActivity(intent);
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
                        .appendQueryParameter("username", params[0]);
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
            StringTokenizer st = new StringTokenizer(s);




            edt1=(TextView) findViewById(R.id.textView6);
            edt2=(TextView) findViewById(R.id.textView8);
            edt3=(TextView) findViewById(R.id.textView10);
            edt4=(TextView) findViewById(R.id.textView12);
            edt5=(TextView) findViewById(R.id.textView14);
            edt6=(TextView) findViewById(R.id.textView16);
            edt7=(TextView) findViewById(R.id.textView18);
            edt8=(TextView) findViewById(R.id.textView20);
            edt9=(TextView) findViewById(R.id.textView22);
            edt10=(TextView) findViewById(R.id.textView24);
            edt11=(TextView) findViewById(R.id.textView26);


            str1=st.nextToken(";");
            str2=st.nextToken(";");
            str3=st.nextToken(";");
            str4=st.nextToken(";");
            str5=st.nextToken(";");
            str6=st.nextToken(";");
            str7=st.nextToken(";");
            str8=st.nextToken(";");
            str9=st.nextToken(";");
            str10=st.nextToken(";");
            str11=st.nextToken(";");

            edt1.setText(str1);
            edt2.setText(str2);
            edt3.setText(str3);
            edt4.setText(str4);
            edt5.setText(str5);
            edt6.setText(str6);
            edt7.setText(str7);
            edt8.setText(str8);
            edt9.setText(str9);
            edt10.setText(str10);
            edt11.setText(str11);


            //Toast.makeText(viewdetail.this,s,Toast.LENGTH_LONG).show();



        }

    }



}
