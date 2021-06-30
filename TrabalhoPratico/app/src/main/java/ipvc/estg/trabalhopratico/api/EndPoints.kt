package ipvc.estg.trabalhopratico.api

import android.icu.util.Output
import retrofit2.Call
import retrofit2.http.*


interface EndPoints {
    @GET("/users/")
    fun getUsers(): Call<List<User>>
    @GET("/users/{id}")
    fun getUserById(@Path("id") id: Int): Call<List<User>>
/*
    @FormUrlEncoded
    @POST("post")
    fun postTest(@Field("title") first: String?): Call<OutputPost>
*/


}