package com.example.schoolcomposeui.repository

import com.example.schoolcomposeui.data.SchoolDataItem
import com.example.schoolcomposeui.data.SchoolDetailData

interface Repository {
    suspend fun getSchoolData(): List<SchoolDataItem>
    suspend fun getSchoolDetailData(): List<SchoolDetailData>
}