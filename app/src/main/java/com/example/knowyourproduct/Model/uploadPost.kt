package com.example.knowyourproduct.Model

import android.provider.ContactsContract.CommonDataKinds.Email


class uploadPost {
    var postUrl : String ?= null
    var email : String ?= null
    var caption : String ?= null
    var name : String? = ""
    var time : String? = null
    var profilepic :String? = null
    constructor()
    constructor(postUrl: String?, caption:String?, name:String?, time: String?, profilepic:String?,email: String?){
        this.postUrl=postUrl
        this.caption = caption
        this.time = time
        this.name = name
        this.profilepic= profilepic
        this.email = email
    }
}