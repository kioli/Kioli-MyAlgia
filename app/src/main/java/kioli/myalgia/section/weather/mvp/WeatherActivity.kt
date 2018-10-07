package kioli.myalgia.section.weather.mvp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.squareup.picasso.Picasso
import kioli.myalgia.R
import kioli.myalgia.common.di.InjectedActivity
import kioli.myalgia.common.error.MyError
import kioli.myalgia.common.ext.isPermissionGranted
import kioli.myalgia.common.ext.requestPermission
import kioli.myalgia.section.settings.SettingsActivity
import kioli.myalgia.section.weather.di.weatherActivityModule
import kioli.myalgia.section.weather.entity.WeatherModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.weather_content_layout.*
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == permissionRequestLocation && grantResults.contains(PackageManager.PERMISSION_GRANTED)) {
            requestWeather(true)
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun showLoading() {
        loading_layout.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading_layout.visibility = View.GONE
    }

    override fun showWeather(weather: WeatherModel) {
        content_layout.visibility = View.VISIBLE
        error_layout.visibility = View.GONE

        Picasso.get().load(weather.current.condition.icon).into(weather_img)
        weather_time.text = weather.current.last_updated
        weather_temp.text = weather.current.temperature_c.toString()
    }

    override fun showError(error: MyError) {
        content_layout.visibility = View.GONE
        error_layout.visibility = View.VISIBLE
    }

    private fun requestWeather(forceNewWeather: Boolean) {
        presenter.getWeather(forceNewWeather)
    }
}
