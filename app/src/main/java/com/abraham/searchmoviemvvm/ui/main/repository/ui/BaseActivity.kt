package com.abraham.searchmoviemvvm.ui.main.repository.ui

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.abraham.searchmoviemvvm.R

/**
 * Created by Abraham on 11/09/2018.
 */
abstract class BaseActivity : AppCompatActivity(){
    fun replaceFragment(fragment: Fragment, addToBackStack: Boolean) {
        val ft = supportFragmentManager.beginTransaction()
        ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.container, fragment)

        if (addToBackStack) ft.addToBackStack(null)

        ft.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}