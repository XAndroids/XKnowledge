package com.android.xknowledge.jetpack.navigation

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.android.xknowledge.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class NavigationActivity : AppCompatActivity() {
    private lateinit var mAppBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val host: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.navigation_container_fragment)
                    as NavHostFragment? ?: return
        val navController = host.navController

        mAppBarConfiguration = AppBarConfiguration(navController.graph)

        //如果是平板抽屉导航栏，你需要移除上一步老的mAppBarConfiguration
        val drawerLayout: DrawerLayout? = findViewById(R.id.navigation_drawer_layout)
        mAppBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.deepLinkFragment),
            drawerLayout
        )

        setupActionBar(navController)

        setupNavigationMenu(navController)

        setupBottomNavMenu(navController)

        navController.addOnDestinationChangedListener { _, destation, _ ->
            val dest: String = try {
                resources.getResourceName(destation.id)
            } catch (e: Resources.NotFoundException) {
                Integer.toString(destation.id)
            }

            Toast.makeText(
                this@NavigationActivity, "Navigated to $dest",
                Toast.LENGTH_SHORT
            ).show()
            Log.d("NavigationActivity", "Navigated to $dest")
        }
    }

    private fun setupActionBar(navController: NavController) {
        //设置action bar的导航，看什么时候出现option Menu或者抽屉导航
        setupActionBarWithNavController(navController, mAppBarConfiguration)
    }

    private fun setupNavigationMenu(navController: NavController) {
        //如果在分屏模式，即横屏或者宽屏下，你可以从左边拖出这个view
        //这不修改actionbar
        val sideNavView = findViewById<NavigationView>(R.id.navigation_navigation)
        sideNavView?.setupWithNavController(navController)
    }

    private fun setupBottomNavMenu(navController: NavController) {
        //如果在竖屏模式下，使用底部导航进行导航
        val bottomNav = findViewById<BottomNavigationView>(R.id.navigation_bottom_navigation)
        bottomNav?.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val retValue = super.onCreateOptionsMenu(menu)
        val navigationView = findViewById<NavigationView>(R.id.navigation_navigation)
        //即不是平板，不是横屏的时候，即竖屏的时候，底部BottomNavigation长度有点，所以使用有Setting Menu
        if (navigationView == null) {
            menuInflater.inflate(R.menu.navigation_overflow_menu, menu)
            return true
        }
        return retValue
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //使得OptionsMenu也支持点击后导航
        return item.onNavDestinationSelected(findNavController(R.id.navigation_container_fragment))
                || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        //允许NavigationUI视情况，来支持正确的导航或者抽屉布局抽屉菜单
        return findNavController(R.id.navigation_container_fragment).navigateUp(mAppBarConfiguration)
    }
}

