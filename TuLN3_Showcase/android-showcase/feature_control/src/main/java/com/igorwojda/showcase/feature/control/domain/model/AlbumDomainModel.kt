package com.igorwojda.showcase.feature.control.domain.model

import com.igorwojda.showcase.feature.control.domain.enum.AlbumDomainImageSize

internal data class AlbumDomainModel(
    val name: String,
    val artist: String,
    val images: List<AlbumImageDomainModel>,
    val wiki: AlbumWikiDomainModel? = null,
    val mbId: String? = null
) {
    fun getDefaultImageUrl() = images.firstOrNull { it.size == AlbumDomainImageSize.EXTRA_LARGE }?.url
}
