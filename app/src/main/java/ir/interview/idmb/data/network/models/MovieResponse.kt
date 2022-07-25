package ir.interview.idmb.data.network.models

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("Title") var title: String,
    @SerializedName("Year") var year: String,
    @SerializedName("imdbID") var imdbID: String,
    @SerializedName("Type") var type: String,
    @SerializedName("Poster") var poster: String
)
