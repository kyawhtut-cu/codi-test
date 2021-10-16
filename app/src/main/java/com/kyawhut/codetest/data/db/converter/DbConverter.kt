package com.kyawhut.codetest.data.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kyawhut.codetest.data.db.utils.MoviesType

/**
 * @author kyawhtut
 * @date 10/16/21
 */
object DbConverter {

    @JvmStatic
    @TypeConverter
    fun MoviesType.toMoviesTypeString(): String = this.type

    @JvmStatic
    @TypeConverter
    fun String.toMoviesType(): MoviesType = MoviesType.getMoviesType(this)

    @JvmStatic
    @TypeConverter
    fun List<Int>.toGenresIDString(): String {
        return Gson().toJson(this)
    }

    @JvmStatic
    @TypeConverter
    fun String.toGenresIDList(): List<Int> {
        return Gson().fromJson(this, object : TypeToken<List<Int>>() {}.type)
    }
}
