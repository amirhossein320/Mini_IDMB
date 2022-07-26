package ir.interview.idmb.utils

import android.view.View
import android.widget.Toast


inline fun View.visible() {
    visibility = View.VISIBLE
}

inline fun View.gone() {
    visibility = View.GONE
}

inline fun View.invisible() {
    visibility = View.INVISIBLE
}
