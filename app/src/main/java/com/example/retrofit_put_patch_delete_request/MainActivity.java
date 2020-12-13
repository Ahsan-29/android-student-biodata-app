package com.example.retrofit_put_patch_delete_request;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText et1,et2,et3,et4;
    Button b1,b2,b3,b4,b5;
    private TextView textViewResult;

    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textViewResult = findViewById(R.id.text_view_result);
        et1 = (EditText)findViewById(R.id.e1);
        et2 = (EditText)findViewById(R.id.e2);
        et3 = (EditText)findViewById(R.id.e3);
        et4 = (EditText)findViewById(R.id.e4);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.30:3306/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);


        b1 = (Button)findViewById(R.id.create);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPost();

            }
        });

        b2 = (Button)findViewById(R.id.read);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getposts();

            }
        });

        b3 = (Button)findViewById(R.id.update);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatepost();

            }
        });

        b4 = (Button)findViewById(R.id.delete);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletepost();

            }
        });

        b5 = (Button)findViewById(R.id.clear);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearpost();

            }
        });
    }

    private void updatepost()
    {
        Post post = new Post(1 , "ahsan1212", "ahsan12312", "ahsan45612", "ahsan78912");
        Call<Post> call = jsonPlaceHolderApi.putPost(post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code() + "\nData has not been update successfully" );
                    return;
                }

                else
                {
                    textViewResult.setText("Code: " + response.code() + "\nData has been update successfully" );
                }

                Post postResponse = response.body();
                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "ID: " + postResponse.getId() + "\n";
                content += "User ID: " + postResponse.getEmail() + "\n";
                content += "Title: " + postResponse.getPassword() + "\n";
                content += "Text: " + postResponse.getName() + "\n\n";
                content += "Text: " + postResponse.getSchool() + "\n\n";
                textViewResult.setText(content);
            }
            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void deletepost()
    {
        Call<Void> call = jsonPlaceHolderApi.deletePost(1);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response)
            {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code() + "\nData has not been delete successfully" );
                    return;
                }

                else
                {
                    textViewResult.setText("Code: " + response.code() + "\nData has been delete successfully" );
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void getposts()
    {

        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code() + "\nData has not been read successfully" );
                    return;
                }

                else
                {
                    textViewResult.setText("Code: " + response.code() + "\nData has been read successfully" );
                }


                List<Post> posts = response.body();
                for (Post post : posts) {
                    String content = "";
                    content += "\nID: " + post.getId();
                    content += "\nemail: " + post.getEmail();
                    content += "\npassword: " + post.getPassword();
                    content += "\nname: " + post.getName();
                    content += "\nschool: " + post.getSchool() + "\n";
                    textViewResult.append(content);
                }
            }
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void createPost()
    {

        Map<String, String> fields = new HashMap<>();
        fields.put("email", "ahsan.mukhtar94@hotmail.com");
        fields.put("password", "ahsan123");
        fields.put("name", "Ahsan");
        fields.put("School", "Educators");

        Call<Post> call = jsonPlaceHolderApi.createPost(fields);

        call.enqueue(new Callback<Post>()
        {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response)
            {

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code() + "\nData has not been create successfully" );
                    return;
                }

                else
                {
                    textViewResult.setText("Code: " + response.code() + "\nData has been create successfully" );
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    public void clearpost()
    {
        startActivity(new Intent(MainActivity.this, MainActivity.class));
    }


}