package com.viginfotech.chennaitimes.backend.apis


import com.google.api.server.spi.config.Api
import com.google.api.server.spi.config.ApiMethod
import com.google.api.server.spi.config.ApiNamespace
import com.google.api.server.spi.config.Named
import com.viginfotech.chennaitimes.backend.Constants
import com.viginfotech.chennaitimes.backend.model.Feed
import com.viginfotech.chennaitimes.backend.tamil.Dinamalar

import com.viginfotech.chennaitimes.backend.Constants.CATEGORY_BUSINESS
import com.viginfotech.chennaitimes.backend.Constants.CATEGORY_CINEMA
import com.viginfotech.chennaitimes.backend.Constants.CATEGORY_HEADLINES
import com.viginfotech.chennaitimes.backend.Constants.CATEGORY_TAMILNADU
import com.viginfotech.chennaitimes.backend.Constants.CATEGORY_WORLD
import com.viginfotech.chennaitimes.backend.Constants.SOURCE_DINAMALAR


/**
 * Created by anand on 1/17/16.
 */
@Api(name = "chennaiTimesApi", version = "v1", namespace = ApiNamespace(ownerDomain = Constants.API_OWNER, ownerName = Constants.API_OWNER, packagePath = Constants.API_PACKAGE_PATH))
class DinamalarEndpoint {

    @ApiMethod(name = "getDinamalarFeedList", path = "dinamalar")
    fun getDinamalarFeedList(@Named("category") category: Int): List<Feed>? {

        when (category) {
            Constants.CATEGORY_HEADLINES,
            Constants.getCATEGORY_TAMILNADU(),
            Constants.getCATEGORY_WORLD(),
            Constants.getCATEGORY_BUSINESS(),
            Constants.CATEGORY_CINEMA-> return Dinamalar.queryDinamalarNews(category)
            else -> return null
        }


    }

    @ApiMethod(name = "getDinamalarDetail", path = "dinamalar/detail")
    fun getDinamalarDetail(@Named("guid") guid: String, @Named("source") sourceId: Int,
                           @Named("category") category: Int): Feed? {

        when (sourceId) {
            INSTANCE.getSOURCE_DINAMALAR() -> return Dinamalar.getDetail(guid, category)

            else -> return null
        }
    }

}
