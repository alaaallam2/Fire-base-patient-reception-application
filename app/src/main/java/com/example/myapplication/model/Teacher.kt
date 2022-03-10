package com.example.myapplication.model

import com.google.firebase.database.Exclude

data class Teacher(
    var id:String?=null,
    var name:String? = null,
    var imageUrl:String? = null,
    var imageUrl2:String? = null,
    var description:String? = null,
    var country:String? = null,
    var lastname:String? = null,
    var national:String? = null,
    var usercoment:String? = null,
    var usercomment2:String? = null,
    var usercomment1:String? = null,
    var usercomment3:String? = null,
    var  delete:String?=null,
    var  date:String?=null,
    @get:Exclude
    @set:Exclude
    var key:String? = null

){
    constructor():this( "","","","","","","","","","","","",""){
        this.name = name
        this.lastname = lastname
        this.usercoment = usercoment

    }


}
