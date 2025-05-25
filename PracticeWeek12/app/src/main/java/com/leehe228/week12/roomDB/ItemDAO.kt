package com.leehe228.week12.roomDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun InsertItem(itemEntity: ItemEntity)

    @Update
    suspend fun UpdateItem(itemEntity: ItemEntity)

    @Delete
    suspend fun DeleteItem(itemEntity: ItemEntity)

    @Query("select * from ItemTable")
    fun getAllItems(): Flow<List<ItemEntity>>
}
