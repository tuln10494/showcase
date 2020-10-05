package com.igorwojda.showcase.feature.control

import com.igorwojda.showcase.app.feature.KodeinModuleProvider
import com.igorwojda.showcase.feature.control.data.dataModule
import com.igorwojda.showcase.feature.control.domain.domainModule
import com.igorwojda.showcase.feature.control.presentation.presentationModule
import org.kodein.di.Kodein

internal const val MODULE_NAME = "Control"

object FeatureKodeinModule : KodeinModuleProvider {

    override val kodeinModule = Kodein.Module("${MODULE_NAME}Module") {
        import(presentationModule)
        import(domainModule)
        import(dataModule)
    }
}
