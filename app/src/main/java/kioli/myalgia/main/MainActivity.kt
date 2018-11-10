package kioli.myalgia.main

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kioli.myalgia.R
import kioli.myalgia.common.di.InjectedActivity
import kioli.myalgia.element.weather.mvp.WeatherContract
import kioli.myalgia.section.settings.SettingsActivity
import kotlinx.android.synthetic.main.view_main.*
import android.support.v7.app.AppCompatDelegate

internal class MainActivity : InjectedActivity() {

    private val sectionsAdapter: SectionsAdapter by lazy { SectionsAdapter() }

    private val pageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) = Unit

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) = Unit

        override fun onPageSelected(position: Int) {
            navigation.selectedItemId = navigation.menu.getItem(position).itemId
        }
    }

    private val navigationListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                pager.currentItem = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_history -> {
                pager.currentItem = 1
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_main)
        pager.adapter = sectionsAdapter
        pager.addOnPageChangeListener(pageChangeListener)
        navigation.setOnNavigationItemSelectedListener(navigationListener)
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
            (pager.findViewWithTag<View>(Section.HOME.tag) as WeatherContract.View).requestWeather(true)
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    internal companion object {
        internal const val permissionRequestLocation = 12345
    }
}
