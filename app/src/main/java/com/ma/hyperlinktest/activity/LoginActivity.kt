package com.ma.hyperlinktest.activity

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ma.hyperlinktest.R
import com.ma.hyperlinktest.db.DatabaseHelper
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.edtEmail
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        labelCreateAccount.setOnClickListener(this)
        btnLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.labelCreateAccount -> {
                startActivity(Intent(this, SignUp::class.java))
            }
            R.id.btnLogin -> {
                if(checkValidation())
                {
                    DatabaseHelper.getDatabase(this).getRegistrationDao().getRegistrationData().observe(this, Observer {
                        if(it.size>0)
                        {
                            for (item in it)
                            {
                                if(item.email.equals(edtEmail.text.toString().trim()) && item.password.equals(edtPassWord.text.toString().trim())) {
                                    Toast.makeText(this,"login successfully",Toast.LENGTH_SHORT).show()
                                    startActivity(Intent(this,
                                        MainActivity::class.java))
                                }
                                else
                                    Toast.makeText(this,"Invalid Credential",Toast.LENGTH_SHORT).show()
                            }
                        }
                        else
                            Toast.makeText(this,"Please Register",Toast.LENGTH_SHORT).show()
                    })
                }
            }
        }
    }

    private fun checkValidation():Boolean {
        val isValid: Boolean
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        if (edtEmail.text.isNullOrEmpty() || !pattern.matcher(edtEmail.text.toString()).matches()) {
            Toast.makeText(this, "Please enter valid email", Toast.LENGTH_SHORT).show()
            isValid = false
        } else if (edtPassWord.text.isNullOrEmpty() || edtPassWord.text.toString().length < 3) {
            Toast.makeText(this, "Please enter valid password", Toast.LENGTH_SHORT).show()
            isValid = false
        } else
            isValid = true

        return isValid
    }

}