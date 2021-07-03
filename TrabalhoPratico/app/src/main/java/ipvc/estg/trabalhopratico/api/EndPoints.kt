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
    fun getNotasById(@Path("id") id: Int): Call<Notas>

    @FormUrlEncoded
    @POST("/myslim/api/users")
    fun login(@Field("username") username: String?, @Field("password") password: String?): Call<OutputLogin>

    //NMOTA QUE A CLASS DO CALL PODE ESTAR ERRADA
    @FormUrlEncoded
    @POST("/smartcity/api/problema_post")
    fun reportar(@Field("latitude") latitude: String?,
                 @Field("longitude") longitude: String?,
                 @Field("tipo") tipo: String?,
                 @Field("descricao") descricao: String?,
                 @Field("imagem") imagem: String?,
                 @Field("utilizador_id") utilizador_id: Int?): Call<OutputPost>


//NMOTA QUE A CLASS DO CALL PODE ESTAR ERRADA
    @FormUrlEncoded
    @POST("/smartcity/api/problema_put/{id}")
    fun editar(@Path("id") id: String?,
               @Field("latitude") latitude: String?,
               @Field("longitude") longitude: String?,
               @Field("tipo") tipo: String?,
               @Field("descricao") descricao: String?,
               @Field("imagem") imagem: String?,
               @Field("utilizador_id") utilizador_id: Int?): Call<OutputPost>


    @POST("/smartcity/api/problema_delete/{id}")
    fun apagar(@Path("id") id: String?): Call<OutputPost>
/*
    @FormUrlEncoded
    @POST("post")
    fun postTest(@Field("title") first: String?): Call<OutputPost>


     @FormUrlEncoded
    @POST("/login")
    fun login(@Field("username") username: String,@Field("password") password: String): Call<OutputPost>

*/


}