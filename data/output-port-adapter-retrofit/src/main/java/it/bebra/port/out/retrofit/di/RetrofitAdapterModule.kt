package it.bebra.port.out.retrofit.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import it.bebra.cinema.domain.port.out.AuthOutputPort
import it.bebra.cinema.domain.port.out.MovieOutputPort
import it.bebra.cinema.domain.port.out.SessionOutputPort
import it.bebra.cinema.domain.port.out.TicketOutputPort
import it.bebra.cinema.domain.port.out.UserOutputPort
import it.bebra.cinema.remote.retrofit.api.gateway.AuthGateway
import it.bebra.cinema.remote.retrofit.api.gateway.MovieGateway
import it.bebra.cinema.remote.retrofit.api.gateway.SessionGateway
import it.bebra.cinema.remote.retrofit.api.gateway.TicketGateway
import it.bebra.cinema.remote.retrofit.api.gateway.UserGateway
import it.bebra.port.out.retrofit.RetrofitAuthGatewayAdapter
import it.bebra.port.out.retrofit.RetrofitMovieGatewayAdapter
import it.bebra.port.out.retrofit.RetrofitSessionGatewayAdapter
import it.bebra.port.out.retrofit.RetrofitTicketGatewayAdapter
import it.bebra.port.out.retrofit.RetrofitUserGatewayAdapter
import it.bebra.port.out.retrofit.handler.ResponseHandler
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitAdapterModule {

    @Provides
    @Singleton
    fun provideResponseHandler(): ResponseHandler = ResponseHandler()

    @Provides
    @Singleton
    fun provideAuthOutputPort(
        gateway: AuthGateway,
        handler: ResponseHandler
    ): AuthOutputPort =
        RetrofitAuthGatewayAdapter(gateway, handler)

    @Provides
    @Singleton
    fun provideMovieOutputPort(
        gateway: MovieGateway,
        handler: ResponseHandler
    ): MovieOutputPort =
        RetrofitMovieGatewayAdapter(gateway, handler)

    @Provides
    @Singleton
    fun provideTicketOutputPort(
        gateway: TicketGateway,
        handler: ResponseHandler
    ): TicketOutputPort =
        RetrofitTicketGatewayAdapter(gateway, handler)

    @Provides
    @Singleton
    fun provideUserOutputPort(
        gateway: UserGateway,
        handler: ResponseHandler
    ): UserOutputPort =
        RetrofitUserGatewayAdapter(gateway, handler)

    @Provides
    @Singleton
    fun provideSessionOutputPort(
        gateway: SessionGateway,
        handler: ResponseHandler
    ): SessionOutputPort =
        RetrofitSessionGatewayAdapter(gateway, handler)
}