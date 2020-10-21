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
package com.personal.learn_android.model

import com.personal.learn_android.R
import io.realm.RealmObject

open class Event : RealmObject {
    var eventName: String
    var tanggalEvent: String
    var image: Int
    var isSelected: Boolean
    var latitude: Double
    var longitude: Double

    // Default Event
    constructor() {
        eventName = "Event"
        tanggalEvent = "01 Januari 2017"
        image = R.drawable.events
        isSelected = false
        latitude = 0.0
        longitude = 0.0
    }

    constructor(event_name: String, tanggal_event: String, image: Int, latitude: Double, longitude: Double) {
        eventName = event_name
        tanggalEvent = tanggal_event
        this.image = image
        isSelected = false
        this.latitude = latitude
        this.longitude = longitude
    }

}