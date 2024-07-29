package com.example.android.test_tracker.viewmodels
/*
 * Copyright (C) 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.test_tracker.database.dao.PatientDao
import com.example.android.test_tracker.database.entity.Patient
import kotlinx.coroutines.flow.Flow

class PatientViewModel(private val patientDao: PatientDao) : ViewModel() {

    fun allPatients(): Flow<List<Patient>> = patientDao.getAllPatients()
}

class PatientViewModelFactory(
    private val patientDao: PatientDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TestViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PatientViewModel(patientDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
