package ipvc.estg.trabalhopratico.api

import android.icu.util.Output
import retrofit2.Call
import retrofit2.http.*


interface EndPoints {
    @GET("/myslim/api/users")
    fun getUsers(): Call<List<User>>
    @GET("/myslim/api/users/{id}")
    fun getUserById(@Path("id") id: Int): Call<User>


    @GET("/myslim/api/notas")
    fun getNotas(): Call<List<Notas>>

    @GET("/myslim/api/notas/{id}")
    fun getNotasById(@Path("id") id: String): Call<List<Notas>>

    @FormUrlEncoded
    @POST("/myslim/api/users_login")
    fun login(@Field("username") username: String?, @Field("password") password: String?): Call<OutputLogin>


    @FormUrlEncoded
    @POST("/myslim/api/users_register")
    fun registarUser(@Field("username") username: String?, @Field("password") password: String?, @Field("email") email: String?): Call<OutputLogin>



    @FormUrlEncoded
    @POST("/myslim/api/inserirNota")
    fun inserirNota(
               @Field("title") title: String?,
               @Field("description") description: String?,
               @Field("latitude") latitude: Float?,
               @Field("longitude") longitude: Float?,
               @Field("id_utilizador") id_utilizador: Int?): Call<Notas>


    @POST("/smartcity/api/problema_delete/{id}")
    fun apagar(@Path("id") id: String?): Call<Notas>


}