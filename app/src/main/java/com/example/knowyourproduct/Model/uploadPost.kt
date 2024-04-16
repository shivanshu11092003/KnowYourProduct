package com.example.knowyourproduct.Model



class uploadPost {
    var postUrl : String ?= null
    var caption : String ?= null
    var name : String? = ""
    var time : String? = ""
    var profilepic :String? = null
    constructor()
    constructor(postUrl: String?, caption:String?, name:String?, time: String, profilepic:String?){
        this.postUrl=postUrl
        this.caption = caption
        this.time = time
        this.name = name
        this.profilepic= profilepic
    }
}