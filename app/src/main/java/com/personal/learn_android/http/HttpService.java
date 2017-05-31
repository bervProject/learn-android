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

package com.personal.learn_android.http;

import com.personal.learn_android.model.Guest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Bervianto Leo P on 30/05/2017.
 */

public interface HttpService {

    String BASE_URL = "http://dry-sierra-6832.herokuapp.com/";

    @GET("api/people")
    Call<List<Guest>> listGuests();
}
