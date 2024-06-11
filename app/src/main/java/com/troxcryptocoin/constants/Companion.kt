package com.troxcryptocoin

import android.animation.ValueAnimator
import android.app.AlertDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


object Companion {
    const val siteUrl = "https://nexifyapp.com/nexifyapp/users/"
    const val PREF_NAME = "Nexify"
    const val APP_VERSION = 2
    const val ONESIGNAL_APP_ID = "ca04a5b3-8e2c-4210-a624-3d190beaab52"
    const val APP_NATIVE = "dc2ee83fd8fbf6d5"
    const val APP_INTER = "4df52a009bb2ff70"
    const val APP_BANNER = "e9d95a211f9bebcb"

}

object Utils {
    private var alertDialog: AlertDialog? = null
    fun openUrl(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }

    fun toSpeakableString(number: Int): String {
        val units =
            arrayOf("", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine")
        val teens = arrayOf(
            "Ten",
            "Eleven",
            "Twelve",
            "Thirteen",
            "Fourteen",
            "Fifteen",
            "Sixteen",
            "Seventeen",
            "Eighteen",
            "Nineteen"
        )
        val tens = arrayOf(
            "",
            "",
            "Twenty",
            "Thirty",
            "Forty",
            "Fifty",
            "Sixty",
            "Seventy",
            "Eighty",
            "Ninety"
        )

        if (number == 0) {
            return "Zero"
        }

        val sb = StringBuilder()

        if (number >= 1000) {
            sb.append(units[number / 1000]).append(" Thousand ")
        }

        if (number / 100 % 10 != 0) {
            sb.append(units[number / 100 % 10]).append(" Hundred ")
        }

        if (number % 100 in 10..19) {
            sb.append(teens[number % 10]).append(" ")
        } else {
            if (number % 100 / 10 != 0) {
                sb.append(tens[number % 100 / 10]).append(" ")
            }
            if (number % 10 != 0) {
                sb.append(units[number % 10]).append(" ")
            }
        }

        return sb.toString().trim()
    }

    fun hideKeyboard(context: Context, editText: EditText) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText.windowToken, 0)
    }

    fun copyToClipboard(context: Context, text: String) {
        val clipboardManager =
            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("Copied Text", text)
        Toast.makeText(context, "Copied !", Toast.LENGTH_SHORT).show()
        clipboardManager.setPrimaryClip(clipData)
    }

    fun getCopiedText(context: Context): String {
        val clipboardManager =
            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        if (clipboardManager.hasPrimaryClip()) {
            val clipData = clipboardManager.primaryClip
            val item = clipData?.getItemAt(0)
            return item?.text?.toString() ?: ""
        }
        return ""
    }

    fun convertDateTime(inputDateTimeString: String, outputPattern: String): String {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val dateTime = LocalDateTime.parse(inputDateTimeString, inputFormatter)

        val outputFormatter = DateTimeFormatter.ofPattern(outputPattern, Locale.ENGLISH)
        return dateTime.format(outputFormatter)
    }

    fun fadeInView(view: View, duration: Long) {
        val fadeInAnimator = ValueAnimator.ofFloat(0f, 1f)
        fadeInAnimator.duration = duration // You can adjust the duration as needed
        fadeInAnimator.addUpdateListener { animation ->
            view.alpha = animation.animatedValue as Float
        }
        fadeInAnimator.start()
        view.visibility = View.VISIBLE
    }

    fun showCustomPopup(
        context: Context,
        layout: Int,
        viewId: Int,
        listener: View.OnClickListener
    ) {
        val customLayout = LayoutInflater.from(context).inflate(layout, null)
        val builder = AlertDialog.Builder(context, R.style.TransparentDialogTheme)
        builder.setView(customLayout)
        val alertDialog = builder.create()
        builder.setCancelable(true)

        customLayout.findViewById<View>(viewId).setOnClickListener {
            listener.onClick(it)
            alertDialog.dismiss()
        }
        alertDialog.show()
    }

    fun showLoadingPopUp(context: Context) {
        val customLayout = LayoutInflater.from(context).inflate(R.layout.popup_loading, null)
        val builder = AlertDialog.Builder(context, R.style.TransparentDialogTheme)
        builder.setView(customLayout)
        alertDialog = builder.create()
        alertDialog?.setCancelable(false)

        alertDialog?.show()
    }

    fun dismissLoadingPopUp() {
        alertDialog?.dismiss()
    }

    fun showSnackBar(context: Context, view: View, text: String, bgColor: Int) {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG)
            .setAction("ok") {
                // Responds to click on the action
            }
            .setBackgroundTint(bgColor)
            .setActionTextColor(context.resources.getColor(R.color.white))
            .show()
    }
}

object TinyDB {
    private const val PREF_NAME = "Nexify"

    // Method to save a string value in SharedPreferences
    fun saveString(context: Context, key: String?, value: String?) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    // Method to retrieve a string value from SharedPreferences
    fun getString(context: Context, key: String?, defaultValue: String?): String? {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, defaultValue)
    }

    // Method to save an integer value in SharedPreferences
    fun saveInt(context: Context, key: String?, value: Int) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    // Method to retrieve an integer value from SharedPreferences
    fun getInt(context: Context, key: String?, defaultValue: Int): Int {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getInt(key, defaultValue)
    }

    fun getList(context: Context, key: String?): MutableList<String> {
        val old = getString(context, key, "")!!
        return old.split(",").toMutableList()

    }

    fun addItemList(context: Context, key: String?, value: String) {
        val old = getString(context, key, "")!!
        val oldArr = old.split(",").toMutableList()
        oldArr.add(value)
        val new = oldArr.joinToString(",")
        saveString(context, key, new)
    }

    // Method to save a boolean value in SharedPreferences
    fun saveBoolean(context: Context, key: String?, value: Boolean) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    // Method to retrieve a boolean value from SharedPreferences
    fun getBoolean(context: Context, key: String?, defaultValue: Boolean): Boolean {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    // Method to clear all SharedPreferences data
    fun clearPreferences(context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}