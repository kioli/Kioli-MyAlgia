package kioli.myalgia.common.di

import android.content.Context
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import kioli.myalgia.BuildConfig
import kioli.myalgia.common.interactor.Invoker
import kioli.myalgia.common.interactor.UseCaseInvoker
import kioli.myalgia.element.weather.api.WeatherApi
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
    bind<Invoker>() with singleton { UseCaseInvoker(instance()) }
    import(httpModule())
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