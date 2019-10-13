package com.example.retrofitexample;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Call<List<Post>> getPosts(@QueryMap Map<String,String> parameters);

    @GET("posts")
    Call<List<Post>> getPosts(@Query("userId") Integer[] userIds,
                              @Query("_sort") String sort,
                              @Query("_order") String order);

    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") Integer postId);

    @GET
    Call<List<Comment>> getComments(@Url String url);

    @POST("posts")
    Call<Post> createPost(@Body Post post);

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(@Field("userId") Integer userId,
                          @Field("title") String title,
                          @Field("body") String text);

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(@FieldMap Map<String,String> fields);

    @PUT("posts/{id}")
    Call<Post> updatePutPost(@Path("id") Integer id, @Body Post post);  //put will update entire body

    @PATCH("posts/{id}")
    Call<Post> updatePatchPost(@Path("id") Integer id, @Body Post post); //patch will update only the modified fields, remaining fields will retain the existing values

    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") Integer id);
}
