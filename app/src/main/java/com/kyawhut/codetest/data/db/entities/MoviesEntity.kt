package com.kyawhut.codetest.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kyawhut.codetest.BuildConfig
import com.kyawhut.codetest.data.db.utils.MoviesType
import com.kyawhut.codetest.data.network.response.MoviesResponse

/**
 * @author kyawhtut
 * @date 10/16/21
 */
@Entity(tableName = "movies_table")
data class MoviesEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "movies_title")
    val moviesTitle: String,
    @ColumnInfo(name = "movies_poster")
    val moviesPoster: String,
    @ColumnInfo(name = "movies_cover")
    val moviesCover: String,
    @ColumnInfo(name = "movies_description")
    val moviesDescription: String,
    @ColumnInfo(name = "movies_original_title")
    val moviesOriginalTitle: String,
    @ColumnInfo(name = "movies_original_language")
    val moviesOriginalLanguage: String,
    @ColumnInfo(name = "movies_genres_ids")
    val moviesGenresIDS: List<Int>,
    @ColumnInfo(name = "movies_release_date")
    val moviesReleaseDate: String,
    @ColumnInfo(name = "movies_vote_average")
    val moviesVoteAverage: Float,
    @ColumnInfo(name = "movies_vote_count")
    val moviesVoteCount: Long,
    @ColumnInfo(name = "movies_popularity")
    val moviesPopularity: Float,
    @ColumnInfo(name = "is_adult")
    val isAdult: Boolean,
    @ColumnInfo(name = "is_video")
    val isVideo: Boolean,
    @ColumnInfo(name = "type")
    val type: MoviesType,
) {

    companion object {
        fun MoviesResponse.toMovieEntity(type: MoviesType): MoviesEntity {
            return MoviesEntity(
                id,
                title,
                BuildConfig.IMAGE_BASE_URL + posterPath ?: "",
                BuildConfig.IMAGE_BASE_URL + backdropPath ?: "",
                overview,
                originTitle,
                originalLanguage,
                genreIDS,
                releaseDate,
                voteAverage,
                voteCount,
                popularity,
                adult,
                video,
                type
            )
        }
    }
}
