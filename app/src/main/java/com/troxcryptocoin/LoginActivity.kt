package com.troxcryptocoin

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.troxcryptocoin.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityLoginBinding.inflate(layoutInflater)
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
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT

      binding.tvSignup.setOnClickListener {
          val intent = Intent(this, SignUpActivity::class.java)
          startActivity(intent)
          finish()
      }
        binding.cvLogin.setOnClickListener {
            if(((binding.etEmail.text.toString()!= "") && (binding.etPassword.text.toString()!=""))){
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Snackbar.make(
                    binding.root,
                    "Fill Details Properly !", Snackbar.LENGTH_LONG)
                    .setAction("ok") {
                        // Responds to click on the action
                    }
                    .setTextColor(resources.getColor(R.color.white))
                    .setBackgroundTint(resources.getColor(R.color.red))
                    .setActionTextColor(resources.getColor(R.color.white))
                    .show()

            }
          
        }
    }
    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }
}