package com.example.knowyourproduct.Model



class uploadPost {
    var postUrl : String ?= null
    var caption : String ?= null
    constructor()
    constructor(postUrl: String?,caption:String?){
        this.postUrl=postUrl
        this.caption = caption
    }
}