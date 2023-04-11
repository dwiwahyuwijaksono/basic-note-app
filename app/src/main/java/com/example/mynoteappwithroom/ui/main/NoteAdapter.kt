package com.example.mynoteappwithroom.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mynoteappwithroom.R
import com.example.mynoteappwithroom.database.Note
import com.example.mynoteappwithroom.databinding.ItemNoteBinding
import com.example.mynoteappwithroom.helper.NoteDiffCallback
import com.example.mynoteappwithroom.ui.insert.NoteAddUpdateActivity

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private val listNotes = ArrayList<Note>()
    fun setListNotes(listNotes: List<Note>) {
        val diffCallback = NoteDiffCallback(this.listNotes, listNotes)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listNotes.clear()
        this.listNotes.addAll(listNotes)
        diffResult.dispatchUpdatesTo(this)
    }

   inner class NoteViewHolder(private val binding: ItemNoteBinding): RecyclerView.ViewHolder(binding.root){
       fun bind(note: Note) {
           with(binding) {
               tvItemTitle.text = note.title
               tvItemDescription.text = note.description
               tvItemDate.text = note.date
               cvItemNote.setOnClickListener {
                   val intent = Intent(it.context, NoteAddUpdateActivity::class.java)
                   intent.putExtra(NoteAddUpdateActivity.EXTRA_NOTE, note)
                   it.context.startActivity(intent)
               }
           }
       }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(listNotes[position])
    }

    override fun getItemCount(): Int {
        return listNotes.size
    }
}