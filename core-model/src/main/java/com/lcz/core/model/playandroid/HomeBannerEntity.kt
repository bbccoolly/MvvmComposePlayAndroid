package com.lcz.core.model.playandroid

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


/**
 *
 * desc: TODO
 *
 * create by lcz on 2023-03-11
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class HomeBannerEntity(
    val desc: String? = null,
    val id: Int? = 0,
    val imagePath: String? = null,
    val isVisible: Int? = 0,
    val order: Int? = 0,
    val title: String? = null,
    val type: Int? = 0,
    val url: String? = null
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class HomeBannersEntity(
    val banners: List<HomeBannerEntity>

) : Parcelable {
    override fun equals(other: Any?): Boolean {
        if (other is HomeBannersEntity) {
            if (this.banners.size == other.banners.size) {
                this.banners.forEachIndexed { index, banner ->
                    if (banner != other.banners[index]) {
                        return false
                    }
                }
                return true
            } else {
                return false
            }
        } else {
            return super.equals(other)
        }
    }

    override fun hashCode() = banners.hashCode()
}