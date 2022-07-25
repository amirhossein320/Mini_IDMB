package ir.interview.idmb.data.network.models

import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("Search") var data: ArrayList<MovieResponse> = arrayListOf(),
    @SerializedName("totalResults") var totalResults: String? = null,
    @SerializedName("Response") var response: String? = null
)
