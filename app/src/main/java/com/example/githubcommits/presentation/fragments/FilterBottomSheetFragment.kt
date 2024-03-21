package com.example.githubcommits.presentation.fragments

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.githubcommits.databinding.FragmentFilterBottomSheetBinding
import com.example.githubcommits.domain.utils.FILTER_DATA_KEY
import com.example.githubcommits.domain.utils.FilterData
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.SimpleDateFormat
import java.util.Locale


class FilterBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentFilterBottomSheetBinding
    private val calendar: Calendar = Calendar.getInstance()
    private var filterData: FilterData = FilterData()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilterBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        binding.setListener {
            when(it){
                binding.etDateFrom -> showDatePicker(binding.etDateFrom)
                binding.etDateTo -> showDatePicker(binding.etDateTo)
                binding.btnApply ->{
                    val author = binding.etAuthor.text.toString().ifBlank { null }
                    val email = binding.etEmail.text.toString().ifBlank { null }
                    val bundle = bundleOf(FILTER_DATA_KEY to filterData.copy(author = author, email = email))
                    findNavController().popBackStack()
                    setFragmentResult(FILTER_DATA_KEY, bundle)
                }
            }
        }
    }
    private fun showDatePicker(textView: TextView) {
        // Create a DatePickerDialog
        val datePickerDialog = DatePickerDialog(
            requireContext(), { DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                // Create a new Calendar instance to hold the selected date
                val selectedDate = Calendar.getInstance()
                // Set the selected date using the values received from the DatePicker dialog
                selectedDate.set(year, monthOfYear, dayOfMonth)
                // Create a SimpleDateFormat to format the date as "dd/MM/yyyy"
                val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                // Format the selected date into a string
                val formattedDate = dateFormat.format(selectedDate.time)
                // Update the TextView to display the selected date with the "Selected Date: " prefix
                textView.text = formattedDate
                filterData = if (binding.etDateFrom == textView) {
                    filterData.copy(fromDate = selectedDate.time.time)
                }else {
                    filterData.copy(toDate = selectedDate.time.time)
                }
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        // Show the DatePicker dialog
        datePickerDialog.show()
    }


}