package com.xc.common_base.u.network

import com.xc.common_base.u.network.RepoSearchResponse
import retrofit2.http.*

interface ApiService {
    @GET("search/repositories?sort=stars")
    suspend fun searchRepos(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): RepoSearchResponse
}