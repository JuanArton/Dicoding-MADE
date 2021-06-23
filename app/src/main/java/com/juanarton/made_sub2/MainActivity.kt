package com.juanarton.made_sub2

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.juanarton.made_sub2.databinding.ActivityMainBinding
import com.juanarton.made_sub2.fragments.HomeFragment
import com.juanarton.made_sub2.fragments.SearchFragment
import nl.joery.animatedbottombar.AnimatedBottomBar

class MainActivity : AppCompatActivity() {

    private var bindingtmp: ActivityMainBinding? = null
    private val binding get() = bindingtmp!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingtmp = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val color = ContextCompat.getColor(this, R.color.black).toString().toInt()
        supportActionBar?.setBackgroundDrawable(
            ColorDrawable(Color.parseColor(
                String.format("#%06X", 0xFFFFFF and color)
            ))
        )
        supportActionBar?.title = resources.getString(R.string.popular)

        navigationChange(HomeFragment())

        binding.bottomBar.setOnTabSelectListener(object : AnimatedBottomBar.OnTabSelectListener {
            override fun onTabSelected(
                lastIndex: Int,
                lastTab: AnimatedBottomBar.Tab?,
                newIndex: Int,
                newTab: AnimatedBottomBar.Tab
            ) {
                when(newIndex){
                    0 -> navigationChange(SearchFragment())
                    1 -> navigationChange(HomeFragment())
                    2 -> moveToFavoriteFragment()
                }
            }
        })
    }

    private fun moveToFavoriteFragment() {
        val fragment = instantiateFragment(className)
        if (fragment != null) {
            navigationChange(fragment)
        }
    }

    private val className: String
        get() = "com.juanarton.favorite.fragments.FavoriteFragment"

    private fun instantiateFragment(className: String): Fragment? {
        return try {
            Class.forName(className).newInstance() as Fragment
        } catch (e: Exception) {
            Toast.makeText(this, "Module not found", Toast.LENGTH_SHORT).show()
            null
        }
    }

    private fun navigationChange(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment_activity_main, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }
}