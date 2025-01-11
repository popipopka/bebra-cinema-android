package it.bebra.cinema.domain.port.out

import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.dto.user.UserCreateRequest
import it.bebra.cinema.domain.dto.user.UserDetailResponse
import it.bebra.cinema.domain.dto.user.UserUpdateRequest

interface UserOutputPort {
    suspend fun createUser(user: UserCreateRequest): Resource<Unit>

    suspend fun getUserProfile(token: String): Resource<UserDetailResponse>

    suspend fun deleteUser(token: String): Resource<Unit>

    suspend fun updateUser(token: String, payload: UserUpdateRequest): Resource<Unit>
}