package it.bebra.cinema.domain.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import it.bebra.cinema.domain.port.`in`.CreateTicketInputPort
import it.bebra.cinema.domain.port.`in`.CreateUserInputPort
import it.bebra.cinema.domain.port.`in`.DeleteUserInputPort
import it.bebra.cinema.domain.port.`in`.GetAllMovieSessionsInputPort
import it.bebra.cinema.domain.port.`in`.GetAllMoviesInputPort
import it.bebra.cinema.domain.port.`in`.GetAllTicketsInputPort
import it.bebra.cinema.domain.port.`in`.GetMovieInputPort
import it.bebra.cinema.domain.port.`in`.GetNewAccessTokenInputPort
import it.bebra.cinema.domain.port.`in`.GetUserProfileInputPort
import it.bebra.cinema.domain.port.`in`.IsUserLoggedInInputPort
import it.bebra.cinema.domain.port.`in`.LoginInputPort
import it.bebra.cinema.domain.port.`in`.LogoutInputPort
import it.bebra.cinema.domain.port.`in`.impl.CreateTicketUseCase
import it.bebra.cinema.domain.port.`in`.impl.CreateUserUseCase
import it.bebra.cinema.domain.port.`in`.impl.DeleteUserUseCase
import it.bebra.cinema.domain.port.`in`.impl.GetAllMovieSessionsUseCase
import it.bebra.cinema.domain.port.`in`.impl.GetAllMoviesUseCase
import it.bebra.cinema.domain.port.`in`.impl.GetAllTicketsUseCase
import it.bebra.cinema.domain.port.`in`.impl.GetMovieUseCase
import it.bebra.cinema.domain.port.`in`.impl.GetNewAccessTokenUseCase
import it.bebra.cinema.domain.port.`in`.impl.GetUserProfileUseCase
import it.bebra.cinema.domain.port.`in`.impl.IsUserLoggedInUseCase
import it.bebra.cinema.domain.port.`in`.impl.LoginUseCase
import it.bebra.cinema.domain.port.`in`.impl.LogoutUseCase
import it.bebra.cinema.domain.port.out.AuthOutputPort
import it.bebra.cinema.domain.port.out.MovieOutputPort
import it.bebra.cinema.domain.port.out.SessionOutputPort
import it.bebra.cinema.domain.port.out.TicketOutputPort
import it.bebra.cinema.domain.port.out.UserOutputPort
import it.bebra.cinema.domain.service.request.manager.RequestManager
import it.bebra.cinema.domain.service.request.manager.RequestManagerImpl
import it.bebra.cinema.domain.service.storage.EncryptedStorage
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun provideRequestManager(
        storage: EncryptedStorage,
        isUserLoggedInInputPort: IsUserLoggedInInputPort,
        getNewAccessTokenInputPort: GetNewAccessTokenInputPort,
        loginInputPort: LoginInputPort
    ): RequestManager =
        RequestManagerImpl(
            storage,
            isUserLoggedInInputPort,
            getNewAccessTokenInputPort,
            loginInputPort
        )

    @Provides
    @Singleton
    fun provideEncryptedStorage(
        @ApplicationContext context: Context
    ): EncryptedStorage = EncryptedStorage(context)

    @Provides
    @Singleton
    fun provideCreateTicketInputPort(
        outputPort: TicketOutputPort,
        storage: EncryptedStorage,
        manager: RequestManager
    ): CreateTicketInputPort =
        CreateTicketUseCase(outputPort, storage, manager)

    @Provides
    @Singleton
    fun provideCreateUserInputPort(
        outputPort: UserOutputPort
    ): CreateUserInputPort =
        CreateUserUseCase(outputPort)

    @Provides
    @Singleton
    fun provideGetAllMoviesInputPort(
        outputPort: MovieOutputPort,
        storage: EncryptedStorage,
        manager: RequestManager
    ): GetAllMoviesInputPort =
        GetAllMoviesUseCase(outputPort, storage, manager)

    @Provides
    @Singleton
    fun provideGetAllTicketsInputPort(
        outputPort: TicketOutputPort,
        storage: EncryptedStorage,
        manager: RequestManager
    ): GetAllTicketsInputPort =
        GetAllTicketsUseCase(outputPort, storage, manager)

    @Provides
    @Singleton
    fun provideGetMovieInputPort(
        outputPort: MovieOutputPort,
        storage: EncryptedStorage,
        manager: RequestManager
    ): GetMovieInputPort =
        GetMovieUseCase(outputPort, storage, manager)

    @Provides
    @Singleton
    fun provideGetNewAccessTokenInputPort(
        outputPort: AuthOutputPort,
        storage: EncryptedStorage
    ): GetNewAccessTokenInputPort =
        GetNewAccessTokenUseCase(outputPort, storage)

    @Provides
    @Singleton
    fun provideGetUserProfileInputPort(
        outputPort: UserOutputPort,
        storage: EncryptedStorage,
        manager: RequestManager
    ): GetUserProfileInputPort =
        GetUserProfileUseCase(outputPort, storage, manager)

    @Provides
    @Singleton
    fun provideLoginInputPort(
        outputPort: AuthOutputPort,
        storage: EncryptedStorage
    ): LoginInputPort =
        LoginUseCase(outputPort, storage)

    @Provides
    @Singleton
    fun provideIsUserLoggedInInputPort(
        storage: EncryptedStorage
    ): IsUserLoggedInInputPort =
        IsUserLoggedInUseCase(storage)

    @Provides
    @Singleton
    fun provideGetAllMovieSessionsInputPort(
        outputPort: SessionOutputPort,
        storage: EncryptedStorage,
        manager: RequestManager
    ): GetAllMovieSessionsInputPort =
        GetAllMovieSessionsUseCase(outputPort, storage, manager)

    @Provides
    @Singleton
    fun provideLogoutInputPort(
        storage: EncryptedStorage
    ): LogoutInputPort =
        LogoutUseCase(storage)

    @Provides
    @Singleton
    fun provideDeleteUserInputPort(
        storage: EncryptedStorage,
        outputPort: UserOutputPort,
        manager: RequestManager
    ): DeleteUserInputPort =
        DeleteUserUseCase(outputPort, storage, manager)
}