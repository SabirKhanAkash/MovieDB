sealed class GenericApiResponse<out T> {
    data class Success<out T>(val data: T) : GenericApiResponse<T>()
    data class Error(val message: String?) : GenericApiResponse<Nothing>()
}
