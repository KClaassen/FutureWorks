package com.example.android.futureworks.authentication

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android.futureworks.MainActivity
import com.example.android.futureworks.R
import com.example.android.futureworks.articlemain.ArticleMainFragment
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.custom_login_screen.*
import kotlinx.android.synthetic.main.custom_registry_screen.*

class Registry : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_registry_screen)

        tv_login.setOnClickListener {
            onBackPressed()
            }

        register_btn.setOnClickListener {
            when {
                TextUtils.isEmpty(et_register_email.text.toString().trim{ it <= ' '  }) -> {
                    Toast.makeText(
                        this, "Please enter your email address", Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(et_register_password.text.toString().trim{ it <= ' '  }) -> {
                    Toast.makeText(
                        this, "Please enter password", Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {

                    val email: String = et_register_email.text.toString().trim { it <= ' ' }
                    val password: String = et_register_password.text.toString().trim { it <= ' ' }

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            // If registration is successful
                            if (task.isSuccessful) {

                                //Firebase registered user
                                val firebaseUser: FirebaseUser = task.result!!.user!!

                                Toast.makeText(
                                    this, "You're registered successfully.", Toast.LENGTH_SHORT
                                ).show()
                                // Send new registered user directly through to ArticleMain fragment
                                val intent = Intent(this, MainActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra("user_id", firebaseUser.uid)
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
    }
}