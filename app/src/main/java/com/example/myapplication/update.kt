package com.example.myapplication

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.model.Teacher
import com.google.firebase.database.FirebaseDatabase

class update(val mCtx : Context , val layoutId:Int , val employeeList:List<Teacher>)
    :ArrayAdapter<Teacher>(mCtx,layoutId,employeeList){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val inflater = LayoutInflater.from(mCtx)
        val view = inflater.inflate(R.layout.activity_update, null)


        val firstname = view.findViewById<TextView>(R.id.descriptionDetailTextView)
        val lastname = view.findViewById<TextView>(R.id.usercomment3DetailTextView)
        val address = view.findViewById<TextView>(R.id.usercomment1DetailTextView)
        val department = view.findViewById<TextView>(R.id.usercommentDetailTextView)
        val updatebtn = view.findViewById<TextView>(R.id.updateBtn)

        val employee = employeeList[position]

        firstname.text = employee.country
        lastname.text = employee.lastname
        address.text = employee.usercomment1
        department.text = employee.usercomment2

        updatebtn.setOnClickListener {
            updateInfo(employee)
        }


        return view
    }

    private fun updateInfo(employee: Teacher) {

        val builder = AlertDialog.Builder(mCtx)
        builder.setTitle("Update Info")
        val inflater = LayoutInflater.from(mCtx)
        val view = inflater.inflate(R.layout.activity_update, null)

        val firstname = view.findViewById<TextView>(R.id.create_title1up)
        val lastname = view.findViewById<TextView>(R.id.create_priorityup)
        val address = view.findViewById<TextView>(R.id.usercomment1up)
        val department = view.findViewById<TextView>(R.id.usercomment2up)

        firstname.setText(employee.country)
        lastname.setText(employee.lastname)
        address.setText(employee.usercomment1)
        department.setText(employee.usercomment2)

        builder.setView(view)

        builder.setPositiveButton("Update", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {

                val myDatabase = FirebaseDatabase.getInstance().getReference("patient_uploads")

                val firstname1 = firstname.text.toString().trim()
                val lastname2 = lastname.text.toString().trim()
                val address3 = address.text.toString().trim()
                val department4 = department.text.toString().trim()

                if (firstname1.isEmpty()) {
                    firstname.error = "Please enter your firstname"
                    return
                }
                if (lastname2.isEmpty()) {
                    lastname.error = "Please enter your lastname"
                    return
                }
                if (address3.isEmpty()) {
                    address.error = "Please enter your address"
                    return
                }
                if (department4.isEmpty()) {
                    department.error = "Please enter your department"
                    return
                }

                val employee = Teacher(employee.key, firstname1, lastname2, address3, department4)
                myDatabase.child(employee.key!!).setValue(employee)
                Toast.makeText(mCtx, "Updated :) ", Toast.LENGTH_LONG).show()


            }
        })

        builder.setNegativeButton("cancel", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {

            }

        })

        val alert = builder.create()
        alert.show()

    }



}