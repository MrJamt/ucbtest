package com.ucb.ucbtest.di

import android.content.Context
import com.ucb.data.GithubRepository
import com.ucb.data.MovieRepository
import com.ucb.data.git.IGitRemoteDataSource
import com.ucb.data.git.ILocalDataSource
import com.ucb.data.movie.IMovieRemoteDataSource
import com.ucb.framework.github.GithubLocalDataSource
import com.ucb.framework.github.GithubRemoteDataSource
import com.ucb.framework.movie.MovieRemoteDataSource
import com.ucb.framework.service.RetrofitBuilder
import com.ucb.ucbtest.R
import com.ucb.usecases.FindGitAlias
import com.ucb.usecases.GetPopularMovies
import com.ucb.usecases.SaveGitalias
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providerRetrofitBuilder(
        @ApplicationContext context: Context,
    ): RetrofitBuilder = RetrofitBuilder(context)

    @Provides
    @Singleton
    fun gitRemoteDataSource(retrofiService: RetrofitBuilder): IGitRemoteDataSource = GithubRemoteDataSource(retrofiService)

    @Provides
    @Singleton
    fun provideLocalDataSource(
        @ApplicationContext context: Context,
    ): ILocalDataSource = GithubLocalDataSource(context)

    @Provides
    @Singleton
    fun gitRepository(
        remoteDataSource: IGitRemoteDataSource,
        localDataSource: ILocalDataSource,
    ): GithubRepository = GithubRepository(remoteDataSource, localDataSource)

    @Provides
    @Singleton
    fun provideSaveGitAlias(repository: GithubRepository): SaveGitalias = SaveGitalias(repository)

    @Provides
    @Singleton
    fun provideGitUseCases(githubRepository: GithubRepository): FindGitAlias = FindGitAlias(githubRepository)

    @Provides
    @Singleton
    fun provideGetPopularMovies(
        movieRepository: MovieRepository,
        @ApplicationContext context: Context,
    ): GetPopularMovies {
        val token = context.getString(R.string.token)
        return GetPopularMovies(movieRepository, token)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(dataSource: IMovieRemoteDataSource): MovieRepository = MovieRepository(dataSource)

    @Provides
    @Singleton
    fun provideMovieRemoteDataSource(retrofit: RetrofitBuilder): IMovieRemoteDataSource = MovieRemoteDataSource(retrofit)
}
