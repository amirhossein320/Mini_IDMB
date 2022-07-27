package ir.interview.idmb.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
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

inline fun View.hideKeyboard(){
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}