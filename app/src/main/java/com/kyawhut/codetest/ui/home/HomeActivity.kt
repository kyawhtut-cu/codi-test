package com.kyawhut.codetest.ui.home

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.kyawhut.codetest.R
import com.kyawhut.codetest.base.BaseActivity
import com.kyawhut.codetest.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author kyawhtut
 * @date 10/16/21
 */
@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    override val layoutID: Int
        get() = R.layout.activity_home

    private val navController: NavController by lazy {
        findNavController(R.id.nav_host_fragment)
    }

    private val appBarConfiguration: AppBarConfiguration by lazy {
        AppBarConfiguration.Builder(
            R.id.upcoming_fragment,
            R.id.popular_fragment
        ).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(findViewById(R.id.toolbar))
        setupActionBarWithNavController(navController, appBarConfiguration)

        vb.bottomNavBar.setupWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }
}
