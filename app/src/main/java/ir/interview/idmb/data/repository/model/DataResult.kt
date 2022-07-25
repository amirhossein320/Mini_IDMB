package ir.interview.idmb.data.repository.model

sealed class DataResult<out T : Any> {

    object Loading : DataResult<Nothing>()
    object NoInternet : DataResult<Nothing>()
    object NoData : DataResult<Nothing>()
    data class Error(val message: String) : DataResult<Nothing>()
    data class Data<out T : Any>(val data: T) : DataResult<T>()
}