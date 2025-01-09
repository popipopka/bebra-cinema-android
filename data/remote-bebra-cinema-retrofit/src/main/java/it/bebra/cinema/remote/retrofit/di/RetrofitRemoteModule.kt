package it.bebra.cinema.remote.retrofit.di

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import it.bebra.cinema.remote.retrofit.RemoteConstants
import it.bebra.cinema.remote.retrofit.api.AuthApi
import it.bebra.cinema.remote.retrofit.api.MovieApi
import it.bebra.cinema.remote.retrofit.api.SessionApi
import it.bebra.cinema.remote.retrofit.api.TicketApi
import it.bebra.cinema.remote.retrofit.api.UserApi
import it.bebra.cinema.remote.retrofit.api.gateway.AuthGateway
import it.bebra.cinema.remote.retrofit.api.gateway.MovieGateway
import it.bebra.cinema.remote.retrofit.api.gateway.SessionGateway
import it.bebra.cinema.remote.retrofit.api.gateway.TicketGateway
import it.bebra.cinema.remote.retrofit.api.gateway.UserGateway
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitRemoteModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .baseUrl(RemoteConstants.BASE_URL.value)
        .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(
                HttpLoggingInterceptor { message ->
                    Log.d("OkHttp", message)
                }.apply {
                    setLevel(HttpLoggingInterceptor.Level.BASIC)
                },
            ).build()

    @Provides
    @Singleton
    fun provideTicketGateway(
        ticketApi: TicketApi
    ): TicketGateway = TicketGateway(ticketApi)

    @Provides
    @Singleton
    fun provideTicketApi(retrofit: Retrofit): TicketApi = retrofit.create(TicketApi::class.java)

    @Provides
    @Singleton
    fun provideUserGateway(
        userApi: UserApi
    ): UserGateway = UserGateway(userApi)

    @Provides
    @Singleton
    fun provideUserApi(retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)

    @Provides
    @Singleton
    fun provideMovieGateway(
        api: MovieApi
    ): MovieGateway = MovieGateway(api)

    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit): MovieApi = retrofit.create(MovieApi::class.java)

    @Provides
    @Singleton
    fun provideAuthGateway(
        api: AuthApi
    ): AuthGateway = AuthGateway(api)

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)

    @Provides
    @Singleton
    fun provideSessionGateway(
        api: SessionApi
    ): SessionGateway = SessionGateway(api)

    @Provides
    @Singleton
    fun provideSessionApi(retrofit: Retrofit): SessionApi = retrofit.create(SessionApi::class.java)
}