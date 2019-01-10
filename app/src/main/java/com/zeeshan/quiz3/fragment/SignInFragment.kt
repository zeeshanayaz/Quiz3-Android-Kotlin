package com.zeeshan.quiz3.fragment


import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.gson.Gson

import com.zeeshan.quiz3.R
import com.zeeshan.quiz3.model.User

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SignInFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_sign_in, container, false)

        submitButton(view)

        return view
    }

    private fun submitButton(view: View) {
        val userEmail = view.findViewById<EditText>(R.id.email_textview)
        val userPassword = view.findViewById<EditText>(R.id.password_textview)
        val logInButton = view.findViewById<Button>(R.id.logIn_btn)

        logInButton.setOnClickListener {
            if(!userEmail.text.trim().isEmpty()){
                if(!userPassword.text.trim().isEmpty()){
                    LogInUser(userEmail.text.trim().toString(), userPassword.text.trim().toString())
                }
                else{
                    userPassword.setError("Invalid Password")
                }
            }
            else{
                userEmail.setError("Invalid Emial")
            }
        }
    }

    private fun LogInUser(email: String, password: String) {
        val sharedPreferences = activity?.getSharedPreferences("App", 0)
        val gson = Gson()
        val userStringSet = sharedPreferences?.getStringSet("users", null)

        if (userStringSet != null){
            userStringSet.forEach {
                val userObj = gson.fromJson(it, User::class.java)
                if(userObj.email.equals(email) && userObj.password.equals(password))
                {
//                    userObj.setValueOfLogged(email)
//                    User.userLogedEmail = email
                    Toast.makeText(activity, "User Log In successful", Toast.LENGTH_SHORT).show()
                    return
                }
            }
            Toast.makeText(activity, "User does not exist....", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(activity, "User does not exist....", Toast.LENGTH_SHORT).show()
        }
    }


}
