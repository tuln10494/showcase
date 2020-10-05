package com.igorwojda.showcase.feature.control.presentation.controlList

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import com.igorwojda.showcase.feature.control.R
import com.igorwojda.showcase.feature.control.presentation.controlList.recycleview.ControlAdapterSmallItem
import com.igorwojda.showcase.feature.control.presentation.controlList.recycleview.ControlAdapterFullHorizontal
import com.igorwojda.showcase.feature.control.presentation.controlList.recycleview.GridAutofitLayoutManager
import com.igorwojda.showcase.library.base.presentation.extension.observe
import com.igorwojda.showcase.library.base.presentation.fragment.InjectionFragment
import kotlinx.android.synthetic.main.fragment_control.*
import org.kodein.di.generic.instance


class ControlListFragment : InjectionFragment(R.layout.fragment_control) {

    private val viewModel: ControlListViewModel by instance()
    private val albumAdapterSmallItem: ControlAdapterSmallItem by instance()
    private val albumAdapterFullHorizontal: ControlAdapterFullHorizontal by instance()

    private val stateObserver = Observer<ControlListViewModel.ViewState> {
        albumAdapterSmallItem.albums = it.albums
    }
    private val stateObserver2 = Observer<ControlListViewModel.ViewState> {
        albumAdapterFullHorizontal.albums = it.albums.asReversed()
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context = checkNotNull(context)

        rc_one.apply {
            setHasFixedSize(true)
            val columnWidth = context.resources.getDimension(R.dimen._120dp).toInt()
            layoutManager =
                GridAutofitLayoutManager(
                    context,
                    columnWidth, LinearLayout.HORIZONTAL, false
                )
            adapter = albumAdapterSmallItem
        }

        rc_two.apply {
            setHasFixedSize(true)
            val columnWidth = context.resources.displayMetrics.widthPixels
            layoutManager =
                GridAutofitLayoutManager(
                    context,
                    columnWidth, LinearLayout.HORIZONTAL, false
                )
            adapter = albumAdapterFullHorizontal.apply {
                albums.asReversed()
            }
        }

        rc_three.apply {
            setHasFixedSize(true)
            val columnWidth = context.resources.displayMetrics.widthPixels
            layoutManager =
                GridAutofitLayoutManager(
                    context,
                    columnWidth, LinearLayout.HORIZONTAL, false
                )
            adapter = albumAdapterFullHorizontal
        }

        observe(viewModel.stateLiveData, stateObserver)
        observe(viewModel.stateLiveData, stateObserver2)
        viewModel.loadData()
    }
}
