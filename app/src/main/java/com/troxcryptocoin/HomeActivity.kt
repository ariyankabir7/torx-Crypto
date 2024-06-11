package com.troxcryptocoin

import HomeFragment
import InvestFragment
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.troxcryptocoin.databinding.ActivityHomeBinding
import com.troxcryptocoin.fragments.SettingFragment

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val insetsController = ViewCompat.getWindowInsetsController(v)
            insetsController?.isAppearanceLightStatusBars = true
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.navigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.nav_invest -> {
                    loadFragment(InvestFragment())
                    true
                }
                R.id.nav_settings -> {
                    loadFragment(SettingFragment())
                    true
                }
                else -> false
            }
        }

        // Load the default fragment
        if (savedInstanceState == null) {
            binding.navigationView.selectedItemId = R.id.nav_home
        }
    }
    private fun showPpopupDialog() {
        AlertDialog.Builder(this, R.style.TransparentDialogTheme).setView(R.layout.back_popup)
            .setCancelable(true).create().apply {
                show()

                findViewById<MaterialButton>(R.id.buttonCancel)?.setOnClickListener {
                    dismiss()
                }
                findViewById<MaterialButton>(R.id.buttonConfirm)?.setOnClickListener {
                    dismiss()
                    super.onBackPressed()
                    finish()
                }
            }

    }
    private fun loadFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
    override fun onBackPressed() {
        showPpopupDialog()

    }
}