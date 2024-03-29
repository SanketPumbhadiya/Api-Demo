package com.example.api_demo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.api_demo.RetrofitModel.Data;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    ListView listView;
    CustomAdapter customAdapter;
    ArrayList<Data> responseModel;
    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        responseModel = new ArrayList<>();
        inflater = getLayoutInflater();
        String myUrl = "https://reqres.in/api/users?page=2";

        HttpRequest httpRequest = new HttpRequest(this);
        httpRequest.execute(myUrl);

    }

    public void onResponse(String response) {
        try {

            Log.d("onResponse : ", response);
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                int id = object.getInt("id");
                String firstName = object.getString("first_name");
                String lastName = object.getString("last_name");
                String email = object.getString("email");
                String image = object.getString("avatar");
                Data modelData = new Data(id, email, firstName, lastName, image);
                responseModel.add(modelData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        customAdapter = new CustomAdapter(this, responseModel);
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                view = inflater.inflate(R.layout.activity_alertdialog, null);
                builder.create();
                final ImageView ivAvatar = view.findViewById(R.id.avatar);
                final TextView tvId = view.findViewById(R.id.tvId);
                final TextView tvFirstName = view.findViewById(R.id.tvfName);
                final TextView tvLastName = view.findViewById(R.id.tvlName);
                final TextView tvEmail = view.findViewById(R.id.tvEmail);
                builder.setView(view);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();

                RetrofitModel.Data getData = responseModel.get(position);

                tvId.setText(String.valueOf(getData.getId()));
                tvFirstName.setText(getData.getFirst_name());
                tvLastName.setText(getData.getLast_name());
                tvEmail.setText(getData.getEmail());

                String image = getData.getAvatar();
                Glide.with(getApplicationContext())
                        .load(image)
                        .into(ivAvatar);
            }
        });
    }
}