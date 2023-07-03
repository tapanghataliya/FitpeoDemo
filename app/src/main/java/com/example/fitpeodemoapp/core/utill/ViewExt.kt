package com.example.fitpeodemoapp.core.utill
import android.view.View
import androidx.core.content.ContextCompat
import com.example.fitpeodemoapp.R
import com.google.android.material.snackbar.Snackbar

class ViewExt {

    companion object{

        //This is snackbar to display message in screen on bottom
        fun View.showSnackBar(message: String?) {
            Snackbar.make(this, message ?: "", Snackbar.LENGTH_SHORT).show()
            val snackBar = Snackbar.make(this, message.toString(), Snackbar.LENGTH_LONG)
            if (message != Constant.NO_RECORD) {
                snackBar.view.setBackgroundColor(ContextCompat.getColor(context, R.color.red))
            } else {
                snackBar.view.setBackgroundColor(ContextCompat.getColor(context, R.color.green))
            }
            snackBar.show()
        }
    }

}