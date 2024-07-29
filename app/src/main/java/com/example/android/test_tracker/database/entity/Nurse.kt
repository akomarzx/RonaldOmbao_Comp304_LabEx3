package com.example.android.test_tracker.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Nurse(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    @ColumnInfo(name = "firstName") val firstName: String,
    @ColumnInfo(name = "lastName") val lastName: String,
    @ColumnInfo(name = "department") val department: String,
    @ColumnInfo(name = "password") val password: String,
)


