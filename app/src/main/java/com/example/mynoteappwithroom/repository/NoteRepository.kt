package com.example.mynoteappwithroom.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.mynoteappwithroom.database.Note
import com.example.mynoteappwithroom.database.NoteDao
import com.example.mynoteappwithroom.database.NoteRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NoteRepository(application: Application) {
    private val mNotesDao: NoteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()


    /**
     * Kelas NoteRepository mendapatkan akses ke database dan mendapatkan referensi ke NoteDao yang
     * digunakan untuk melakukan operasi terhadap data Note
     */
    init {
        val db = NoteRoomDatabase.getDatabase(application)
        mNotesDao = db.noteDao()
    }

    /**
     * Mengembalikan LiveData dari daftar Note yang ada dalam database
     */
    fun getAllNotes(): LiveData<List<Note>> = mNotesDao.getAllNotes()

    /**
     * Menyimpan sebuah Note ke dalam database. Fungsi ini memanfaatkan executorService agar operasi
     * penyimpanan berjalan di luar thread utama agar tidak menghambat interaksi pengguna dengan UI
     */
    fun insert(note: Note) {
        executorService.execute { mNotesDao.insert(note) }
    }

    /**
     * Menghapus sebuah Note dari database
     */
    fun delete(note: Note) {
        executorService.execute { mNotesDao.delete(note) }
    }

    /**
     * Memperbarui sebuah Note yang telah ada dalam database
     */
    fun update(note: Note) {
        executorService.execute { mNotesDao.update(note) }
    }

}