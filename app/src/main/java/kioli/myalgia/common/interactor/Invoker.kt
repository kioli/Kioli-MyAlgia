package kioli.myalgia.common.interactor

import kioli.myalgia.common.error.MyError
import kioli.myalgia.common.functional.Either

interface Invoker {

    fun <Params, Type : Any> execute(useCase: UseCase<Params, Type>,
                                     params: Params,
                                     onResult: (Either<MyError, Type>) -> Unit)
}
