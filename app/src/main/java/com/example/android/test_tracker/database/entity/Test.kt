
package com.example.android.test_tracker.database.entity
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(foreignKeys =
    [ForeignKey(entity = Nurse::class, parentColumns = arrayOf("id"), childColumns = arrayOf("nurseId")),
    ForeignKey(entity = Patient::class, parentColumns = arrayOf("patientId"), childColumns = arrayOf("patientId"))],
    indices = [Index(value = ["nurseId"]), Index(value = ["patientId"])]
)
data class Test (
    @ColumnInfo(name = "patientId") val patientId: Long = 0,
    @ColumnInfo(name = "nurseId") val nurseId: Long,
    @ColumnInfo(name = "BPL") val bpl: Int,
    @ColumnInfo(name = "BPH") val bph: Int,
    @ColumnInfo(name = "temperature") val temperature: Double,
) {
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "testId")
    var testId: Int = 0
}
