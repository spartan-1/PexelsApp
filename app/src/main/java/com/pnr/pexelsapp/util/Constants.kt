package com.pnr.pexelsapp.util

class Constants {
    companion object {
        /**
         * Base Url
         */
        var BASE_URL = "https://api.pexels.com/v1/"

        /**
         * api connect timeout
         */
        const val API_CONNECT_TIMEOUT_IN_SECONDS = 30L

        /**
         * api read timeout
         */
        const val API_READ_TIMEOUT_IN_SECONDS = 30L

        /**
         * api write timeout
         */
        const val API_WRITE_TIMEOUT_IN_SECONDS = 30L

        /**
         * cache size
         */
        const val CACHE_SIZE = 10 * 1024 * 1024L

    }
}