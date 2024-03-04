package com.appadore.db

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.appadore.data.model.ResponseItem

@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( data: ResponseItem)

    @Query("SELECT * FROM groupTable")
    fun getPost(): DataSource.Factory<Int, ResponseItem>

    @Query("SELECT * FROM groupTable Where id=:id")
    fun getParticularData(id: Int): LiveData<List<ResponseItem>>

    @Query("UPDATE groupTable SET name=:title,bio=:body WHERE id=:id")
    fun updateData (title: String, body: String,id: Int)

//    @Query("DELETE FROM groupTable WHERE id=:id")
//    fun delete (id: Int)
}
