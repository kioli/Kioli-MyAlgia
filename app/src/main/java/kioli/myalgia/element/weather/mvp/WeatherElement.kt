package kioli.myalgia.element.weather.mvp

import android.Manifest
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.squareup.picasso.Picasso
import kioli.myalgia.R
import kioli.myalgia.common.di.InjectedContainer
import kioli.myalgia.common.error.MyError
import kioli.myalgia.common.ext.isPermissionGranted
import kioli.myalgia.common.ext.requestPermission
import kioli.myalgia.element.weather.di.weatherActivityModule
import kioli.myalgia.element.weather.entity.WeatherUiModel
import kioli.myalgia.main.MainActivity.Companion.permissionRequestLocation
import kotlinx.android.synthetic.main.view_weather.view.*
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

internal class WeatherElement(context: Context) : InjectedContainer(context), WeatherContract.View {

    private val presenter by instance<WeatherContract.Presenter>()

    init {
        val view = View.inflate(context, R.layout.view_weather, this)
        view.setOnClickListener {
            if ((context as AppCompatActivity).isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)) {
                requestWeather(true)
                return@setOnClickListener
            }
            context.requestPermission(Manifest.permission.ACCESS_FINE_LOCATION, permissionRequestLocation)
        }
    }

    override fun elementModule() = Kodein.Module("module weather element", false) {
        import(weatherActivityModule())
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        presenter.attachView(this)
        requestWeather(false)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.detachView()
    }

    override fun showLoading() {
        weather_error.visibility = View.GONE
        weather_loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        weather_loading.visibility = View.GONE
    }

    override fun showWeather(weather: WeatherUiModel) {
        Picasso.get().load(weather.icon).into(weather_img)
        weather_location.text = weather.location
        weather_temp.text = weather.temperature.toString()
    }

    override fun showError(error: MyError) {
        weather_error.visibility = View.VISIBLE
    }

    override fun requestWeather(forceNewWeather: Boolean) {
        presenter.getWeather(forceNewWeather)
    }
}
