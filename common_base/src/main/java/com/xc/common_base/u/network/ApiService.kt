package com.xc.common_base.u.network

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/repositories?sort=stars")
    suspend fun searchRepos(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): RepoSearchResponse

    @FormUrlEncoded
    @POST("user/login")
    suspend fun Login(
        @Field("username") username: String,
        @Field("password") password: String
    ): BaseResp<String>

    @FormUrlEncoded
    @POST("user/login")
    fun queryProgressByStaffId(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<BaseResp<String>>


}