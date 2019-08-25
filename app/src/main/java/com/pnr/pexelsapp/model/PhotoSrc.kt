package com.pnr.pexelsapp.model

import java.io.Serializable

/**
 * Photo source model
 *
 * @property original
 * @property large2x
 * @property large
 * @property medium
 * @property small
 * @property portrait
 * @property landscape
 * @property tiny
 */
data class PhotoSrc(
    val original: String,
    val large2x: String,
    val large: String,
    val medium: String,
    val small: String,
    val portrait: String,
    val landscape: String,
    val tiny: String
) : Serializable