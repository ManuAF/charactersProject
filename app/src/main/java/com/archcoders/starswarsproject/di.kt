package com.archcoders.starswarsproject

import androidx.room.Room
import com.archcoders.data.model.datasource.CharacterLocalDataSource
import com.archcoders.data.model.datasource.CharacterRemoteDataSource
import com.archcoders.data.repository.CharacterRepository
import com.archcoders.starswarsproject.data.database.CharacterDataBase
import com.archcoders.starswarsproject.data.database.CharacterRoomDataSource
import com.archcoders.starswarsproject.data.server.CharacterServerDataSource
import com.archcoders.starswarsproject.presentation.viewmodel.DetailCharacterViewModel
import com.archcoders.starswarsproject.presentation.viewmodel.ListCharacterViewModel
import com.archcoders.starswarsproject.utils.Constants
import com.archcoders.usecase.FindCharacterUseCase
import com.archcoders.usecase.GetCharactersUseCase
import com.archcoders.usecase.RefreshCharactersUseCase
import com.archcoders.usecase.SwitchCharacterFavouriteUseCase
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val appModule = module {


    single {
        Room.databaseBuilder(androidApplication(), CharacterDataBase::class.java, Constants.NAME_DATA_BASE)
            .fallbackToDestructiveMigration()
            .build()

    }

    single {
        get<CharacterDataBase>().characterDao()

    }
    viewModel { ListCharacterViewModel(get(), get()) }

    viewModel { DetailCharacterViewModel(get(), get(), get()) }
}


val dataModule = module {

    single<CharacterLocalDataSource> { CharacterRoomDataSource(get()) }
    single<CharacterRemoteDataSource> { CharacterServerDataSource() }

    singleOf(::CharacterRepository)


}


val useCasesModule = module {
    factory { GetCharactersUseCase(repository = get()) }
    factory { FindCharacterUseCase(repository = get()) }
    factory { RefreshCharactersUseCase(repository = get()) }
    factory { SwitchCharacterFavouriteUseCase(repository = get()) }
}