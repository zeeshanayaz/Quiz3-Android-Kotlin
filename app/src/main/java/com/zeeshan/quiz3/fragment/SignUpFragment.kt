package com.zeeshan.quiz3.fragment

import android.content.Context
import android.net.Uri
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
 * Activities that contain this fragment must implement the
 * [SignUpFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SignUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class SignUpFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_sign_up, container, false)

        submitButton(view)

        return view
    }

    private fun submitButton(view: View) {
        val userName = view.findViewById<EditText>(R.id.user_name_textview)
        val userEmail = view.findViewById<EditText>(R.id.email_textview)
        val userPassword = view.findViewById<EditText>(R.id.password_textview)
        val signUpButton = view.findViewById<Button>(R.id.signUp_btn)

        signUpButton.setOnClickListener {
            if (!userName.text.trim().isEmpty()){
                if (!userEmail.text.trim().isEmpty()){
                    if (!userPassword.text.trim().isEmpty()){
                        SignUpUser(User(userName.text.toString(), userEmail.text.toString(), userPassword.text.toString()))
                        userName.setText("")
                        userEmail.setText("")
                        userPassword.setText("")
                    }
                    else{
                        userPassword.setError("Invalid Password")
                    }
                }
                else{
                    userEmail.setError("Invalid Email")
                }
            }
            else{
                userName.setError("Invalid User Name")
            }
        }

    }

    private fun SignUpUser(user: User) {
        val sharedPreferences = activity?.getSharedPreferences("App", 0)
        val gson = Gson()
        val editor = sharedPreferences?.edit()
        val userStringObj = gson.toJson(user)

        val userStringSet = sharedPreferences?.getStringSet("users",null)
        if (userStringSet!=null){
            userStringSet.forEach{
                val userObj = gson.fromJson(it, User::class.java)
                if (user.email.equals(userObj.email)){
                    Toast.makeText(activity, "User Already Exist!!", Toast.LENGTH_SHORT).show()
                    return
                }
            }
            userStringSet.add(userStringObj)
            editor?.putStringSet("users", userStringSet)
            editor?.apply()
            Toast.makeText(activity, "User Added Successfully", Toast.LENGTH_SHORT).show()
        }
        else{
            val hashSet = HashSet<String>()
            hashSet.add(userStringObj)
            editor?.putStringSet("users", userStringSet)
            editor?.apply()
            Toast.makeText(activity, "User Added Successfully", Toast.LENGTH_SHORT).show()
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SignUpFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignUpFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
