package me.melkopisi.payskytask.art_core.extensions

import android.app.Activity
import android.content.Context
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import me.melkopisi.payskytask.core.Consumer

fun EditText.hideKeyboard() {
    this.clearFocus()
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    imm?.hideSoftInputFromWindow(rootView?.windowToken, 0)
}

fun EditText.showKeyboard() {
    this.requestFocus()
    val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.HIDE_IMPLICIT_ONLY)
}

fun EditText.onDone(callback: Consumer<EditText>) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            callback(this@onDone)
            return@setOnEditorActionListener true
        }
        return@setOnEditorActionListener false
    }
}