package me.melkopisi.payskytask.art_core.extensions

import android.content.Context
import android.content.DialogInterface
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import me.melkopisi.payskytask.core.Consumer

fun Context.setupDialog(view: ViewGroup?, setupViews: Consumer<DialogInterface>): AlertDialog {
    val alertDialogBuilder = AlertDialog.Builder(this)
        .setView(view)

    val alertDialog = alertDialogBuilder.create().apply {
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        setupViews.invoke(this)
    }

    return alertDialog
}