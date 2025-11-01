package com.example.juicetracker.ui

import android.view.LayoutInflater
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.juicetracker.databinding.FragmentEntryDialogBinding

class EntryDialogFragment : BottomSheetDialogFragment() {
    private val entryViewModel by viewModels<EntryViewModel> { AppViewModelProvider.Factory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentEntryDialogBinding.inflate(inflater, container, false).root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentEntryDialogBinding.bind(view)

    }

}