package com.pnr.pexelsapp.model

import java.io.Serializable

/**
 * Pexel Photo model
 *
 * @property id
 * @property width
 * @property height
 * @property url
 * @property photographer
 * @property photographer_url
 * @property src
 */
data class PexelPhoto(
    val id: String,
    val width: Int,
    val height: Int,
    val url: String,
    val photographer: String,
    val photographer_url: String,
    val src: PhotoSrc
) : Serializable