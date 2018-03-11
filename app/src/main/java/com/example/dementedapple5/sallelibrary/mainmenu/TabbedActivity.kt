package com.example.dementedapple5.sallelibrary.mainmenu

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.support.v7.app.AppCompatActivity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.Menu
import android.view.MenuItem

import com.example.dementedapple5.sallelibrary.R
import com.example.dementedapple5.sallelibrary.mainmenu.adapters.SectionsPagerAdapter
import com.example.dementedapple5.sallelibrary.userauth.activities.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_tabbed.*
import kotlinx.android.synthetic.main.tabs.*
import kotlinx.android.synthetic.main.toolbar.*
import android.content.DialogInterface
import android.graphics.Color
import android.support.transition.TransitionManager
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader
import android.support.v4.view.MenuItemCompat
import android.support.v4.view.MenuItemCompat.expandActionView
import android.support.v7.app.AlertDialog
import android.support.v7.widget.SearchView
import android.view.View
import android.view.ViewAnimationUtils
import java.lang.reflect.AccessibleObject.setAccessible
import android.widget.TextView
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.support.v4.view.MenuItemCompat.getActionView
import android.util.Log
import android.widget.ImageView


class TabbedActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<String> {
    override fun onCreateLoader(id: Int, args: Bundle?): Loader<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoadFinished(loader: Loader<String>?, data: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoaderReset(loader: Loader<String>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mSearchView: SearchView
    private lateinit var search_menu: Menu
    private lateinit var search_item: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabbed)
        setSupportActionBar(toolbar)

        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        container.adapter = mSectionsPagerAdapter

        mAuth = FirebaseAuth.getInstance()

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings_menu, menu)

        val searchItem: MenuItem? = menu?.findItem(R.id.action_search)
        mSearchView = searchItem?.actionView as SearchView
        mSearchView.queryHint = "Busca un título"

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_logout -> {
                confirmDialog()
                return true
            }

            R.id.action_search -> {
                circleReveal(R.id.toolbar, 1, true, true)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun confirmDialog() {
        AlertDialog.Builder(this)
                .setTitle(R.string.action_log_out)
                .setMessage(R.string.logout_confirmation)
                .setPositiveButton(R.string.logout_ok, DialogInterface.OnClickListener { _, _ ->
                    mAuth.signOut()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                })
                .setNegativeButton(R.string.logout_cancel, null).show()
    }

    private fun circleReveal(viewId: Int, startingPos: Int, hasOverflow: Boolean, isShow: Boolean) {
        val view = findViewById<View>(viewId)
        var width = view.width

        if (startingPos > 0) {
            width -= (startingPos * resources.getDimensionPixelSize(R.dimen.abc_action_button_min_width_material)) - (resources.getDimensionPixelSize(R.dimen.abc_action_button_min_width_material))
        }

        if (hasOverflow) {
            width -= resources.getDimensionPixelSize(R.dimen.abc_action_button_min_width_overflow_material)
        }

        val centerX = width
        val centerY = view.height / 2
        val anim: Animator

        if (isShow) {
            anim = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, 0f, width.toFloat())
        } else {
            anim = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, width.toFloat(), 0f)
        }

        anim.duration = 220L

        anim.addListener(object: AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                if (!isShow) {
                    super.onAnimationEnd(animation)
                    view.visibility = View.INVISIBLE
                }
            }
        })

        if (isShow) {
            view.visibility = View.VISIBLE
        }

        anim.start()
    }
}
