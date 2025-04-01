package com.ucb.data

import com.ucb.data.git.IGitRemoteDataSource
import com.ucb.data.git.ILocalDataSource
import com.ucb.domain.Gitalias

class GitAccountRepository(
    private val remoteDataSource: IGitRemoteDataSource,
    private val localDataSource: ILocalDataSource,
) {
    suspend fun findbyId(userId: String): NetworkResult<Gitalias> = this.remoteDataSource.fetch(userId)

    suspend fun save(account: Gitalias): Boolean {
        this.localDataSource.save(account)
        return true
    }
}
