package com.example.mynotesshift.di

import android.content.Context
import androidx.room.Room
import com.example.mynotesshift.data.local.dao.NotesDao
import com.example.mynotesshift.data.local.database.NotesDatabase
import com.example.mynotesshift.data.repository.NotesRepositoryImpl
import com.example.mynotesshift.domain.repository.NotesRepository
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
    fun provideDatabase(@ApplicationContext context: Context): NotesDatabase {
        return Room.databaseBuilder(
            context = context,
            NotesDatabase::class.java,
            name = "notes_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideNotesDao(db: NotesDatabase): NotesDao {
        return db.notesDao()
    }

    @Provides
    @Singleton
    fun provideRepositoryImpl(dao: NotesDao): NotesRepository {
        return NotesRepositoryImpl(dao = dao)
    }

}