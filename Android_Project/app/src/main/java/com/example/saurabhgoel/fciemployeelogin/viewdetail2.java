package com.example.saurabhgoel.fciemployeelogin;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
import java.util.StringTokenizer;

public class viewdetail2 extends AppCompatActivity {

    String username;

    int response_code;
    String str1,str2,str3,str4,str5,str6,str7,str8,str9,str10,str11;
    TextView edt23,edt24,edt25,edt26,edt27,edt28,edt29,edt30,edt31,edt32,edt33;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdetail2);

        Intent i=getIntent();
        username=i.getStringExtra("employeeid");
        new viewdetail2.AsyncLogin().execute(String.valueOf(username));
    }

    public void view3detail(View view) {
        Intent i = getIntent();
        username=i.getStringExtra("employeeid");

        Intent intent = new Intent(viewdetail2.this, viewdetail3.class);
        intent.putExtra("employeeid",username);
        viewdetail2.this.startActivity(intent);
        finish();
    }

    public void view1detail(View view) {
        Intent i = getIntent();
        username=i.getStringExtra("employeeid");

        Intent intent = new Intent(viewdetail2.this, viewdetail1.class);
        intent.putExtra("employeeid",username);
        viewdetail2.this.startActivity(intent);
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




            edt23=(TextView) findViewById(R.id.textView50);
            edt24=(TextView) findViewById(R.id.textView52);
            edt25=(TextView) findViewById(R.id.textView54);
            edt26=(TextView) findViewById(R.id.textView56);
            edt27=(TextView) findViewById(R.id.textView58);
            edt28=(TextView) findViewById(R.id.textView60);
            edt29=(TextView) findViewById(R.id.textView62);
            edt30=(TextView) findViewById(R.id.textView64);
            edt31=(TextView) findViewById(R.id.textView66);
            edt32=(TextView) findViewById(R.id.textView68);
            edt33=(TextView) findViewById(R.id.textView70);


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

            edt23.setText(str1);
            edt24.setText(str2);
            edt25.setText(str3);
            edt26.setText(str4);
            edt27.setText(str5);
            edt28.setText(str6);
            edt29.setText(str7);
            edt30.setText(str8);
            edt31.setText(str9);
            edt32.setText(str10);
            edt33.setText(str11);


            Toast.makeText(viewdetail2.this,s,Toast.LENGTH_LONG).show();



        }

    }



}
