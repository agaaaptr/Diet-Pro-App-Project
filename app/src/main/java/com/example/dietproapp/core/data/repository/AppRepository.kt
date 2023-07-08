package com.example.dietproapp.core.data.repository

import com.example.dietproapp.core.data.source.local.LocalDataSource
import com.example.dietproapp.core.data.source.remote.RemoteDataSource

class AppRepository(val local:LocalDataSource, val remote:RemoteDataSource) {



}