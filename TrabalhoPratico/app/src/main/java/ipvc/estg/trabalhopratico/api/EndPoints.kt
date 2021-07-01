package ipvc.estg.trabalhopratico.api

import android.icu.util.Output
import retrofit2.Call
import retrofit2.http.*


interface EndPoints {
    @GET("/myslim/api/users/")
    fun getUsers(): Call<List<User>>
    @GET("/myslim/api/users/{id}")
    fun getUserById(@Path("id") id: Int): Call<User>


/*
    @FormUrlEncoded
    @POST("post")
    fun postTest(@Field("title") first: String?): Call<OutputPost>


     @FormUrlEncoded
    @POST("/login")
    fun login(@Field("username") username: String,@Field("password") password: String): Call<OutputPost>

*/


}