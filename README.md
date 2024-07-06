<h1 align="center">STOCKS App(Android application for a stocks / etfs broking platform)</h1>
<p align="center">  
  STOCKS App demonstrates modern Android development with Jetpack Compose, Hilt, Coroutines, Flow, Jetpack (Room, ViewModel), and Material Design based on MVVM architecture.
</p>

 <img src="https://github.com/utkarshxf/StocksApp/assets/78771861/ad7c810e-ddf5-450d-ba1d-264f80eb724e"/>

## Download

Go to the [Releases](https:??) to download the latest APK.
<img src="previews/preview.gif" align="right" width="320"/>

Go to  [Features](#features) Section to know how Stocks App work.

## Tech stack & Open-source libraries

- [Kotlin](https://kotlinlang.org/) based, utilizing [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous operations.
- Jetpack Libraries:
  - Jetpack Compose: Android’s modern toolkit for declarative UI development.
  - ViewModel: Manages UI-related data and is lifecycle-aware, ensuring data survival through configuration changes.
  - Navigation: Facilitates screen navigation, complemented by [Compose Navigation]([https://developer.android.com/develop/ui/compose/navigation]) for Screen Navigation.
  - Room: Constructs a database with an SQLite abstraction layer for seamless database access.
  - [Hilt](https://dagger.dev/hilt/): Facilitates dependency injection.
- Architecture:
  - MVVM Architecture (View - ViewModel - Model): Facilitates separation of concerns and promotes maintainability.
  - Repository Pattern: Acts as a mediator between different data sources and the application's business logic.
- [Retrofit2](https://github.com/square/retrofit): Constructs REST APIs and facilitates paging network data retrieval.

## Architecture
**STOCKS App** adheres to the MVVM architecture and implements the Repository pattern, aligning with [Google's official architecture guidance](https://developer.android.com/topic/architecture).
The architecture of **STOCKS App** is structured into three distinct layers: the UI layer, domain layer and data layer. Each layer fulfills specific roles and responsibilities, outlined as follows:
**STOCKS App** follows the principles outlined in the [Guide to app architecture](https://developer.android.com/topic/architecture), making it an exemplary demonstration of architectural concepts in practical application.

### Architecture Overview
- The data layer operates autonomously from other layers, maintaining purity without dependencies on external layers.
- This loosely coupled architecture enhances component reusability and app scalability, facilitating seamless development and maintenance.

### UI Layer
The UI layer encompasses UI elements responsible for configuring screens for user interaction, alongside the [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel), which manages app states and restores data during configuration changes.
- UI elements observe the data flow, ensuring synchronization with the underlying data layer.

### Domain Layer

### Data Layer
The data layer is composed of repositories that handle business logic tasks such as retrieving data from a local database or fetching remote data from a network. This layer is designed to prioritize online access, functioning primarily as an online-first repository of business logic. It adheres to the principle of "single source of truth," ensuring that all data operations are centralized and consistent.<br>

## Data API

STOCKS App using the [Alphavantage](https://www.alphavantage.co) for constructing RESTful API.<br>
Alphavantage provides a RESTful API interface to highly detailed objects built from thousands of lines of data related to stocks.
These endpoints used for all the required data:
- Alpha Intelligence - Top Gainers and Losers.
- Fundamental data - Company Overview
- Core Stocks API - Ticker search

## Features
- View Top gainers and losers
    - Open the app.
    ->click on TOP GAINERS / LOSERS Button.
      
       <img src="https://github.com/utkarshxf/StocksApp/assets/78771861/1da1a225-b8ef-489b-9ed7-78e903528c83" width="200"/>

      
- Searching for Stocks:
    - Open the app.
    -> Use the search bar to find a specific stock by its symbol.
      
      <img src="https://github.com/utkarshxf/StocksApp/assets/78771861/23024411-e961-4b90-893f-1522db9c55e5" width="200"/>

- Viewing Stock Details:
    - Select a stock from the search results.
    -> View the stock's real-time data and historical charts.

      <img src="https://github.com/utkarshxf/StocksApp/assets/78771861/ee2c2b00-3a9e-45b2-ad10-7beaa6a21c17" width="200"/>

- Offline Mode
    - Data Catch in local Database.
      
      <img src="https://github.com/utkarshxf/StocksApp/assets/78771861/57a58922-f947-4949-ab01-a59dee0b2ea5" width="200"/>

    - TTL Catched Data
      
        -Whenever there is an Insert or an Update operation, the lastUpDatedDate column updated with the current timestamp.
        -The next time a Read operation happens, limit it by the TTL(defined in Android Client).

- Dark Mode
    - switched dynamically(Light / Dark Mode)
 
      <img src="https://github.com/utkarshxf/StocksApp/assets/78771861/f34d218f-45fe-476b-8822-1f479136c6e9" width="200"/>

## Screens

### Light Mode
<img src="https://github.com/utkarshxf/StocksApp/assets/78771861/0f81a58e-d0a4-4aa7-adaf-0b7919b84ccd" width="200"/>
<img src="https://github.com/utkarshxf/StocksApp/assets/78771861/2d9c26b4-30ae-4a8b-92a6-b5ffe5454d78" width="200"/>

### Dark Mode

<img src="https://github.com/utkarshxf/StocksApp/assets/78771861/778c3e41-7452-42bc-8adf-37c4bd139762" width="200"/>
<img src="https://github.com/utkarshxf/StocksApp/assets/78771861/ca1b7f41-f3ff-41c3-a2bf-12858999f443" width="200"/>