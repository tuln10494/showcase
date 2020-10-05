package com.igorwojda.showcase.feature.control.data.model

import com.igorwojda.showcase.feature.control.data.DataFixtures
import com.igorwojda.showcase.feature.control.data.enum.AlbumDataImageSize
import com.igorwojda.showcase.feature.control.domain.enum.AlbumDomainImageSize
import com.igorwojda.showcase.feature.control.domain.model.AlbumDomainModel
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class AlbumDataModelTest {

    @Test
    fun `data model with full data maps to AlbumDomainModel`() {
        // given
        val cut = DataFixtures.getAlbum()

        // when
        val domainModel = cut.toDomainModel()

        // then
        domainModel shouldBeEqualTo AlbumDomainModel(
            cut.name,
            cut.artist,
            cut.images?.map { it.toDomainModel() } ?: listOf(),
            cut.wiki!!.toDomainModel(),
            cut.mbId!!
        )
    }

    @Test
    fun `data model with missing data maps to AlbumDomainModel`() {
        // given
        val cut = DataFixtures.getMinimalAlbum()

        // when
        val domainModel = cut.toDomainModel()

        // then
        domainModel shouldBeEqualTo AlbumDomainModel(
            name = "name", artist = "artist", images = emptyList(), wiki = null, mbId = null
        )
    }

    @Test
    fun `mapping filters out unknown size`() {
        // given
        val albumDataImages = listOf(AlbumDataImageSize.EXTRA_LARGE, AlbumDataImageSize.UNKNOWN)
            .map { DataFixtures.getAlbumImage(size = it) }
        val cut = DataFixtures.getAlbum(images = albumDataImages)

        // when
        val domainModel = cut.toDomainModel()

        // then
        domainModel.images.single { it.size == AlbumDomainImageSize.EXTRA_LARGE }
    }

    @Test
    fun `mapping filters out blank url`() {
        // given
        val albumDataImages = listOf("", "url")
            .map { DataFixtures.getAlbumImage(url = it) }

        val cut = DataFixtures.getAlbum(images = albumDataImages)

        // when
        val domainModel = cut.toDomainModel()

        // then
        domainModel.images.single { it.url == "url" }
    }
}
