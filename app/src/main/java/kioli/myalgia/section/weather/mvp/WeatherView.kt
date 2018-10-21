package kioli.myalgia.section.weather.mvp

import android.Manifest
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import com.squareup.picasso.Picasso
import kioli.myalgia.R
import kioli.myalgia.common.di.InjectedConstraintLayout
import kioli.myalgia.common.error.MyError
import kioli.myalgia.common.ext.isPermissionGranted
import kioli.myalgia.common.ext.requestPermission
import kioli.myalgia.main.MainActivity.Companion.permissionRequestLocation
import kioli.myalgia.section.weather.di.weatherActivityModule
import kioli.myalgia.section.weather.entity.WeatherUiModel
import kotlinx.android.synthetic.main.view_home.view.*
import kotlinx.android.synthetic.main.weather_content_layout.view.*
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

internal class WeatherView(context: Context) : InjectedConstraintLayout(context), WeatherContract.View {

    private val presenter by instance<WeatherContract.Presenter>()

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.view_home, this, true)
        view.button.setOnClickListener {
            if ((context as AppCompatActivity).isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)) {
                requestWeather(true)
                return@setOnClickListener
            }
            context.requestPermission(Manifest.permission.ACCESS_FINE_LOCATION, permissionRequestLocation)
        }
    }

    override fun viewModule() = Kodein.Module {
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
        loading_layout.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading_layout.visibility = View.GONE
    }

    override fun showWeather(weather: WeatherUiModel) {
        content_layout.visibility = View.VISIBLE
        error_layout.visibility = View.GONE
        Picasso.get().load(weather.icon).into(weather_img)
        weather_location.text = weather.location
        weather_temp.text = weather.temperature.toString()
    }

    override fun showError(error: MyError) {
        content_layout.visibility = View.GONE
        error_layout.visibility = View.VISIBLE
    }

    override fun requestWeather(forceNewWeather: Boolean) {
        presenter.getWeather(forceNewWeather)
    }
}
