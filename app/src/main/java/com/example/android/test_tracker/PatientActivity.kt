package com.example.android.test_tracker

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.android.test_tracker.databinding.ActivityViewEnterPatientInfoBinding
import com.example.android.test_tracker.viewmodels.PatientViewModel
import com.example.android.test_tracker.viewmodels.PatientViewModelFactory


class PatientActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewEnterPatientInfoBinding

    private val viewModel: PatientViewModel by viewModels {
        PatientViewModelFactory(
            (application as HospitalTestTracker).database.patientDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_enter_patient_info) // Replace with your layout file
    }
}