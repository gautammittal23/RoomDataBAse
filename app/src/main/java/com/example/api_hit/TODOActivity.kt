package com.example.api_hit

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_todo.*
import java.util.*
import kotlin.collections.ArrayList


class TODOActivity : AppCompatActivity() {

   lateinit var db:DataBaseClass

    private var mContext: Context? = null

    var mRelativeLayout: RelativeLayout? = null
    private var mButtonAdd: Button? = null


    private var mAdapter: RecyclerView.Adapter<*>? = null
    private val mRandom = Random()


    private var shared = "key"

    var Title = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)
        mContext = getApplicationContext()
        db=DataBaseClass.getInstance(this)


        // refreshing recycler view

        recycler.apply {
            layoutManager = LinearLayoutManager(mContext);
            adapter = TODOAdapter(ArrayList())
        }
        getData()








        var sharedPreferences: SharedPreferences =
            this.getSharedPreferences(shared, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        floatingActionButton.setOnClickListener(View.OnClickListener
        {
            var intent = Intent(this, AddToDoItem::class.java)
            startActivityForResult(intent, 1)
        })





//        imageButton1.setOnClickListener {
//            var intent=Intent(this,AddToDoItem::class.java)
//            startActivityForResult(intent,2)
//        }


        btnlogout.setOnClickListener(View.OnClickListener
        {
            editor.clear()
            editor.commit()
            intent = Intent(this, Login::class.java)
            startActivity(intent);
            finish()
        })



    }

    fun getData()
    {
        Thread{

            var data:List<TodoData> =db.Dao().getAll()
            runOnUiThread{
                (recycler.adapter as TODOAdapter).onUpdateAll(data)
            }

        }.start()
    }

    override fun onBackPressed()
    {
        var builder = AlertDialog.Builder(this)
        builder.setTitle("Are you sure you want to close?")
        builder.setPositiveButton("YES")
        { dialogInterface, Which ->
            finish()
            System.exit(0)
            Toast.makeText(this, "Thanks for Visting", Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("NO")
        { dialogInterface, Which ->
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 1) {
            if (data != null) {

                val tep_title = data.getStringExtra("key").toString()
                var tep_desc  = data.getStringExtra("textdesc".toString())

                Toast.makeText(this,tep_title,Toast.LENGTH_LONG).show()
                var position = 0

                Title.add(position, tep_title+tep_desc)



                // val Titlelabel = "" + mRandom.nextInt(100)

//
//                Title.add(position, tep_title)


                Thread{
                    var todoData = TodoData()
                    todoData.title=tep_title
                    todoData.description=tep_desc

                    db.Dao().insert(todoData)
                    runOnUiThread{
                        (recycler.adapter as TODOAdapter).onUpdateAll(db.Dao().getAll())
                    }

                }.start()


                mAdapter?.notifyItemInserted(position)

            }
        }

        if (resultCode == Activity.RESULT_OK && requestCode == 2)
        {
            Thread{
                var data=db.Dao().getAll()
                runOnUiThread {
                    (recycler.adapter as TODOAdapter).onUpdateAll(data)
                }
            }.start()

        }
    }
}
