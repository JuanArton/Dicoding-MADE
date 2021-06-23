package com.juanarton.core.di

import androidx.room.Room
import com.juanarton.core.domain.RepositoryInterface
import com.juanarton.core.repo.MovieRepository
import com.juanarton.core.repo.local.LocalDataSource
import com.juanarton.core.repo.local.room.AppDatabase
import com.juanarton.core.repo.remote.RemoteDataSource
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single { RemoteDataSource() }
    single { LocalDataSource(get()) }
    single<RepositoryInterface> {
        MovieRepository(
            get(),
            get()
        )
    }
}

val databaseModule = module {
    factory { get<AppDatabase>().dbDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("UwU".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, "movie.db"
        ).fallbackToDestructiveMigration().openHelperFactory(factory).build()
    }
}