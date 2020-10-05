package com.igorwojda.showcase.feature.control.data.retrofit.service

import com.igorwojda.showcase.feature.control.data.retrofit.reponse.GetAlbumInfoResponse
import com.igorwojda.showcase.feature.control.data.retrofit.reponse.SearchAlbumResponse
import retrofit2.http.POST
import retrofit2.http.Query

internal interface AlbumRetrofitService {

    @POST("./?method=album.search")
    suspend fun searchAlbumAsync(
        @Query("album") phrase: String,
        @Query("limit") limit: Int = 60
    ): SearchAlbumResponse

    @POST("./?method=album.getInfo")
    suspend fun getAlbumInfoAsync(
        @Query("artist") artistName: String,
        @Query("album") albumName: String,
        @Query("mbid") mbId: String?
    ): GetAlbumInfoResponse?
}
