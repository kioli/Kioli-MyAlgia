package kioli.myalgia.section.weather.mvp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import com.squareup.picasso.Picasso
import kioli.myalgia.R
import kioli.myalgia.common.di.InjectedActivity
import kioli.myalgia.common.ext.isPermissionGranted
import kioli.myalgia.common.ext.requestPermission
import kioli.myalgia.section.weather.di.weatherActivityModule
import kioli.myalgia.section.weather.entity.WeatherModel
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

internal class WeatherActivity : InjectedActivity(), WeatherContract.View {

    private val permissionRequestLocation = 12345
    private val presenter by instance<WeatherContract.Presenter>()

    override fun activityModule() = Kodein.Module {
        import(weatherActivityModule())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            if (isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)) {
                requestWeather(true)
                return@setOnClickListener
            }
            requestPermission(Manifest.permission.ACCESS_FINE_LOCATION, permissionRequestLocation)
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.attachView(this)
        requestWeather(false)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == permissionRequestLocation && grantResults.contains(PackageManager.PERMISSION_GRANTED)) {
            requestWeather(true)
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading.visibility = View.GONE
    }

    override fun returnResultWeather(weather: WeatherModel?) {
        weather?.current?.let {
            Picasso.get().load(it.condition.icon).into(weather_img)
            weather_temp.text = it.temperature_c.toString()
        }
    }

    private fun requestWeather(forceNewWeather: Boolean) {
        presenter.getWeather(forceNewWeather)
    }
}
