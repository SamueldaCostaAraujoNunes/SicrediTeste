package samuelnunes.com.sicrediteste.presentation.extensions

import android.view.Gravity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar


fun Snackbar.onTop(): Snackbar {
    val params = view.layoutParams as CoordinatorLayout.LayoutParams
    params.gravity = Gravity.TOP
    view.layoutParams = params
    return this
}