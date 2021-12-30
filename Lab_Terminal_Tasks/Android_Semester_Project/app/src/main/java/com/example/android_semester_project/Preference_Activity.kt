package com.example.android_semester_project

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.preference.Preference
import android.preference.PreferenceFragment
import androidx.preference.PreferenceFragmentCompat

class Preference_Activity : AppCompatActivity(), PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preference)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#146775")))

        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.content_preference, MainPreference())
                .commit()
        }else{
            title = savedInstanceState.getCharSequence(TAG_TITLE)
        }
        supportFragmentManager.addOnBackStackChangedListener {
            if(supportFragmentManager.backStackEntryCount == 0){
                setTitle(R.string.settings)
            }
        }

        setUpToolbar()


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun onBackPressedBtn(view: android.view.View){
        val intent = Intent(this@Preference_Activity, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putCharSequence(TAG_TITLE, title)
    }



    private fun setUpToolbar() {
        supportActionBar?.setTitle(R.string.settings)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class MainPreference: PreferenceFragmentCompat(){
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.main_preference, rootKey)


        }

    }

    fun onPreferenceStartFragment(
        caller: PreferenceFragment?,
        pref: Preference?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun onPreferenceStartFragment(
        caller: PreferenceFragmentCompat?,
        pref: androidx.preference.Preference?
    ): Boolean {
        // Initiate the new fragment
        val args = pref?.extras
        val fragrement = pref?.fragment?.let {
            supportFragmentManager.fragmentFactory.instantiate(
                classLoader,
                it
            ).apply {
                arguments = args
                setTargetFragment(caller, 0)
            }
        }
        fragrement?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.content_preference, it)
                .addToBackStack(null)
                .commit()
        }
        title = pref?.title
        return true
    }

    companion object{
        private val TAG_TITLE = "Preference_Activity"
    }
}