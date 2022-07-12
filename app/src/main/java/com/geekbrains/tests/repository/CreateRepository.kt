package com.geekbrains.tests.repository

import com.geekbrains.BASE_URL
import com.geekbrains.FAKE
import com.geekbrains.tests.BuildConfig
import com.geekbrains.tests.presenter.RepositoryContract
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CreateRepository {
    internal val repo = createRepository()

    private fun createRepository(): RepositoryContract {
        return if (BuildConfig.TYPE == FAKE) {
            FakeGitHubRepository()
        } else {
            GitHubRepository(createRetrofit().create(GitHubApi::class.java))
        }
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}