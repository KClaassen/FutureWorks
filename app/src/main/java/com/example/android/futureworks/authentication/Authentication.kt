package com.example.android.futureworks.authentication

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android.futureworks.MainActivity
import com.example.android.futureworks.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.custom_login_screen.*

class Authentication : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_login_screen)

        tv_register.setOnClickListener {
            startActivity(Intent(this, Registry::class.java))
        }

        login_btn.setOnClickListener {
            when {
                TextUtils.isEmpty(et_login_email.text.toString().trim{ it <= ' '  }) -> {
                    Toast.makeText(
                        this, "Please enter your email address", Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(et_login_password.text.toString().trim{ it <= ' '  }) -> {
                    Toast.makeText(
                        this, "Please enter password", Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {

                    val email: String = et_login_email.text.toString().trim { it <= ' ' }
                    val password: String = et_login_password.text.toString().trim { it <= ' ' }

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            // If registration is successful
                            if (task.isSuccessful) {

                                // Send user to ArticleMain fragment
                                val intent = Intent(this, MainActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra("user_id", FirebaseAuth.getInstance().currentUser!!.uid)
                                intent.putExtra("email_id", email)
                                startActivity(intent)
                                finish()
                            } else {
                                // If registration is not successful
                                Toast.makeText(
                                    this, task.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }
            }
        }

        // Check if user is currently logged in
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            // Send user to ArticleMain fragment
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(
                this, "Pleas login", Toast.LENGTH_SHORT
            ).show()
        }
    }

}