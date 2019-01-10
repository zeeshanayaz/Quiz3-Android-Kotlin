package com.zeeshan.quiz3

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.zeeshan.quiz3.fragment.AboutFragment
import com.zeeshan.quiz3.fragment.ProfileFragment
import com.zeeshan.quiz3.fragment.SignInFragment
import com.zeeshan.quiz3.fragment.SignUpFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, SignUpFragment.OnFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {
        Log.d("Fragment","On Fragment Interaction")
    }

    lateinit var signUpFragment: SignUpFragment
    lateinit var signInFragment: SignInFragment
    lateinit var aboutFragment: AboutFragment
    lateinit var profileFragment: ProfileFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        signUpFragment = SignUpFragment()
        signInFragment = SignInFragment()
        aboutFragment = AboutFragment()
        profileFragment = ProfileFragment()

//        aboutFragmentEvoke()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings ->{
                Toast.makeText(this,"About",Toast.LENGTH_SHORT).show()
                aboutFragmentEvoke()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun aboutFragmentEvoke() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_main, aboutFragment)
//            .addToBackStack(aboutFragment.toString())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_sign_in -> {
                Toast.makeText(this,"Sign In",Toast.LENGTH_SHORT).show()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container_main, signInFragment)
                    .addToBackStack(signInFragment.toString())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
//                return true
            }
            R.id.nav_sign_up -> {
                Toast.makeText(this,"Sign Up",Toast.LENGTH_SHORT).show()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container_main, signUpFragment)
                    .addToBackStack(signUpFragment.toString())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
//                return true
            }
            R.id.nav_profile -> {
                Toast.makeText(this,"Profile",Toast.LENGTH_SHORT).show()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container_main, profileFragment)
                    .addToBackStack(profileFragment.toString())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.nav_all_user_list -> {
                Toast.makeText(this,"All USer List",Toast.LENGTH_SHORT).show()
            }
            R.id.nav_chat -> {
                Toast.makeText(this,"Chat",Toast.LENGTH_SHORT).show()
            }
            R.id.nav_share -> {
                Toast.makeText(this,"Share",Toast.LENGTH_SHORT).show()
                val shareIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                    type = "text/plain"
                }
                startActivity(Intent.createChooser(shareIntent, "Feel free to share this app with your friends."))

            }
            R.id.nav_close -> {
                Toast.makeText(this,"Close",Toast.LENGTH_SHORT).show()
                closeApplicationPopup()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun closeApplicationPopup() {
        val dialogBuilder = AlertDialog.Builder(this@MainActivity)
        val create : AlertDialog = dialogBuilder.create()

        dialogBuilder.setCancelable(false)

        dialogBuilder.setTitle("Thanks for Being Here!")
        dialogBuilder.setMessage("Do you want to Close Application?")

        dialogBuilder.setPositiveButton("Yes", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                Toast.makeText(this@MainActivity, "Thank for being with us...", Toast.LENGTH_SHORT).show()
                finishAffinity()
            }

        })
        dialogBuilder.setNegativeButton("No", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                create.dismiss()
                Toast.makeText(this@MainActivity, "Great Idea....", Toast.LENGTH_SHORT).show()
            }
        })
        dialogBuilder.create()
        dialogBuilder.show()
    }
}
