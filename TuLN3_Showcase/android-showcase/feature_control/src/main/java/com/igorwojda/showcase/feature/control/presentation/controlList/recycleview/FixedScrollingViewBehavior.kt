package com.igorwojda.showcase.feature.control.presentation.controlList.recycleview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.google.android.material.appbar.AppBarLayout

internal class FixedScrollingViewBehavior(context: Context, attrs: AttributeSet) :
    AppBarLayout.ScrollingViewBehavior(context, attrs) {
    override fun onMeasureChild(
        parent: CoordinatorLayout, child: View,
        parentWidthMeasureSpec: Int, widthUsed: Int, parentHeightMeasureSpec: Int, heightUsed: Int
    ): Boolean {

        if (child.layoutParams.height == -1) {
            val dependencies = parent.getDependencies(child)
            if (dependencies.isEmpty()) {
                return false
            }
            val appBarLayout: AppBarLayout? = findFirstAppBarLayout(dependencies)
            if (appBarLayout != null && ViewCompat.isLaidOut(appBarLayout)) {
                if (ViewCompat.getFitsSystemWindows(appBarLayout)) {
                    ViewCompat.setFitsSystemWindows(child, true)
                }
                var scrollRange = appBarLayout.totalScrollRange
                var height = parent.getHeight() - appBarLayout.getMeasuredHeight() + Math.min(
                    scrollRange,
                    parent.getHeight() - heightUsed
                )
                var heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY)
                parent.onMeasureChild(child, parentWidthMeasureSpec, widthUsed, heightMeasureSpec, heightUsed);
                return true
            }
        }
        return false
    }

    fun findFirstAppBarLayout(view: List<View>): AppBarLayout? {
        for (items in view) {
            var view = items
            if (view is AppBarLayout) {
                return view as AppBarLayout
            }
        }
        return null
    }
}
