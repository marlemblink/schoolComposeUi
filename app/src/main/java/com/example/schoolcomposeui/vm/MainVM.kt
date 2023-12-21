package com.example.schoolcomposeui.vm

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schoolcomposeui.data.SchoolDataItem
import com.example.schoolcomposeui.data.SchoolDetailData
import com.example.schoolcomposeui.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainVM constructor(private val repository: Repository): ViewModel() {
    var dataList by mutableStateOf<List<SchoolDataItem>>(emptyList())
    var dataDetailList by mutableStateOf<List<SchoolDetailData>>(emptyList())

    init {
        getSchoolDataList()
        getSchoolDetailList()
    }
    private fun getSchoolDataList() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getSchoolData().let {
                Log.d("******", "call main data from MainVM")
                dataList = it
            }
        }
    }

    private fun getSchoolDetailList() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getSchoolDetailData().let {
                Log.d("******", "call detail from MainVM")
                dataDetailList = it
            }
        }
    }
}