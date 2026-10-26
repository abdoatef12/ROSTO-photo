package com.rosto.photopro.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Query

@Entity(tableName = "projects")
data class ProjectEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val imagePath: String,
    val createdAt: Long = System.currentTimeMillis(),
    val modifiedAt: Long = System.currentTimeMillis(),
    val thumbnail: String? = null,
    val filters: String = "[]",
    val adjustments: String = ""
)

@Dao
interface ProjectDao {
    @Insert
    suspend fun insertProject(project: ProjectEntity)

    @Delete
    suspend fun deleteProject(project: ProjectEntity)

    @Query("SELECT * FROM projects ORDER BY modifiedAt DESC")
    suspend fun getAllProjects(): List<ProjectEntity>

    @Query("SELECT * FROM projects WHERE id = :id")
    suspend fun getProjectById(id: Int): ProjectEntity?

    @Query("UPDATE projects SET modifiedAt = :timestamp WHERE id = :id")
    suspend fun updateProjectTimestamp(id: Int, timestamp: Long = System.currentTimeMillis())
}

@Database(entities = [ProjectEntity::class], version = 1)
abstract class RostoDatabase : RoomDatabase() {
    abstract fun projectDao(): ProjectDao
}
