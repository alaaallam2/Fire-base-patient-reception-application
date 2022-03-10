package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.uitel.loadImage
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    private var mDatabaseRef: DatabaseReference? = null
    private var mStorageRef: StorageReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val intss = intent
        var nameT = intss.getStringExtra("NAMET")
        var desT = intss.getStringExtra("DESCRIT")
        var imgT = intss.getStringExtra("IMGURI")
        var imgT2 = intss.getStringExtra("IMGURI2")
        var countryd = intss.getStringExtra("country")
        var lastnameed = intss.getStringExtra("LASTNAME")
        var nationalid = intss.getStringExtra("NATIONALID")
        var usercommentd = intss.getStringExtra("usercoment")
        var usercomment2d = intss.getStringExtra("usercomment2")
        var usercomment1d = intss.getStringExtra("usercomment1")
        var usercomment3d= intss.getStringExtra("usercomment3")
        var dated= intss.getStringExtra("date")
        var result=nameT+lastnameed
        nameDetailTextView.text = result
        countryDetailTextView.text = countryd
        nationalidDetailTextView.text=nationalid
        usercommentDetailTextView.text=usercommentd
        dateDetailTextView.text=dated
        usercomment2DetailTextView.text=usercomment2d
        usercomment1DetailTextView.text=usercomment1d
        usercomment3DetailTextView.text=usercomment3d
        descriptionDetailTextView.text = desT
        teacherDetailImageView.loadImage(imgT)

    }
}