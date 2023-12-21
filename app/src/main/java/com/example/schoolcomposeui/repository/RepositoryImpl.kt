package com.example.schoolcomposeui.repository

import com.example.schoolcomposeui.data.SchoolDataItem
import com.example.schoolcomposeui.data.SchoolDetailData
import com.example.schoolcomposeui.network.NetworkAPI

class RepositoryImpl(private val networkAPI: NetworkAPI): Repository {
    override suspend fun getSchoolData(): List<SchoolDataItem> {
        return networkAPI.getSchoolData()
    }
    override suspend fun getSchoolDetailData(): List<SchoolDetailData> {
        return networkAPI.getSchoolDetail()
    }


}