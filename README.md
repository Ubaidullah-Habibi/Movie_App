# Movie Hub

This Application will let the users to search for movies and view detailed information. The app is built using Kotlin and follows the MVVM (Model-View-ViewModel) architecture for better maintainability and scalability.

# Features of the Application :

* Movie Search: Search for movies by name using the OMDb API.

* Movie Details: View comprehensive details about a selected movie.

* MVVM Architecture: Ensures a clean separation of concerns.

* LiveData & ViewModel: Efficient state management.

* View Binding: Simplifies UI interactions.

* OkHttp & Gson: For network communication and JSON parsing.
  

  
 # Project Structure :

1. model
   - data_source
     - remote
       - ApiClient
       - NetworkClient
       - OkHttpInstance
       - DTOs (Search, Movie, Rating)
   - repository
     - MovieRepository
     - MovieRepositoryImp
   - usecase
     - MovieUsecase

2. view
   - MainActivity
   - SearchActivity
   - MovieDetailActivity

3. viewmodel
   - SearchViewModel
   - MovieDetailViewModel

4. utils
   - Contains helper utilities 
