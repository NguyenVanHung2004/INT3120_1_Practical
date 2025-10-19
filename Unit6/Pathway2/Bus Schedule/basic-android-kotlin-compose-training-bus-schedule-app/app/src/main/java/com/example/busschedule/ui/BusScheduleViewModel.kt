package com.example.busschedule.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.busschedule.BusScheduleApplication
import com.example.busschedule.data.BusSchedule
import com.example.busschedule.data.BusScheduleDao
import kotlinx.coroutines.flow.Flow

class BusScheduleViewModel(private val busScheduleDao: BusScheduleDao): ViewModel() {

    fun getFullSchedule(): Flow<List<BusSchedule>> = busScheduleDao.getAll()

    fun getScheduleFor(stopName: String): Flow<List<BusSchedule>> =
        busScheduleDao.getByStopName(stopName)

    companion object {
        val factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY] as BusScheduleApplication)
                BusScheduleViewModel(application.database.busScheduleDao())
            }
        }
    }
}