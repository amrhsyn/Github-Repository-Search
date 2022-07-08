
#  Description

An application for searching Github repositories and showing details. you can search multi-users in GitHub(with putting a space in search) and get the repositories together
<br />
when you open the application, the application gets "infinum" and "JakeWharton" repositories by default
<br />
you can download a debug version here : [debug version ](https://github.com/amrhsyn/Github-Repository-Search/blob/develop/app-debug.apk)

#  Stack :
- Kotlin
- Compose + Flow
- Coroutines
- Clean architecture + MVVM
- Multi module (feautred-layerd-base)
- Retrofit + Gson
- kotlin dsl
- custom paging

#  Screenshots
search screen :
<br />
<img src="https://github.com/amrhsyn/Github-Repository-Search/blob/develop/screenshots/11.png" width="25%">
<img src="https://github.com/amrhsyn/Github-Repository-Search/blob/develop/screenshots/22.png" width="25%">
<img src="https://github.com/amrhsyn/Github-Repository-Search/blob/develop/screenshots/33.png" width="25%">
<img src="https://github.com/amrhsyn/Github-Repository-Search/blob/develop/screenshots/44.png" width="25%">
<br />
<br />
<br />
details screen:
<br />
<img src="https://github.com/amrhsyn/Github-Repository-Search/blob/develop/screenshots/55.png" width="25%">
<br />
<br />
<br />

#  Module Design

| Module name | Description |

| ------------- | ------------- |

| [app](/app/) | main activity, application class, navigation |

| [core](/core/)  | core business models, util classes and paging classes. |

| [core_ui](/core_ui/)  | core ui utils classes. |

| [repositoryList presentation](/repository_list/repository_list_presentation/)  | repository search ui contains compose files, viewmodels and etc ... |

| [repositoryList domain](/repository_list/repository_list_domain) | repository search domain layer contains repository interfaces and usecases calasses |

| [repositoryList data ](/repository_list/repository_list_data/) | repository search data layer contains repositories implementation, retrofit, dto files, mappers |

| [repositoryDetails presentation ](/repository_details/repository_details_presentation/)  | repository details ui contains compose files, viewmodels and etc .... |

| [repositoryDetails domain](/repository_details/repository_details_domain) | repository details domain layer contains repository interfaces and usecases calasses |

| [repositoryDetails data ](/repository_details/repository_details_data/) | repository details data layer contains repositories implementation, retrofit, dto files, mappers |


#  Architecture
- I used MVVM + Clean Architecture, I have three separated modules named presentation, domain, data for each feature module, with this I have strict separation in my layers and they don't access each other I implemented all clean architecture concepts because the project was an assignment and I think it's over-engineering for this project.

#  And
- because of time limitations, I couldn't write tests for this project but you can see:
  a sample view model test [here](https://github.com/amrhsyn/Pixabay-Image-Search/blob/develop/image_search/image_search_presentation/src/test/java/me/ahch/image_search_presentation/SearchViewModelTest.kt)
  repository testing [here](https://github.com/amrhsyn/fna/tree/master/fleetlist/fleetlist_data/src/test/java/me/ahch/fleetlist_data)
  and compose E2E testing [here](https://github.com/amrhsyn/Pixabay-Image-Search/tree/develop/app/src/androidTest/java/me/ahch/pixabaysearch)
- I used Hilt as my DI library because I think Hilt has fewer boilerplate codes than dagger2 but I could use dagger2 or even koin too.
- I used Coroutines and Flows for app threading and observing because it's lighter than Rx, it's native and integrated with other google libraries and it's easier to test because google has some libraries for testing them.
- For UI/UX, I tried to keep it simple, I used material design and free assets
- I used git-flow as my git strategy, I created a master, develop, feature/repository_search, and feature/details branches
- generaly speaking, because of time limitations i couldn't develop perfect and many things can be improved
