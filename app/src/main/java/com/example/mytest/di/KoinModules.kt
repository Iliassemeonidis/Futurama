package com.example.mytest.di

import androidx.room.Room
import com.example.mytest.model.data.FuturamaResultData
import com.example.mytest.repositiry.RetrofitBuilder
import com.example.mytest.repositiry.datasourse.local.DataSoursLocalImplimentation
import com.example.mytest.repositiry.datasourse.local.RoomDataBaseImplementation
import com.example.mytest.repositiry.interactor.MainInteractor
import com.example.mytest.repositiry.interactor.OnlineIdentificatorImp
import com.example.mytest.repositiry.repository.MainRepository
import com.example.mytest.repositiry.repository.*
import com.example.mytest.repositiry.datasourse.remote.DataSoursImplimentation
import com.example.mytest.repositiry.room.HistoryDataBase
import com.example.mytest.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val application = module {
    single {
        Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB")
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<HistoryDataBase>().historyDao() }
}


val mainScreen = module {

    viewModel { MainViewModel(get()) }
    factory { MainInteractor(repository = get(), connectivity = get()) }

    single<MainInteractor.Connectivity> { OnlineIdentificatorImp(get()) }

    factory<MainRepository<FuturamaResultData>> {
        RepositoryMainImplementation(
            DataSoursImplimentation(RetrofitBuilder()),
            DataSoursLocalImplimentation(RoomDataBaseImplementation(get()))
        )
    }

}