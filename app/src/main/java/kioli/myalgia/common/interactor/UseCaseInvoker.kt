package kioli.myalgia.common.interactor

import kioli.myalgia.common.error.MyError
import kioli.myalgia.common.functional.Either
import kotlinx.coroutines.*

class UseCaseInvoker(private val dispatcher: CoroutineDispatcher) : Invoker {

    override fun <Params, Type : Any> execute(useCase: UseCase<Params, Type>,
                                              params: Params,
                                              onResult: (Either<MyError, Type>) -> Unit) {
        val job = GlobalScope.async(dispatcher, CoroutineStart.DEFAULT, { useCase.run(params) })
        GlobalScope.launch(Dispatchers.Main, CoroutineStart.DEFAULT) { onResult.invoke(job.await()) }
    }
}
