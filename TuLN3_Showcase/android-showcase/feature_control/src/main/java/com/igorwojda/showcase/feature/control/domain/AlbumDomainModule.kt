package com.igorwojda.showcase.feature.control.domain

import com.igorwojda.showcase.feature.control.MODULE_NAME
import com.igorwojda.showcase.feature.control.domain.usecase.GetAlbumListUseCase
import com.igorwojda.showcase.feature.control.domain.usecase.GetAlbumUseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

internal val domainModule = Kodein.Module("${MODULE_NAME}DomainModule") {

    bind() from singleton { GetAlbumListUseCase(instance()) }

    bind() from singleton { GetAlbumUseCase(instance()) }
}

