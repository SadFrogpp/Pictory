package com.example.rxpictory.database

import android.arch.persistence.room.*
import com.example.rxpictory.database.Auth

@Dao
interface AuthDao {
    @Query("SELECT * FROM auth")
    fun getAuth(): Auth

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg auth: Auth)

    @Delete
    fun delete(vararg auth: Auth)

}