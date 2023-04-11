package com.example.mynoteappwithroom.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Annotation database untuk mendefinisikan bahwa class ini merupakan database room pada aplikasi ini
 * Parameter entities digunakan untuk menentukan tabel"  yang akan dibuat pada database yaitu
 * tabel Note akan dibuat dalam database
 * Parameter version digunakan untuk menentukan versi dari database
 */
@Database(entities = [Note::class], version = 1)
abstract class NoteRoomDatabase: RoomDatabase() {

    abstract fun noteDao():NoteDao

    companion object {

        /**
         * Membuat variabel global berupa singleton untuk Dao yang nanti akan dipanggil di class repository
         * Annotation volatile menandakan bahwa nilai INSTANCE akan selalu diperbarui ketika ada perubahan
         */
        @Volatile
        private var INSTANCE: NoteRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): NoteRoomDatabase {
            if (INSTANCE == null) {
                synchronized(NoteRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        NoteRoomDatabase::class.java, "note_database")
                        .build()
                }
            }
            return INSTANCE as NoteRoomDatabase
        }
    }

}