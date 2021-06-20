package com.rafael.domain

import com.rafael.domain.usecase.*
import org.koin.dsl.module

val domainModule = module {

    factory <GetFactUseCase> {GetFactUseCaseImpl(get())}

    factory <GetFilteredFactUseCase>{GetFilteredFactUseCaseImpl(get())}

    factory <AddFactToDBUseCase> {AddFactToDBUseCaseImpl(get())}

    factory <DeleteFactFromDBUseCase> {DeleteFactFromDBUseCaseImpl(get())}

    factory <ReadAllDBUseCase> {ReadAllDBUseCaseImpl(get())}
}