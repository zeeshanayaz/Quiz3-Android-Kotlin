package com.zeeshan.quiz3.model

class User(var name : String, var email : String /*Primary Key*/, var password : String) {
    override fun toString(): String {
        return "User(name='$name', email='$email', password='$password')"
    }

    companion object {
        var userLogedEmail : String? = null
    }
}