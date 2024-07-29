package com.example.android.test_tracker.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.android.test_tracker.database.entity.Patient
import kotlinx.coroutines.flow.Flow

@Dao
interface PatientDao {
    @Insert
    suspend fun insert(patient: Patient)

    @Update
    suspend fun update(patient: Patient)

    @Delete
    suspend fun delete(patient: Patient)

    @Query("SELECT * FROM Patient")
    fun getAllPatients(): Flow<List<Patient>>

    @Query("SELECT * FROM Patient WHERE patientId = :patientId")
    fun getPatientById(patientId: Int): Flow<Patient>
}