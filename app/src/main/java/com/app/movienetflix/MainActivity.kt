package com.app.movienetflix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.app.movienetflix.movie.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    val movieViewModel by viewModels<MovieViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = (nav_host_fragment as NavHostFragment).navController
        NavigationUI.setupWithNavController(bottom_navigation, navController)
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.showMovie -> {
                    movieViewModel.getAllMovie()
                    navController.navigate(R.id.action_to_movieFragment)

                    true
                }
                R.id.createMovie -> {
                    navController.navigate(R.id.action_to_createMovieFragment)
                    true
                }
                else -> {
                    true
                }
            }
        }
    }
}

