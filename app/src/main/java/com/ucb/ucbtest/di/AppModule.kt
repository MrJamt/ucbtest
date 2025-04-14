package com.ucb.ucbtest.di

import android.content.Context
import com.ucb.data.GithubRepository
import com.ucb.data.MovieRepository
import com.ucb.data.expense.ExpenseRepository
import com.ucb.data.expense.IExpenseLocalDataSource
import com.ucb.data.git.IGitRemoteDataSource
import com.ucb.data.git.ILocalDataSource
import com.ucb.data.income.IIncomeLocalDataSource
import com.ucb.data.income.IncomeRepository
import com.ucb.data.movie.IMovieRemoteDataSource
import com.ucb.framework.datasource.ExpenseLocalDataSource
import com.ucb.framework.datasource.IncomeLocalDataSource
import com.ucb.framework.github.GithubLocalDataSource
import com.ucb.framework.github.GithubRemoteDataSource
import com.ucb.framework.movie.MovieRemoteDataSource
import com.ucb.framework.service.RetrofitBuilder
import com.ucb.ucbtest.R
import com.ucb.usecases.FindGitAlias
import com.ucb.usecases.GetPopularMovies
import com.ucb.usecases.SaveGitalias
import com.ucb.usecases.expenses.DeleteExpense
import com.ucb.usecases.expenses.GetAllExpenses
import com.ucb.usecases.expenses.RegisterExpense
import com.ucb.usecases.incomes.DeleteIncome
import com.ucb.usecases.incomes.GetAllIncomes
import com.ucb.usecases.incomes.RegisterIncome
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

    @Provides
    @Singleton
    fun provideIncomeLocalDataSource(
        @ApplicationContext context: Context,
    ): IIncomeLocalDataSource = IncomeLocalDataSource(context)

    @Provides
    @Singleton
    fun provideIncomeRepository(incomeLocalDataSource: IIncomeLocalDataSource): IncomeRepository = IncomeRepository(incomeLocalDataSource)

    @Provides
    @Singleton
    fun provideRegisterIncome(incomeRepository: IncomeRepository): RegisterIncome = RegisterIncome(incomeRepository)

    @Provides
    @Singleton
    fun provideGetAllIncomes(incomeRepository: IncomeRepository): GetAllIncomes = GetAllIncomes(incomeRepository)

    @Provides
    @Singleton
    fun provideDeleteIncome(incomeRepository: IncomeRepository): DeleteIncome = DeleteIncome(incomeRepository)

    @Provides
    @Singleton
    fun provideExpenseLocalDataSource(
        @ApplicationContext context: Context,
    ): IExpenseLocalDataSource = ExpenseLocalDataSource(context)

    @Provides
    @Singleton
    fun provideExpenseRepository(expenseLocalDataSource: IExpenseLocalDataSource): ExpenseRepository =
        ExpenseRepository(expenseLocalDataSource)

    @Provides
    @Singleton
    fun provideRegisterExpense(expenseRepository: ExpenseRepository): RegisterExpense = RegisterExpense(expenseRepository)

    @Provides
    @Singleton
    fun provideGelAllExpense(expenseRepository: ExpenseRepository): GetAllExpenses = GetAllExpenses(expenseRepository)

    @Provides
    @Singleton
    fun provideDeleteExpense(expenseRepository: ExpenseRepository): DeleteExpense = DeleteExpense(expenseRepository)
}
