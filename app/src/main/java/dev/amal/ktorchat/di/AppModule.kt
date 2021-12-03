package dev.amal.ktorchat.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.amal.ktorchat.data.remote.ChatSocketService
import dev.amal.ktorchat.data.remote.ChatSocketServiceImpl
import dev.amal.ktorchat.data.remote.MessageService
import dev.amal.ktorchat.data.remote.MessageServiceImpl
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.websocket.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient =
        HttpClient(CIO) {
            install(Logging)
            install(WebSockets)
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
        }

    @Provides
    @Singleton
    fun provideMessageService(client: HttpClient): MessageService =
        MessageServiceImpl(client)

    @Provides
    @Singleton
    fun provideChatSocketService(client: HttpClient): ChatSocketService =
        ChatSocketServiceImpl(client)

}