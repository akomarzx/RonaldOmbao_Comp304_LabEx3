package com.example.android.test_tracker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.android.test_tracker.viewmodels.NurseViewModel
import com.example.android.test_tracker.viewmodels.NurseViewModelFactory
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var nurseIdEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var registerButton : Button

    private val viewModel: NurseViewModel by viewModels {
        NurseViewModelFactory(
            (application as HospitalTestTracker).database.nurseDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login) // Replace with your layout file

        nurseIdEditText = findViewById(R.id.nurseIdEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)
        registerButton = findViewById(R.id.registerButton)

        loginButton.setOnClickListener {
            val nurseId = nurseIdEditText.text.toString()
            val password = passwordEditText.text.toString()

            lifecycleScope.launch {
                val nurse = viewModel.getNurseById(nurseId.toIntOrNull() ?: 0).firstOrNull()
                if (nurse != null && nurse.password  == password) {
                    saveUserIdToSharedPreferences(nurse.id)
                    startActivity(Intent(this@LoginActivity, PatientActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this@LoginActivity, "Invalid username or password", Toast.LENGTH_SHORT).show()
                }
            }
        }

        registerButton.setOnClickListener{
            val intent = Intent(this@LoginActivity, RegisterNurseActivity::class.java)
            startActivity(intent)
        }
    }

    private fun saveUserIdToSharedPreferences(userId: Long?) {
        val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        if (userId != null) {
            sharedPreferences.edit().putLong("user_id", userId).apply()
        }
    }
}