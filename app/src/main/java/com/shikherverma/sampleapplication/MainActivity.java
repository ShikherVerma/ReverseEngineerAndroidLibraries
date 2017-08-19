package com.shikherverma.sampleapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RetrofitApiInterface retrofitApiInterface = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .build()
                .create(RetrofitApiInterface.class);
        final TextView textView = (TextView) findViewById(R.id.text);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("Loading repos...");
                Call<ResponseBody> call = retrofitApiInterface.loadRepos("ShikherVerma");
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        String ResponseBody = null;
                        try {
                            ResponseBody = response.body().string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        textView.setText(ResponseBody);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        textView.setText("Request Failed");
                    }
                });
            }
        });
    }
}
