package com.example.schoolcomposeui.network

import com.example.schoolcomposeui.data.SchoolDataItem
import com.example.schoolcomposeui.data.SchoolDetailData
import retrofit2.http.GET

interface NetworkAPI {
    @GET("/resource/s3k6-pzi2.json")
    suspend fun getSchoolData():List<SchoolDataItem>

    @GET("/resource/f9bf-2cp4.json")
    suspend fun getSchoolDetail():List<SchoolDetailData>
}