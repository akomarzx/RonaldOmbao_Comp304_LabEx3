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

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.android.test_tracker.database.dao.NurseDao
import com.example.android.test_tracker.database.entity.Nurse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class NurseViewModel(private val nurseDao: NurseDao) : ViewModel() {

    var firstName = ""
    var lastName = ""
    var department = ""
    var password = ""

    private val _registrationResult = MutableSharedFlow<Boolean>()
    val registrationResult = _registrationResult.asSharedFlow()

    fun registerNurse() {
        viewModelScope.launch {
            try {
                val nurse = Nurse(
                    id = null,
                    firstName = firstName,
                    lastName = lastName,
                    department = department,
                    password = password // In a real app, hash the password securely
                )

                nurseDao.insert(nurse)
                _registrationResult.emit(true)

            } catch (e: Exception) {
                Log.i("Hello", e.toString())
                _registrationResult.emit(false)
            }
        }
    }

    fun getAllNurse(): Flow<List<Nurse>> = nurseDao.getAllNurses()
    fun getNurseById(nurseId : Int): Flow<Nurse?> = nurseDao.getNurseById(nurseId)

}

class NurseViewModelFactory (
    private val nurseDao: NurseDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NurseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NurseViewModel(nurseDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
