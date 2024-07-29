package com.example.android.test_tracker

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.android.test_tracker.databinding.ActivityRegisterNurseBinding
import com.example.android.test_tracker.viewmodels.NurseViewModel
import com.example.android.test_tracker.viewmodels.NurseViewModelFactory

class RegisterNurseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterNurseBinding

    private val viewModel: NurseViewModel by viewModels() {
        NurseViewModelFactory(
            (application as HospitalTestTracker).database.nurseDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterNurseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewModel = viewModel

        lifecycleScope.launchWhenStarted {
            viewModel.registrationResult.collect { result ->
                if (result) {
                    Toast.makeText(this@RegisterNurseActivity, "Nurse registered successfully!", Toast.LENGTH_SHORT).show()
                    finish() // Close the activity after successful registration
                } else {
                    Toast.makeText(this@RegisterNurseActivity, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}