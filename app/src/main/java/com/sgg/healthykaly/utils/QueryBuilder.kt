package com.sgg.healthykaly.utils

import com.sgg.healthykaly.utils.QueryConstants.DEFAULT_LOAD_SIZE
import com.sgg.healthykaly.utils.QueryConstants.DEFAULT_MAX_FAT
import com.sgg.healthykaly.utils.QueryConstants.INITIAL_PAGE
import com.sgg.healthykaly.utils.QueryConstants.PARAM_LOAD_SIZE
import com.sgg.healthykaly.utils.QueryConstants.PARAM_MAX_FAT
import com.sgg.healthykaly.utils.QueryConstants.PARAM_PAGE

class QueryBuilder {
    private val queryMap: MutableMap<String, Int> = mutableMapOf(PARAM_LOAD_SIZE to DEFAULT_LOAD_SIZE,
                                                                 PARAM_PAGE to INITIAL_PAGE)

    fun addParam(key: String,
                 value: Int): QueryBuilder {
        queryMap[key] = value
        return this
    }

    fun build(): Map<String, Int> {
        return queryMap
    }

    companion object {
        val defaultQuery = mapOf(PARAM_LOAD_SIZE to DEFAULT_LOAD_SIZE,
                                 PARAM_PAGE to INITIAL_PAGE,
                                 PARAM_MAX_FAT to DEFAULT_MAX_FAT)
    }
}