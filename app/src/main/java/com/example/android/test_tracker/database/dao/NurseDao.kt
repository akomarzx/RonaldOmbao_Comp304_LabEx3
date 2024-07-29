package com.example.android.test_tracker.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.android.test_tracker.database.entity.Nurse
import kotlinx.coroutines.flow.Flow

@Dao
interface NurseDao {

    @Insert
    suspend fun insert(nurse: Nurse)

    @Update
    suspend fun update(nurse: Nurse)

    @Delete
    suspend fun delete(nurse: Nurse)

    @Query("SELECT * FROM Nurse")
    fun getAllNurses(): Flow<List<Nurse>>

    @Query("SELECT * FROM Nurse WHERE id = :nurseId")
    fun getNurseById(nurseId: Int): Flow<Nurse>
}