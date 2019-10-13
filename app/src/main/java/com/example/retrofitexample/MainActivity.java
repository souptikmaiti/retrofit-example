package com.example.retrofitexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView tvShow;
    JsonPlaceHolderApi jsonPlaceHolderApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvShow = findViewById(R.id.tvShow);
        tvShow.setText("");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        getPosts();
        //getComments();
        //createPost();
        //updatePost();
        //deletePost();
    }

    private void getPosts(){

        Call<List<Post>> call = jsonPlaceHolderApi.getPosts(new Integer[]{1,3}, "id", "desc");
        //call.execute() will run in the main/ui thread, which will freeze our ui
        //instead retrofit will run the network operation in a background thread by enqueue() method
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {  //this run in ui/main thread
                if(!response.isSuccessful()) return;

                List<Post> listPosts = response.body();
                for(Post post: listPosts){
                    String contentPost = "";
                    contentPost += "UserId: "+ post.getUserId().toString() + "\n";
                    contentPost += "Id: "+ post.getId().toString() + "\n";
                    contentPost += "Title: "+ post.getTitle() + "\n";
                    contentPost += "Text: "+ post.getText() + "\n\n";
                    tvShow.append(contentPost);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                tvShow.setText(t.getMessage());
            }
        });
    }

    private void getComments(){

        Call<List<Comment>> call = jsonPlaceHolderApi.getComments(3);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if(!response.isSuccessful()) return;

                List<Comment> listComments = response.body();
                for(Comment comment : listComments){
                    String contentComment = "";
                    contentComment += "PostId: "+ comment.getPostId().toString() + "\n";
                    contentComment += "Id: "+ comment.getId().toString() + "\n";
                    contentComment += "Name: "+ comment.getName() + "\n";
                    contentComment += "Email: "+ comment.getEmail() + "\n";
                    contentComment += "Body: "+ comment.getText() + "\n\n";
                    tvShow.append(contentComment);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                tvShow.setText(t.getMessage());
            }
        });
    }

    private void createPost(){
        //Post post = new Post(45,"New Title","New Body");
        //Call<Post> call = jsonPlaceHolderApi.createPost(post);
        //Call<Post> call = jsonPlaceHolderApi.createPost(87,"Another Title","Another Body of Text");
        Map<String,String> fields = new HashMap<String, String>();
        fields.put("userId", "54");
        fields.put("title","Again Title");
        Call<Post> call = jsonPlaceHolderApi.createPost(fields);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()) return;
                Post responsePost = response.body();
                String contentResponded = "";
                contentResponded += "Response Code: " + response.code() +"\n";
                contentResponded += "Response Id: " + responsePost.getId() +"\n";  //Server generated
                contentResponded += "Response UserId: " + responsePost.getUserId() +"\n";
                contentResponded += "Response Title: " + responsePost.getTitle() +"\n";
                contentResponded += "Response Text: " + responsePost.getText() +"\n";

                tvShow.append(contentResponded);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }

    private void updatePost(){
        Post post = new Post(4,null,"Updated Text"); //in case of PUT title field will be updated to null
                                                                      //in case of PATCH title field will remain as it was
        //Call<Post> call = jsonPlaceHolderApi.updatePutPost(2,post);
        Call<Post> call = jsonPlaceHolderApi.updatePatchPost(2,post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()) return;
                Post responsePost = response.body();
                String contentResponded = "";
                contentResponded += "Response Code: " + response.code() +"\n";
                contentResponded += "Response Id: " + responsePost.getId() +"\n";  //Server generated
                contentResponded += "Response UserId: " + responsePost.getUserId() +"\n";
                contentResponded += "Response Title: " + responsePost.getTitle() +"\n";
                contentResponded += "Response Text: " + responsePost.getText() +"\n";

                tvShow.append(contentResponded);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                tvShow.setText(t.getMessage());
            }
        });
    }

    private void deletePost(){
        Call<Void> call = jsonPlaceHolderApi.deletePost(5);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful()) return;
                tvShow.setText("Response Code: " + response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                tvShow.setText(t.getMessage());
            }
        });
    }
}
