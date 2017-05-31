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

package com.personal.learn_android.model;

import com.personal.learn_android.R;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Guest extends RealmObject {
    @PrimaryKey
    private int id;

    private Integer image;
    private String name;
    private String birthdate;

    // Default Guest Input
    public Guest() {
        this.id = -1;
        this.image = R.drawable.face;
        this.name = "Android";
        this.birthdate = "01 Januari 1997";
    }

    public Guest(int id, Integer image, String name, String birthdate) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.birthdate = birthdate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public Integer getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
}
