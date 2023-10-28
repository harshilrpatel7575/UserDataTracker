package com.example.demo3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.demo3.databinding.FragmentProfileBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.updateBtn.setOnClickListener {
            val userName = binding.userName.text.toString()
            val firstName = binding.firstName.text.toString()
            val lastname = binding.lastname.text.toString()
            val age = binding.age.text.toString()

            updateData(userName, firstName, lastname, age)
        }

        return view
    }

    private fun updateData(userName: String, firstName: String, lastname: String, age: String) {
        database = FirebaseDatabase.getInstance().getReference("Users")
        val user = mapOf(
            "firstName" to firstName,
            "lastname" to lastname,
            "age" to age
        )

        database.child(userName).updateChildren(user).addOnSuccessListener {
            binding.userName.text.clear()
            binding.firstName.text.clear()
            binding.lastname.text.clear()
            binding.age.text.clear()
            Toast.makeText(requireContext(), "Successfully Updated", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(requireContext(), "Failed to Update", Toast.LENGTH_SHORT).show()
        }
    }
}
