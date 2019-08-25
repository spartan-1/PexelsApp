package com.pnr.pexelsapp.model

import java.io.Serializable

/**
 * Search API result model
 *
 * @property total_results
 * @property page
 * @property per_page
 * @property photos
 */
data class ApiResult(
    val total_results:Int,
    val page:Int,
    val per_page:Int,
    val photos:List<PexelPhoto>
) : Serializable