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

import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.personal.learn_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.button.setOnClickListener {
            sendName()
        }
    }

    fun sendName() {
        val intent = Intent(this, HomeActivity::class.java)
        val message = binding.editText.text.toString()
        if (!message.equals("", ignoreCase = true)) {
            intent.putExtra(EXTRA_MESSAGE, message)
            startActivity(intent)
        }
    }

    companion object {
        const val EXTRA_MESSAGE = "com.personal.learn_android.MESSAGE"
    }
}