package com.igorwojda.showcase.feature.control.presentation

import androidx.fragment.app.Fragment
import com.igorwojda.showcase.feature.control.MODULE_NAME
import com.igorwojda.showcase.feature.control.presentation.controlList.ControlListViewModel
import com.igorwojda.showcase.feature.control.presentation.controlList.recycleview.ControlAdapterSmallItem
import com.igorwojda.showcase.feature.control.presentation.controlList.recycleview.ControlAdapterFullHorizontal
import com.igorwojda.showcase.library.base.di.KotlinViewModelProvider
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

internal val presentationModule = Kodein.Module("${MODULE_NAME}PresentationModule") {

    bind<ControlListViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
        KotlinViewModelProvider.of(context) { ControlListViewModel(instance(), instance()) }
    }

    bind() from singleton { ControlAdapterSmallItem() }

    bind() from singleton { ControlAdapterFullHorizontal() }
}
