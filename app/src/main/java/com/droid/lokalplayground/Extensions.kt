package com.droid.lokalplayground

import android.view.View
import android.widget.Toast

fun View.toast(message: String) {
    Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
}