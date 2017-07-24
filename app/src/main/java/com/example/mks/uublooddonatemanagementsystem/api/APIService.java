package com.example.mks.uublooddonatemanagementsystem.api;

import com.example.mks.uublooddonatemanagementsystem.model.MessageResponse;
import com.example.mks.uublooddonatemanagementsystem.model.Result;
import com.example.mks.uublooddonatemanagementsystem.model.Users;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by mks on 7/24/2017.
 */

public interface APIService {
    //register call
    @FormUrlEncoded
    @POST("register")
    Call<Result> createUser(
            @Field("name")String name,
            @Field("email")String email,
            @Field("password")String password,
            @Field("gender")String gender,
            @Field("bloodgroup")String bloodgroup,
            @Field("city")String city,
            @Field("contactno")String contactno
    );
    @FormUrlEncoded
    @POST("login")
    Call<Result> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );
    @GET("users")
    Call<Users> getUsers();

    //sending message
    @FormUrlEncoded
    @POST("sendmessage")
    Call<MessageResponse> sendMessage(
            @Field("from") int from,
            @Field("to") int to,
            @Field("title") String title,
            @Field("message") String message);
    //updating user
    @FormUrlEncoded
    @POST("update/{id}")
    Call<Result> updateUser(
            @Path("id") int id,
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("gender") String gender,
            @Field("bloodgroup") String bloodgroup,
            @Field("city") String city,
            @Field("contactno") String contactno
    );
}
