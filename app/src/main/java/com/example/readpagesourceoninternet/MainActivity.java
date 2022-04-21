package com.example.readpagesourceoninternet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    Button btnGetPage;
    TextView txtViewPageSource,txtHttp;
    EditText edtLink;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGetPage = (Button) findViewById(R.id.btnGetPageSource);
        txtViewPageSource = (TextView) findViewById(R.id.textViewPageSource);
        txtHttp = (TextView) findViewById(R.id.txtHttp);
        edtLink = (EditText) findViewById(R.id.edtLinkSite);
        url = "http://";


        txtHttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtHttp.getText().toString().equals("http://")){
                    txtHttp.setText("https://");
                     url = "https://";
                }else{
                    txtHttp.setText("http://");
                     url = "http://";
                }
            }
        });


        btnGetPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String urlZ = url +    edtLink.getText().toString();
                Toast.makeText(MainActivity.this, urlZ, Toast.LENGTH_SHORT).show();
                new readContentWebsite().execute(urlZ);
            }
        });

    }

    private class readContentWebsite extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder stringBuilder  = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                URLConnection urlConnection = url.openConnection();

                InputStream inputStream = urlConnection.getInputStream();

               InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

               BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

               String line = "";

               while((line = bufferedReader.readLine())!= null){
                   stringBuilder.append(line + "\n");
               }
               bufferedReader.close();




            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            txtViewPageSource.setText(s);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }
}