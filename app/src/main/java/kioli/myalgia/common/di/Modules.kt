package kioli.myalgia.common.di

import android.arch.persistence.room.Room
import android.content.Context
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import kioli.myalgia.BuildConfig
import kioli.myalgia.common.ext.readSettingOptionFromSharedPref
import kioli.myalgia.common.interactor.Invoker
import kioli.myalgia.common.interactor.UseCase
import kioli.myalgia.common.interactor.UseCaseInvoker
import kioli.myalgia.element.weather.api.WeatherApi
import kioli.myalgia.section.history.repository.HistoryLocalDataSource
import kioli.myalgia.section.history.repository.HistoryRepository
import kioli.myalgia.section.history.repository.SourceStateLocal
import kioli.myalgia.section.main.state.db.StateDb
import kioli.myalgia.section.main.state.manager.IStateManager
import kioli.myalgia.section.main.state.manager.StateManager
import kioli.myalgia.section.main.state.interactor.StoreStateUseCase
import kioli.myalgia.section.main.state.entity.StateModel
import kioli.myalgia.section.settings.entity.Option
import kioli.myalgia.section.settings.entity.Setting
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.asCoroutineDispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun appModule(appContext: Context) = Kodein.Module("module app", false) {
    bind<Context>() with provider { appContext }
    bind<CoroutineDispatcher>() with provider { AsyncTask.THREAD_POOL_EXECUTOR.asCoroutineDispatcher() }
    bind<UseCase<StoreStateUseCase.Params, StateModel>>() with provider { StoreStateUseCase(instance()) }

    bind<List<Option>>() with provider {
        val context = instance() as Context
        Setting.values().map { context.readSettingOptionFromSharedPref(it) }
    }

    bind<Invoker>() with singleton { UseCaseInvoker(instance()) }
    bind<IStateManager>() with singleton { StateManager(instance(), instance()) }

    import(httpModule())
    import(stateModule())
}

fun stateModule() = Kodein.Module("module state", false) {
    bind<HistoryRepository>() with provider { HistoryRepository(instance()) }

    bind<SourceStateLocal>() with singleton { HistoryLocalDataSource(instance()) }
    bind<StateDb>() with singleton { Room.databaseBuilder(instance(), StateDb::class.java, "state.db").build() }
}

fun httpModule() = Kodein.Module("module http", false) {
    bind<Interceptor>() with singleton {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    bind<OkHttpClient>() with singleton {
        OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(instance())
            }
        }.build()
    }
    bind<WeatherApi>() with singleton {
        Retrofit.Builder()
                .baseUrl("https://api.apixu.com/v1/")
                .client(instance())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherApi::class.java)
    }
}

fun injectedActivityModule(activity: AppCompatActivity) = Kodein.Module("module injected activity", false) {
    bind<Context>(overrides = true) with provider { activity }
}