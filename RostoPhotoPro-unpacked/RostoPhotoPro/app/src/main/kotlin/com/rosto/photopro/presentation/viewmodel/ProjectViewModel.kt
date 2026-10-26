package com.rosto.photopro.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rosto.photopro.data.local.ProjectEntity
import com.rosto.photopro.data.repository.ProjectRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProjectViewModel(private val repository: ProjectRepository) : ViewModel() {
    
    private val _projects = MutableStateFlow<List<ProjectEntity>>(emptyList())
    val projects: StateFlow<List<ProjectEntity>> = _projects

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        loadProjects()
    }

    private fun loadProjects() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val projects = repository.getAllProjects()
                _projects.value = projects
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun createProject(name: String, imagePath: String, thumbnail: String? = null) {
        viewModelScope.launch {
            try {
                repository.createProject(name, imagePath, thumbnail)
                loadProjects()
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun deleteProject(id: Int) {
        viewModelScope.launch {
            try {
                repository.deleteProject(id)
                loadProjects()
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun clearError() {
        _error.value = null
    }
}
