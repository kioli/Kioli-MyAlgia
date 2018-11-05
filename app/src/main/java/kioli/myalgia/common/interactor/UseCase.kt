package kioli.myalgia.common.interactor

import kioli.myalgia.common.error.MyError
import kioli.myalgia.common.functional.Either

abstract class UseCase<in Params, out ReturnType> where ReturnType : Any {

    abstract fun run(params: Params): Either<MyError, ReturnType>
}
