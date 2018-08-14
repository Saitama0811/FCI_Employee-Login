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

public class viewdetail1 extends AppCompatActivity {


    String username;

    int response_code;
    String str1,str2,str3,str4,str5,str6,str7,str8,str9,str10,str11;
    TextView edt12,edt13,edt14,edt15,edt16,edt17,edt18,edt19,edt20,edt21,edt22;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdetail1);

        Intent i=getIntent();
        username=i.getStringExtra("employeeid");
        new AsyncLogin().execute(String.valueOf(username));

    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Exit");
        builder.setMessage("Do you really want to exit?");
        builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                viewdetail1.super.onBackPressed();
                finish();
                Intent intent = new Intent(viewdetail1.this, login.class);
                viewdetail1.this.startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        builder.show();
    }





    public void view2detail(View view) {

        Intent i = getIntent();
        username=i.getStringExtra("employeeid");
        Intent intent = new Intent(viewdetail1.this, viewdetail2.class);
        intent.putExtra("employeeid",username);
        viewdetail1.this.startActivity(intent);

        finish();
    }

    public void viewdetailback(View v1) {
        Intent i = getIntent();
        username=i.getStringExtra("employeeid");
        Intent intent = new Intent(viewdetail1.this, viewdetail.class);
        intent.putExtra("employeeid",username);
        viewdetail1.this.startActivity(intent);
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




            edt12=(TextView) findViewById(R.id.textView28);
            edt13=(TextView) findViewById(R.id.textView30);
            edt14=(TextView) findViewById(R.id.textView32);
            edt15=(TextView) findViewById(R.id.textView34);
            edt16=(TextView) findViewById(R.id.textView36);
            edt17=(TextView) findViewById(R.id.textView38);
            edt18=(TextView) findViewById(R.id.textView40);
            edt19=(TextView) findViewById(R.id.textView42);
            edt20=(TextView) findViewById(R.id.textView44);
            edt21=(TextView) findViewById(R.id.textView46);
            edt22=(TextView) findViewById(R.id.textView48);


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

            edt12.setText(str1);
            edt13.setText(str2);
            edt14.setText(str3);
            edt15.setText(str4);
            edt16.setText(str5);
            edt17.setText(str6);
            edt18.setText(str7);
            edt19.setText(str8);
            edt20.setText(str9);
            edt21.setText(str10);
            edt22.setText(str11);


            //Toast.makeText(viewdetail1.this,s,Toast.LENGTH_LONG).show();



        }

    }


}
