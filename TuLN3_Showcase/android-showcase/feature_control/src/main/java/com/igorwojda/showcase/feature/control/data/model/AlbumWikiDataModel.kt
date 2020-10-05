package com.igorwojda.showcase.feature.control.data.model

import com.igorwojda.showcase.feature.control.domain.model.AlbumWikiDomainModel

internal data class AlbumWikiDataModel(
    val published: String,
    val summary: String
)

internal fun AlbumWikiDataModel.toDomainModel() = AlbumWikiDomainModel(
    published = this.published,
    summary = this.summary
)
