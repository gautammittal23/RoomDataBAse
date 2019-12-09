package com.example.api_hit

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.custom_layout_todo.view.*
import java.util.*

class TODOAdapter(list: ArrayList<TodoData>) : RecyclerView.Adapter<TODOAdapter.ViewHolder>() {

    lateinit var mylist: ArrayList<TodoData>
//    var mContext: Context? = null
//    var mrandom: Random = Random()

    init {
        mylist = list
    }


//    fun main (context: Context, list: mylist<String>){
//        mylist = list
//        mcontext = context
//    }


    // public TODOAlistdapter(context: Context , list :mylist<String>)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.custom_layout_todo_new, parent, false)
        val vh: ViewHolder = ViewHolder(v)

        return vh
    }

    override fun getItemCount(): Int {

        return mylist.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

     // var todoData=TodoData()


        mylist[position].apply {

            holder.itemView.txtdescription.setText(title + description)


            // deleting item
            holder.itemView.imageButton.setOnClickListener {
                var db = DataBaseClass.getInstance(holder.itemView.context)
                Thread {
                    //  db.Dao().delete(mylist[position])
                    db.Dao().delete(this)
                }.start()

                deleteitem(this)
            }


            holder.itemView.imageButton1.setOnClickListener{

                var intent=Intent(holder.itemView.context,AddToDoItem::class.java)
                intent.putExtra("data",this)
                intent.putExtra("from","edit")
                (holder.itemView.context as Activity).startActivityForResult(intent,2)
            }
        }
    }



    fun onUpdateAll(data: List<TodoData>)
    {
        mylist.clear()
        mylist.addAll(data)

        notifyDataSetChanged()

    }


    fun edititem(data:TodoData)
    {

        mylist.add(data)

        notifyDataSetChanged()
    }


    fun deleteitem(data: TodoData)
    {
        var position= mylist.indexOf(data)
        mylist.removeAt(position)
        notifyItemRemoved(position)
    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var text1:TextView?=null

        init {
            text1=itemView.txtdescription
        }

    }


}

