package com.ma.hyperlinktest.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "registration")
class RegistrationItem()
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var id: Int=0
    @ColumnInfo(name="name")
    var name: String=""
    @ColumnInfo(name="phno")
    var phno: String=""
    @ColumnInfo(name="email")
    var email: String=""
    @ColumnInfo(name="password")
    var password: String=""
    @ColumnInfo(name="adds")
    var adds: String=""
}