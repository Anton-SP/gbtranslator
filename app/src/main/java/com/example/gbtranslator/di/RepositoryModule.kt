package com.example.gbtranslator.di

import com.example.gbtranslator.data.Word
import com.example.gbtranslator.repository.Repository
import com.example.gbtranslator.repository.RepositoryImplementation
import com.example.gbtranslator.source.DataSource
import com.example.gbtranslator.source.RetrofitImplementation
import com.example.gbtranslator.source.RoomDataBaseImplementation
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideRepositoryRemote(@Named(NAME_REMOTE) dataSourceRemote: DataSource<List<Word>>): Repository<List<Word>> =
        RepositoryImplementation(dataSourceRemote)

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideRepositoryLocal(@Named(NAME_LOCAL) dataSourceLocal: DataSource<List<Word>>): Repository<List<Word>> =
        RepositoryImplementation(dataSourceLocal)

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideDataSourceRemote(): DataSource<List<Word>> =
        RetrofitImplementation()

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideDataSourceLocal(): DataSource<List<Word>> = RoomDataBaseImplementation()
}
