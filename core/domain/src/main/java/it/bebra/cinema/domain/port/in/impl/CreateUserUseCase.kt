package it.bebra.cinema.domain.port.`in`.impl

import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.dto.user.UserCreateRequest
import it.bebra.cinema.domain.handleErrorResult
import it.bebra.cinema.domain.port.`in`.CreateUserInputPort
import it.bebra.cinema.domain.port.out.UserOutputPort

class CreateUserUseCase(
    private val userOutputPort: UserOutputPort
) : CreateUserInputPort {
    override suspend fun invoke(user: UserCreateRequest): Resource<Unit> {
        val result = userOutputPort.createUser(user)

        handleErrorResult(javaClass.simpleName, result)

        return result
    }
}