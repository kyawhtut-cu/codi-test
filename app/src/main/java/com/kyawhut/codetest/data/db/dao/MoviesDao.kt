package com.kyawhut.codetest.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kyawhut.codetest.data.db.entities.MoviesEntity
import com.kyawhut.codetest.data.db.utils.MoviesType

/**
 * @author kyawhtut
 * @date 10/16/21
 */
@Dao
abstract class MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(vararg data: MoviesEntity): List<Long>

    @Query("select * from movies_table where type = :screenType")
    abstract fun getAll(screenType: MoviesType): List<MoviesEntity>

    @Query("select * from movies_table where id = :id")
    abstract fun getByID(id: Int): MoviesEntity?

    @Query("delete from movies_table where type = :screenType")
    abstract fun delete(screenType: MoviesType)
}
