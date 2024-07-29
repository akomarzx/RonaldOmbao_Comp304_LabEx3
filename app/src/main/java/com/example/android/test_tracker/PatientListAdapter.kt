/*
 * Copyright (C) 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.test_tracker

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.test_tracker.database.entity.Patient
import com.example.android.test_tracker.databinding.PatientInfoItemBinding

class PatientListAdapter(
    private val onItemClicked: (Patient) -> Unit
) : ListAdapter<Patient, PatientListAdapter.PatientListViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Patient>() {
            override fun areItemsTheSame(oldItem: Patient, newItem: Patient): Boolean {
                return oldItem.patientId == newItem.patientId
            }

            override fun areContentsTheSame(oldItem: Patient, newItem: Patient): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientListViewHolder {
        val viewHolder = PatientListViewHolder(
            PatientInfoItemBinding.inflate(
                LayoutInflater.from( parent.context),
                parent,
                false
            )
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: PatientListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PatientListViewHolder(
        private var binding: PatientInfoItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        fun bind(patient: Patient) {
            binding.stopNameTextView.text = "${patient.firstName}${patient.lastName}"
        }
    }
}
