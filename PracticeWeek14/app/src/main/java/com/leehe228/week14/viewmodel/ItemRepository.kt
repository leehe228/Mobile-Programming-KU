package com.leehe228.week14.viewmodel

import android.util.Log
import com.google.android.gms.tasks.Tasks.await
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class ItemRepository(private val table: DatabaseReference) {
    suspend fun InsertItem(itemEntity: ItemEntity) {
//        table
//            .child(itemEntity.itemID.toString())
//            .setValue(itemEntity)
//            .addOnCompleteListener {
//                if (it.isSuccessful) {
//                    Log.i("insert", "success")
//                }
//                else {
//                    Log.i("insert", "failed")
//                }
//            }

        try {
            table.child(itemEntity.itemID.toString()).setValue(itemEntity).await()
            Log.i("insert", "success")
        } catch (e: Exception) {
            Log.e("insert", "failed")
        }
    }

    suspend fun UpdateItemQuantity(itemEntity: ItemEntity) {
        try {
            table.child(itemEntity.itemID.toString()).child("itemQuantity")
                .setValue(itemEntity.itemQuantity).await()
            Log.i("update", "success")
        } catch (e: Exception) {
            Log.e("update", "failed")
        }
    }

    suspend fun DeleteItem(itemEntity: ItemEntity) {
        try {
            table.child(itemEntity.itemID.toString()).removeValue().await()
            Log.i("delete", "success")
        } catch (e: Exception) {
            Log.e("delete", "failed")
        }
    }

    fun getAllItems(): Flow<List<ItemEntity>> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val itemList = snapshot.children.mapNotNull {
                    it.getValue(ItemEntity::class.java)
                }
                trySend(itemList)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }

        table.addValueEventListener(listener)

        awaitClose {
            table.removeEventListener(listener)
        }
    }

    suspend fun getItems(itemName: String): Flow<List<ItemEntity>> = flow {
        try {
            val snapshot =
                table.orderByChild("itemName").startAt(itemName).endAt(itemName + "\uf8ff").get()
                    .await()
            val itemList = snapshot.children.mapNotNull {
                it.getValue(ItemEntity::class.java)
            }
            emit(itemList)
        } catch (e: Exception) {
            Log.e("get", "failed")
            emit(emptyList())
        }
    }

    suspend fun getDescItems(): Flow<List<ItemEntity>> = flow {
        try {
            val snapshot =
                table.orderByChild("itemName").get().await()
            val itemList = snapshot.children.mapNotNull {
                it.getValue(ItemEntity::class.java)
            }.sortedByDescending { it.itemQuantity }
            emit(itemList)
        } catch (e: Exception) {
            Log.e("get", "failed")
            emit(emptyList())
        }
    }
}
