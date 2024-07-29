package com.example.android.test_tracker

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.android.test_tracker.databinding.ActivityEnterTestDataBinding
import com.example.android.test_tracker.databinding.ActivityViewEnterPatientInfoBinding
import com.example.android.test_tracker.viewmodels.PatientViewModel
import com.example.android.test_tracker.viewmodels.PatientViewModelFactory
import com.example.android.test_tracker.viewmodels.TestViewModelFactory


class TestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEnterTestDataBinding

    private val viewModel: PatientViewModel by viewModels {
        TestViewModelFactory(
            (application as HospitalTestTracker).database.testDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_test_data)
    }
}