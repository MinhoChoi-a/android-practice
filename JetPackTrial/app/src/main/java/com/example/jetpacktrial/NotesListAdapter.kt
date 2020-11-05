package com.example.jetpacktrial

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpacktrial.data.NoteEntity
import com.example.jetpacktrial.databinding.ListItemBinding

class NotesListAdapter(private val notesList: List<NoteEntity>):
    RecyclerView.Adapter<NotesListAdapter.ViewHolder>(){
    //extend the class itself from a class names recyclerview.adapter


    //inner class gives the ability to reference the private value of its parent
    //reference itemView, type is view from the android.view package
    inner class ViewHolder(itemView: View):
        //extend
            RecyclerView.ViewHolder(itemView){
            //reference all the views that are in the XML layout
            val binding = ListItemBinding.bind(itemView)

    }

    //each time a new list item is generated, get those reference to the view and then return the ViewHolder object
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val note = notesList[position]

        //with block
        //holder: references the instnace of the ViewHolder for the current row and binidng is it's public property
        with(holder.binding) {
        noteText.text = note.text
        }

    }

    override fun getItemCount() = notesList.size



}