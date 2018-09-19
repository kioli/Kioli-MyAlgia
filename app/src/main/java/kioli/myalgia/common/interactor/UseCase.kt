package kioli.myalgia.common.interactor

import kioli.myalgia.common.functional.Either
import kioli.myalgia.common.error.Error

abstract class UseCase<in Params, out ReturnType> where ReturnType : Any {

    abstract fun run(params: Params): Either<Error, ReturnType>
}
