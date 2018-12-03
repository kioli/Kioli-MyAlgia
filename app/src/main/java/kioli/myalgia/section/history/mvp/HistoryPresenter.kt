package kioli.myalgia.section.history.mvp

import kioli.myalgia.common.error.MyError
import kioli.myalgia.common.functional.Either
import kioli.myalgia.common.interactor.Invoker
import kioli.myalgia.common.interactor.UseCase
import kioli.myalgia.common.mvp.BasePresenter
import kioli.myalgia.element.weather.mvp.HistoryContract
import kioli.myalgia.section.main.state.entity.StateModel
import kioli.myalgia.section.history.mapper.HistoryMapper

internal class HistoryPresenter(private val invoker: Invoker,
                                private val mapper: HistoryMapper,
                                private val getHistory: UseCase<Unit, List<StateModel>>)
    : BasePresenter<HistoryContract.View>(), HistoryContract.Presenter {

    override fun getHistoryItems() {
        view?.showLoading()
        val params = Unit
        invoker.execute(getHistory, params, ::onHistoryArrived)
    }

    private fun onHistoryArrived(result: Either<MyError, List<StateModel>>) {
        view?.hideLoading()
        result.fold(ifLeft = {
            view?.showEmptyState()
        }, ifRight = { listModels ->
            view?.showHistory(listModels.map { mapper.mapToLeft(it) })
        })
    }
}