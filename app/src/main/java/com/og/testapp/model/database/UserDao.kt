package com.og.testapp.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.og.testapp.model.entity.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun selectUsers(): Single<List<User>>

    @Query("SELECT * FROM users WHERE id=:id LIMIT 1")
    fun selectUserById(id: Int): Single<User>

    @Insert
    fun insertUsers(usersList: List<User>): Completable

    @Query("DELETE FROM users")
    fun deleteUsers(): Completable

}