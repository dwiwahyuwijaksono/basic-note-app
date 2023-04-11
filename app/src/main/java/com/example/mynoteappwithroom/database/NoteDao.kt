package com.example.mynoteappwithroom.database

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Interface DAO(Data Access Object) untuk mengeksekusi quiring
 */
@Dao
interface NoteDao {

    /**
     * Annotation insert untuk menambahkan data baru kedalam tabel
     * Opsi onConflict menentukan bagaimana room bertindak ketika terdapat konflik pada saat memasukkan data baru
     * Opsi IGNORE dugunakan untuk mengabaikan masukkan data yang memiliki nilai primary key yg sama dg data
     * yang telah ada pada tabel
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: Note)

    /**
     * Annotation update untuk mengupdate data pada tabel
     */
    @Update
    fun update(note: Note)

    /**
     * Annotation delete untuk menghapus data pada tabel
     */
    @Delete
    fun delete(note: Note)

    /**
     * Annotation query digunakan untuk melakukan query(memperoleh informasi tertentu) pada tabel
     * Query yang dilakukan adalah memilih seluruh data pada tabel dan mengurutkan id secara ascending(dari kecil ke besar)
     * LiveData digunakan untuk mengamati setiap perubahan yang terjadi pada data dan
     * secara otomatis akan memberitahukan observer ketika data berubah
     */
    @Query("SELECT * from note ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<Note>>
}