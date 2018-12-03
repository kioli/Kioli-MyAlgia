package kioli.myalgia.section.main

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.view.Menu
import android.view.MenuItem
import kioli.myalgia.R
import kioli.myalgia.common.di.InjectedActivity
import kioli.myalgia.section.history.mvp.HistoryView
import kioli.myalgia.section.home.HomeView
import kioli.myalgia.section.main.state.manager.IStateManager
import kioli.myalgia.section.settings.SettingsActivity
import kotlinx.android.synthetic.main.view_main.*
import org.kodein.di.generic.instance

internal class MainActivity : InjectedActivity() {

    private val stateManager by instance<IStateManager>()

    private val sectionsAdapter: SectionsAdapter by lazy { SectionsAdapter(homeView, historyView) }

    private val homeView by lazy { HomeView(this) }
    private val historyView by lazy { HistoryView(baseContext) }

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
            R.id.save -> {
                stateManager.storeState()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == permissionRequestLocation && grantResults.contains(PackageManager.PERMISSION_GRANTED)) {
            homeView.requestWeather(true)
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    internal companion object {
        internal const val permissionRequestLocation = 12345
    }
}
