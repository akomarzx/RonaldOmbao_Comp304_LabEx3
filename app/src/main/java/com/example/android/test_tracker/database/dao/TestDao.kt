package com.example.android.test_tracker.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.android.test_tracker.database.entity.Test
import kotlinx.coroutines.flow.Flow

@Dao
interface TestDao {
    @Insert
    suspend fun insert(test: Test)

    @Update
    suspend fun update(test: Test)

    @Delete
    suspend fun delete(test: Test)

    @Query("SELECT * FROM Test")
    fun getAllTests(): Flow<List<Test>>

    @Query("SELECT * FROM Test WHERE testId = :testId")
    fun getTestById(testId: Int): Flow<Test>

    @Query("SELECT * FROM Test WHERE patientId = :patientId")
    fun getTestsForPatient(patientId: Int): Flow<List<Test>>
}