package it.bebra.cinema.app.common.ui

import android.app.Activity
import android.graphics.Color
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding

fun Activity.edgeToEdge(handling: () -> Unit) {
    WindowCompat.setDecorFitsSystemWindows(window, false)

    window.apply {
        statusBarColor = Color.TRANSPARENT
        navigationBarColor = Color.TRANSPARENT
    }

    handling()
}


fun View.padding(insetType: Int) {
    val oldPaddingLeft = paddingLeft
    val oldPaddingTop = paddingTop
    val oldPaddingRight = paddingRight
    val oldPaddingBottom = paddingBottom

    ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
        val inset = insets.getInsets(insetType)

        view.updatePadding(
            left = oldPaddingLeft + inset.left,
            top = oldPaddingTop + inset.top,
            right = oldPaddingRight + inset.right,
            bottom = oldPaddingBottom + inset.bottom
        )

        insets
    }
}

infix fun View.marginTo(insetType: Int) {
    val oldMarginLeft = marginLeft
    val oldMarginTop = marginTop
    val oldMarginRight = marginRight
    val oldMarginBottom = marginBottom

    ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
        val inset = insets.getInsets(insetType)

        view.updateLayoutParams {
        left = oldMarginLeft + inset.left
        top = oldMarginTop + inset.top
        right = oldMarginRight + inset.right
        bottom = oldMarginBottom + inset.bottom
        }

        insets
    }
}
