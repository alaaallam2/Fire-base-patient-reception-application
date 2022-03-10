package com.example.myapplication.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.DetailsActivity
import com.example.myapplication.R
import com.example.myapplication.model.Teacher
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.StorageTask
import com.orhanobut.dialogplus.DialogPlus
import com.orhanobut.dialogplus.ViewHolder
import java.util.*


class ListAdapter (var mContext:Context,var teacherList:List<Teacher>):

    RecyclerView.Adapter<ListAdapter.ListViewHolder>() {
    inner class ListViewHolder(var v: View) : RecyclerView.ViewHolder(v) {
        var recview: RecyclerView? = null
        var fb: FloatingActionButton? = null
        var nameT = v.findViewById<TextView>(R.id.nameTextView)
        var edit = v.findViewById<ImageView>(R.id.edit)
        var update1 = v.findViewById<ImageView>(R.id.update1)
        var image = v.findViewById<ImageView>(R.id.mMenu)
        val purl = v.findViewById<EditText>(R.id.usercomment3)
        val name = v.findViewById<EditText>(R.id.nameEditText)
        val course = v.findViewById<EditText>(R.id.usercomment)
        val usercommentd = v.findViewById<EditText>(R.id.usercommentDetailTextView)
        val usercomment2d = v.findViewById<EditText>(R.id.usercomment2DetailTextView)
        val usercomment3d= v.findViewById<EditText>(R.id.usercomment3DetailTextView)


    }
    private var mDatabaseRef: DatabaseReference? = null
    private var mUploadTask: StorageTask<*>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        var infalter = LayoutInflater.from(parent.context)
        var v = infalter.inflate(R.layout.row_item, parent, false)
        return ListViewHolder(v)

    }


    override fun getItemCount(): Int = teacherList.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var newList = teacherList[position]
        holder.nameT.text = newList.name + " " + newList.lastname

        holder.edit.setOnClickListener {

            deleteInfo(newList)
        }
        holder.update1.setOnClickListener {
            val dialogPlus = DialogPlus.newDialog(holder.image.context)
                .setContentHolder(ViewHolder(R.layout.activity_update))
                .setExpanded(true, 1100)
                .create()
            val myview = dialogPlus.holderView
            val name = myview.findViewById<EditText>(R.id.nameEditTextup)
            val lastname = myview.findViewById<EditText>(R.id.create_title1up)
            val country = myview.findViewById<EditText>(R.id.create_priorityup)
            val nationalid = myview.findViewById<EditText>(R.id.create_priority1up)
            val usercomment = myview.findViewById<EditText>(R.id.usercommentup)
            val usercomment2 = myview.findViewById<EditText>(R.id.usercomment2up)
            val usercomment1 = myview.findViewById<EditText>(R.id.usercomment1up)
            val usercomment3 = myview.findViewById<EditText>(R.id.usercomment3up)
            val date = myview.findViewById<EditText>(R.id.dateup)
            val anycomment = myview.findViewById<EditText>(R.id.descriptionEditTextup)
            val submit = myview.findViewById<Button>(R.id.updateBtn)
            name.setText(newList.name)
            lastname.setText(newList.lastname)
            country.setText(newList.country)
            nationalid.setText(newList.national)
            usercomment.setText(newList.usercoment)
            usercomment2.setText(newList.usercomment2)
           usercomment1.setText(newList.usercomment1)
            date.setText(newList.date)
            usercomment3.setText(newList.usercomment3)
            anycomment.setText(newList.description)
            dialogPlus.show()
            submit.setOnClickListener {
                val map: MutableMap<String, Any> = HashMap()
                map["name"] = name.text.toString()
                map["lastname"] = lastname.text.toString()
                map["country"] = country.text.toString()
                map["national"] = nationalid.text.toString()
                map["usercoment"] = usercomment.text.toString()
                map["date"] = date.text.toString()
                map["usercomment2"] = usercomment2.text.toString()
                map["usercomment1"] = usercomment1.text.toString()
                map["usercomment3"] = usercomment3.text.toString()
                map["description"] = anycomment.text.toString()
                FirebaseDatabase.getInstance().reference.child("patient_uploads")
                    .child(newList.key!!).updateChildren(map)
                    .addOnSuccessListener { dialogPlus.dismiss() }
                    .addOnFailureListener { dialogPlus.dismiss() }
                }
            }

            holder.image.setOnClickListener {

                val name = newList.name
                val descrip = newList.description
                val imgUri = newList.imageUrl
                val country = newList.country
                val lastname = newList.lastname
                val nationalid = newList.national
                val usercomment = newList.usercoment
                val usercomment2 = newList.usercomment2
                val usercomment1 = newList.usercomment1
                val usercomment3 = newList.usercomment3
                val date = newList.date
                val mIntent = Intent(mContext, DetailsActivity::class.java)
                mIntent.putExtra("NAMET", name)
                mIntent.putExtra("DESCRIT", descrip)
                mIntent.putExtra("country", country)
                mIntent.putExtra("date", date)
                mIntent.putExtra("LASTNAME", lastname)
                mIntent.putExtra("NATIONALID", nationalid)
                mIntent.putExtra("usercoment", usercomment)
                mIntent.putExtra("usercomment2", usercomment2)
                mIntent.putExtra("usercomment1", usercomment1)
                mIntent.putExtra("usercomment3", usercomment3)
                mIntent.putExtra("IMGURI", imgUri)
                mContext.startActivity(mIntent)
            }

        }



        private fun deleteInfo(employee: Teacher) {
            mDatabaseRef = FirebaseDatabase.getInstance().getReference("patient_uploads")
            mDatabaseRef!!.child(employee.key!!).removeValue()
            Toast.makeText(mContext, "Deleted !", Toast.LENGTH_LONG).show()
        }

    }





