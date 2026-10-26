package com.rosto.photopro.data.repository

import com.rosto.photopro.data.local.ProjectDao
import com.rosto.photopro.data.local.ProjectEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProjectRepository(private val projectDao: ProjectDao) {
    
    suspend fun createProject(
        name: String,
        imagePath: String,
        thumbnail: String? = null
    ) = withContext(Dispatchers.IO) {
        val project = ProjectEntity(
            name = name,
            imagePath = imagePath,
            thumbnail = thumbnail
        )
        projectDao.insertProject(project)
    }

    suspend fun deleteProject(id: Int) = withContext(Dispatchers.IO) {
        val project = projectDao.getProjectById(id)
        project?.let { projectDao.deleteProject(it) }
    }

    suspend fun getAllProjects() = withContext(Dispatchers.IO) {
        projectDao.getAllProjects()
    }

    suspend fun getProjectById(id: Int) = withContext(Dispatchers.IO) {
        projectDao.getProjectById(id)
    }

    suspend fun updateProjectTimestamp(id: Int) = withContext(Dispatchers.IO) {
        projectDao.updateProjectTimestamp(id)
    }
}
