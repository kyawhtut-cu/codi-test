package com.kyawhut.codetest.data.db.utils

/**
 * @author kyawhtut
 * @date 10/16/21
 */
enum class MoviesType(val type: String) {
    UPCOMING("up_coming"), POPULAR("popular");

    companion object {
        fun getMoviesType(type: String): MoviesType {
            values().forEach {
                if (it.type == type) return it
            }
            throw RuntimeException("Unknown type => $type")
        }
    }
}
