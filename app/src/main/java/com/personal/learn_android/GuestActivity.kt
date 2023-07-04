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

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import com.personal.learn_android.adapter.GuestAdapter
import com.personal.learn_android.databinding.ActivityGuestBinding
import com.personal.learn_android.http.HttpService
import com.personal.learn_android.model.Guest
import io.realm.Realm
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GuestActivity : AppCompatActivity() {
    private lateinit var service: HttpService
    private lateinit var guestAdapter: GuestAdapter
    private lateinit var realm: Realm
    private lateinit var binding: ActivityGuestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val ourActionBar = supportActionBar
        ourActionBar?.setDisplayHomeAsUpEnabled(true)
        realm = Realm.getDefaultInstance()
        binding.swipeRefreshGuest.setOnRefreshListener { data }
        binding.gridview.setOnItemClickListener{ _,_,position,_ ->
            val guest = guestAdapter.getItem(position)
            val message = guest.name
            Toast.makeText(applicationContext, dateTest(guest.birthDate), Toast.LENGTH_SHORT).show()
            val intent = Intent(this@GuestActivity, HomeActivity::class.java)
            intent.putExtra(EXTRA_MESSAGE, message)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
        setupService()
        data
    }

    private fun setupService() {
        val gson = GsonBuilder().setLenient().create()
        val retrofit: Retrofit = Retrofit.Builder().baseUrl(HttpService.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build()
        service = retrofit.create(HttpService::class.java)
    }

    private fun dateTest(date: String?): String {
        val result = date!!.split("-".toRegex()).toTypedArray()[2]
        val day = Integer.valueOf(result)
        return if (day % 2 == 0 && day % 3 == 0) {
            "iOS"
        } else if (day % 2 == 0) {
            "blackberry"
        } else if (day % 3 == 0) {
            "android"
        } else {
            "feature phone"
        }
    }

    private fun isPrime(date: String): String {
        val result = date.split("-".toRegex()).toTypedArray()[1]
        val day = result.toInt()
        if (day < 2) return "not prime"
        if (day == 2) return "prime"
        if (day % 2 == 0) return "not prime"
        var i = 3
        while (i * i <= day) {
            if (day % i == 0) return "not prime"
            i += 2
        }
        return "prime"
    }// Using already downloaded List// handle error

    // Update Realm Guest Object
    private val data: Unit
        get() {
            service.listGuests().enqueue(object : Callback<List<Guest>> {
                override fun onResponse(call: Call<List<Guest>>, response: Response<List<Guest>>) {
                    if (response.isSuccessful) {
                        val guestList = response.body()!!
                        // Update Realm Guest Object
                        realm.beginTransaction()
                        realm.copyToRealmOrUpdate(guestList)
                        realm.commitTransaction()
                        guestAdapter = GuestAdapter(guestList)
                        binding.gridview.adapter = guestAdapter
                    }
                    binding.swipeRefreshGuest.isRefreshing = false
                }

                override fun onFailure(call: Call<List<Guest>>, t: Throwable) {
                    t.printStackTrace()
                    // Using already downloaded List
                    val guestList: List<Guest> = realm.where(Guest::class.java).findAll()
                    if (guestList.isNotEmpty()) {
                        guestAdapter = GuestAdapter(guestList)
                        binding.gridview.adapter = guestAdapter
                    }
                    binding.swipeRefreshGuest.isRefreshing = false
                }
            })
        }


    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@GuestActivity, HomeActivity::class.java)
        val message: String? = null
        intent.putExtra(EXTRA_MESSAGE, message)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == android.R.id.home) {
            val message: String? = null
            val intent = Intent(this@GuestActivity, HomeActivity::class.java)
            intent.putExtra(EXTRA_MESSAGE, message)
            setResult(Activity.RESULT_OK, intent)
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val EXTRA_MESSAGE = "com.personal.learn_android.GUEST_MESSAGE"
    }
}
