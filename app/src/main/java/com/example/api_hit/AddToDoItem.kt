package com.example.api_hit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_add_to_do_item.*

class AddToDoItem : AppCompatActivity() {

    lateinit var todoData: TodoData

    var textTitle:String=""
    var textDis:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_do_item)

        var data=intent.getSerializableExtra("data")

        if(data!=null)
        {
            todoData=data as TodoData
        }


        var from=intent.getStringExtra("from")

        if(from.equals("edit",true))
        {
            edtTitle.setText(todoData.title)
            edtDescription.setText(todoData.description)

        }

        btnCheck.setOnClickListener {


            textTitle=edtTitle.text.toString()
            textDis=edtDescription.text.toString()

            if (textDis!!.isEmpty()&& textTitle!!.isEmpty()) {
                Snackbar.make(it, "These fields cannot be empty", Snackbar.LENGTH_LONG).show()
            }
            else if(from.equals("edit",true))
                {

                    todoData.title=textTitle
                    todoData.description=textDis
                    var db=DataBaseClass.getInstance(this)

                    Thread{
                        var g=db.Dao().update(todoData)

                        runOnUiThread{

                        }
                    }.start()
                    setResult(Activity.RESULT_OK,intent)
                    finish()
                }


            else
            {
                addtodatabase()
            }

        }

    }

    fun addtodatabase()
    {
        intent= Intent()
        intent.putExtra("key","Title is : "+textTitle+"\n")
        intent.putExtra("textdesc","Desc of text : "+textDis)
        setResult(Activity.RESULT_OK,intent);
        finish()

    }
}
