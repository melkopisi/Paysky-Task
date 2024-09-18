package me.melkopisi.payskytask.domain.remote.exceptions

sealed class Exceptions(msg: String) : Throwable(msg) {
    class NetworkNotAvailableException : Exceptions("Network is not available.")
    class DataRetrievingFailException(errorMsg: String) : Exceptions(errorMsg)
    class NoDataException : Exceptions("No data.")
    class ServerError : Exceptions("Server Error.")
    class GeneralError : Exceptions("General Error.")
    class NoLocalDataException : Exceptions("No local data.")


}