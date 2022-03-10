package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.model.Teacher
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask
import kotlinx.android.synthetic.main.activity_update.*
import kotlinx.android.synthetic.main.activity_upload.*
import kotlinx.android.synthetic.main.activity_upload.progressBar
import kotlinx.android.synthetic.main.activity_upload.usercomment3

class UploadActivity : AppCompatActivity() {

    private var mImageUri : Uri? = null
    private var mImageUri2 : Uri? = null
    private var mStorageRef: StorageReference? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mUploadTask: StorageTask<*>? = null

    private val PICK_IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)
        // Reference the id's of your spinner and text

        // Reference the id's of your spinner and text
        val spinnerTextSize = findViewById<Spinner>(R.id.spinner)
        // This Adapter Settings Enable Your Spinner to work as a spinner

        // This Adapter Settings Enable Your Spinner to work as a spinner
        spinnerTextSize.setOnItemSelectedListener(this)
        val textSizes = resources.getStringArray(R.array.font_sizes)
        val adapter: ArrayAdapter<*> = ArrayAdapter<Any?>(
            this, android.R.layout.simple_spinner_item,
            textSizes
        )
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spinnerTextSize.setAdapter(adapter)

        /**set data*/

        mStorageRef = FirebaseStorage.getInstance().getReference("patient_uploads")
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("patient_uploads")

        button_choose_image.setOnClickListener { openFileChoose() }

        upLoadBtn.setOnClickListener {
            if (mUploadTask != null && mUploadTask!!.isInProgress){

                Toast.makeText(this@UploadActivity,
                    "An Upload is Still in Progress",
                    Toast.LENGTH_SHORT).show()
                uploadFile()
            }
            else{
                uploadFile()
            }

        }




    }

    private fun openFileChoose() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null)

        {

            mImageUri = data.data
            chooseImageView.setImageURI(mImageUri)


        }
    }



    private fun uploadFile() {



            progressBar.visibility = View.VISIBLE
            progressBar.isIndeterminate = true


                    val handler = Handler()
                    handler.postDelayed({
                        progressBar.visibility = View.VISIBLE
                        progressBar.isIndeterminate = false
                        progressBar.progress = 0
                    }, 500)
                    Toast.makeText(
                        this@UploadActivity,
                        "patient data Upload successful",
                        Toast.LENGTH_LONG
                    )
                        .show()
                    val upload = Teacher(
                        name = nameEditText!!.text.toString().trim { it <= ' ' },
                        country = create_priority!!.text.toString().trim { it <= ' ' },
                        lastname= create_title1!!.text.toString().trim { it <= ' ' },
                        national = create_priority1!!.text.toString().trim { it <= ' ' },
                        usercoment = usercomment!!.text.toString().trim { it <= ' ' },
                        usercomment2 = usercomment2!!.text.toString().trim { it <= ' ' },
                        usercomment1 = usercomment1!!.text.toString().trim { it <= ' ' },
                        usercomment3 = usercomment3!!.text.toString().trim { it <= ' ' },
                        date = date!!.text.toString().trim { it <= ' ' },
                        imageUrl = mImageUri.toString(),
                        imageUrl2 = mImageUri2.toString(),
                        description =  descriptionEditText!!.text.toString().trim { it <= ' ' }
                    )
                    val uploadId = mDatabaseRef!!.push().key
                    mDatabaseRef!!.child((uploadId)!!).setValue(upload)
                    progressBar.visibility = View.INVISIBLE
                    openImagesActivity()
                }


    private fun  openImagesActivity() {
        startActivity(Intent(this@UploadActivity, MainActivity::class.java))
    }



private fun Spinner.setOnItemSelectedListener(registration: UploadActivity ) {

}}


