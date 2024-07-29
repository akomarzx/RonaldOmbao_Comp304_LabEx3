package com.example.android.test_tracker.database.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(foreignKeys = [ForeignKey(entity = Nurse::class, parentColumns = arrayOf("id"), childColumns = arrayOf("nurseId"))],
        indices = [Index(value = ["nurseId"])]
)
data class Patient (
    @PrimaryKey(autoGenerate = true)  var patientId: Int = 0,
    @NonNull @ColumnInfo(name = "firstName") val firstName : String,
    @NonNull @ColumnInfo(name = "lastName") val lastName : String,
    @NonNull @ColumnInfo(name = "department") val department : String,
    @NonNull @ColumnInfo(name = "nurseId") val nurseId : Long,
    @NonNull @ColumnInfo(name = "room") val room : Int
)
