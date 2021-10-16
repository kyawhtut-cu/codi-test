package com.kyawhut.codetest.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kyawhut.codetest.data.db.converter.DbConverter
import com.kyawhut.codetest.data.db.dao.MoviesDao
import com.kyawhut.codetest.data.db.entities.MoviesEntity

/**
 * @author kyawhtut
 * @date 10/16/21
 */
@Database(entities = [MoviesEntity::class], version = 1, exportSchema = false)
@TypeConverters(DbConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val moviesDao: MoviesDao

}
