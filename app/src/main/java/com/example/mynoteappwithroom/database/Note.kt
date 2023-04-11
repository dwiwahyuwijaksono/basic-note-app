package com.example.mynoteappwithroom.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity // Annotation ini digunakan untuk merepresentasikan bahwa class ini adalah Table dalam database
data class Note (

    /** Annotation tersebut merepresentasikan bahwa atribut ini sebuah primaryKey pada database
     * nilai autoGenerate = true untuk menggenerate key secara otomatis jika ada penambhaan row
     * Annotation ColumnInfo untuk memberi nama kolom pada table yaitu "id" sesuai dengan atribut pada class entity
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,


    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "description")
    var description: String? = null,

    @ColumnInfo(name = "date")
    var date: String? = null
): Parcelable