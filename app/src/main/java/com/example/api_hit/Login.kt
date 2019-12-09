package com.example.api_hit

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {


    private var shared="key"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        var sharedPreferences: SharedPreferences =this.getSharedPreferences(shared, Context.MODE_PRIVATE);
        var sharedpin = sharedPreferences.getString("email","defaultname")
        var sharedpin2 = sharedPreferences.getString("password","defaultname")


        if(!sharedpin.equals("defaultname") && !sharedpin2.equals("defaultname"))
        {
            Toast.makeText(this,"Welcome back", Toast.LENGTH_LONG).show()
            intent= Intent(this,TODOActivity()::class.java)
            startActivity(intent)
            finish()
        }


        Glide.with(this).load(R.drawable.bg_new).into(img)


        btnlogin.setOnClickListener({

            val email=edttxtemail.text.toString()
            val password=edttxtpassword.text.toString()
            var emailcheck:Boolean=android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

            if(email!="" && password!="" && emailcheck)
            {

                val editor:SharedPreferences.Editor =  sharedPreferences.edit()

                editor.putString("email",email);
                editor.putString("password",password);


                editor.apply()
                sharedpin=email
                sharedpin2=password

                if(!sharedpin.equals("defaultname") && !sharedpin2.equals("defaultname"))
                {
                    Snackbar.make(it,"Welcome to TODOActivity",Snackbar.LENGTH_LONG).show()
                    intent= Intent(this,TODOActivity::class.java)
                    startActivity(intent)
                    finish()
                }

            }
            else
            {
                Snackbar.make(it,"Check your Fields again",Snackbar.LENGTH_LONG).show()
            }

        })


    }
}
