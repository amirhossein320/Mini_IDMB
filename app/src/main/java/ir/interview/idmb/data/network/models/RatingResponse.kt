package ir.interview.idmb.data.network.models

import com.google.gson.annotations.SerializedName

data class RatingResponse(
    @SerializedName("Source") var source: String? = null,
    @SerializedName("Value") var value: String? = null
)
