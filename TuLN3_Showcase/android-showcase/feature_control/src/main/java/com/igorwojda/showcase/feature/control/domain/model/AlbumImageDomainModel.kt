package com.igorwojda.showcase.feature.control.domain.model

import com.igorwojda.showcase.feature.control.domain.enum.AlbumDomainImageSize

internal data class AlbumImageDomainModel(
    val url: String,
    val size: AlbumDomainImageSize
)
