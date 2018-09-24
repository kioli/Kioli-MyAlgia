package kioli.myalgia.common.error

/**
 * Domain errors will fit with one of these types.
 */
sealed class MyError {
    object NetworkConnection : MyError()
    object ServerError : MyError()

    abstract class FeatureError : MyError()
}
