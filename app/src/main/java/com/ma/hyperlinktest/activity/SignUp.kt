package com.ma.hyperlinktest.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ma.hyperlinktest.R
import com.ma.hyperlinktest.db.DatabaseHelper
import com.ma.hyperlinktest.db.entity.RegistrationItem
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.util.regex.Pattern

class SignUp : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        btnSignUp.setOnClickListener(this)
        back.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.back -> finish()
            R.id.btnSignUp -> {
                if (validateUser()) {
                    val item: RegistrationItem = RegistrationItem()
                    item.name = edtName.text.toString().trim()
                    item.phno = edtPhno.text.toString().trim()
                    item.email = edtEmail.text.toString().trim()
                    item.password = edtPassword.text.toString().trim()
                    item.adds = edtAddress.text.toString().trim()

                    Observable.fromCallable {
                        DatabaseHelper.getDatabase(this).getRegistrationDao().registerUser(item)
                    }.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(object : Observer<Unit> {
                            override fun onComplete() {

                            }

                            override fun onSubscribe(d: Disposable) {

                            }

                            override fun onNext(t: Unit) {
                                Toast.makeText(
                                    this@SignUp,
                                    "Registered Successfully",
                                    Toast.LENGTH_SHORT
                                ).show()
                                startActivity(Intent(this@SignUp, Otpactivity::class.java))
                            }

                            override fun onError(e: Throwable) {
                                Log.e("error", e.message)
                            }

                        })
                }
            }
        }
    }

    private fun validateUser(): Boolean {
        val isValid: Boolean
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        if (edtName.text.isNullOrEmpty() || edtName.text.toString().length < 2) {
            makeToast("Please enter valid name")
            isValid = false
        } else if (edtPhno.text.isNullOrEmpty() || edtPhno.text.toString().length < 10 && edtPhno.text.toString().length > 16) {
            makeToast("Please enter valid number")
            isValid = false
        } else if (edtEmail.text.isNullOrEmpty() || !pattern.matcher(edtEmail.text.toString())
                .matches()
        ) {
            makeToast("Please enter valid email")
            isValid = false
        } else if (edtPassword.text.isNullOrEmpty() || edtPassword.text.toString().length < 3) {
            makeToast("Please enter valid password")
            isValid = false
        } else if (edtAddress.text.isNullOrEmpty() || edtAddress.text.toString().length < 5) {
            makeToast("Please enter valid address")
            isValid = false
        } else if (!chkTerms.isChecked) {
            makeToast("Please agreed to terms and condition")
            isValid = false
        } else
            isValid = true
        return isValid
    }

    private fun makeToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}