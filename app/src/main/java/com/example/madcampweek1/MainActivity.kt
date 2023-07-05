package com.example.madcampweek1

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.madcampweek1.databinding.ActivityMainBinding
import com.example.madcampweek1.ui.home.HomeFragment
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var navController: NavController
    private val tabTextList = listOf("Contact", "Photos", "Weather")
    private val tabIconList = listOf(R.drawable.contacticon, R.drawable.photoicon, R.drawable.weathericon3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.viewPager01.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(binding.tabLayout01, binding.viewPager01){
                tab, pos ->
            tab.text = tabTextList[pos]
            tab.setIcon(tabIconList[pos])
        }.attach()
    }

    private fun scrollToTopOfHomeFragment() {
        // Find the HomeFragment by ID
        val homeFragment =
            supportFragmentManager.findFragmentById(R.id.viewPager01)
                ?.childFragmentManager
                ?.fragments
                ?.find { it is HomeFragment } as? HomeFragment

        // Call scrollToTop() if HomeFragment is not null
        homeFragment?.scrollToTop()
    }
}