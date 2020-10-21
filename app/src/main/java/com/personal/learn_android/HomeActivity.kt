/*
 * Copyright (c) 2017 Bervianto Leo Pratama
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.personal.learn_android

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*

class HomeActivity : AppCompatActivity() {
    var name: String? = null
    private var guestText: String? = null
    private var eventText: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        button_guest.setOnClickListener { _ ->
            val intent = Intent(this, GuestActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_GUEST)
        }
        button_event.setOnClickListener { _ ->
            val intent = Intent(this, EventActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_EVENT)
        }
        val intent = intent
        val message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE)
        if (message != null) {
            name = message
            name_view.text = name
            // Show Dialog isPalindrome
            val dialog = AlertDialog.Builder(this).setTitle(getString(R.string.title_dialog_isPalindrom)).setMessage(isPalindrom(name!!)).create()
            dialog.show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_CODE_GUEST -> {
                val guestName = data!!.getStringExtra(GuestActivity.EXTRA_MESSAGE)
                if (guestName != null) {
                    guestText = guestName
                    button_guest.text = guestText
                } else {
                    if (guestText == null) {
                        button_guest.text = getString(R.string.select_guest_button_text)
                    } else {
                        button_guest.text = guestText
                    }
                }
            }
            REQUEST_CODE_EVENT -> {
                val eventName = data!!.getStringExtra(EventActivity.EXTRA_MESSAGE)
                if (eventName != null) {
                    eventText = eventName
                    button_event.text = eventText
                } else {
                    if (eventText == null) {
                        button_event.text = getString(R.string.select_event_button_text)
                    } else {
                        button_event.text = eventText
                    }
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun isPalindrom(name: String): String {
        var temp = name.replace("\\s+".toRegex(), "")
        temp = temp.toLowerCase(Locale.ROOT)
        val j = temp.length - 1
        var i = 0
        var palindrom = true
        while (palindrom && i <= j) {
            if (temp[i] != temp[j - i]) {
                palindrom = false
            } else {
                i++
            }
        }
        return if (palindrom) {
            getString(R.string.palindrome_message)
        } else {
            getString(R.string.not_palindrome_message)
        }
    }

    companion object {
        const val REQUEST_CODE_EVENT = 1
        const val REQUEST_CODE_GUEST = 2
    }
}