# Downloader
Downloader android application used to download files to your device.
# Architecture
MVVM is pretty good in such cases. It goes one step further in separating the responsibilities of your code base. It clearly abstracts the logic of the actions that can be performed in the app.
# Built With 🛠
- [Kotlin](https://kotlinlang.org/) : A modern programming language for Android development.
- [Android Architecture Components](https://developer.android.com/topic/architecture) : Collection of libraries that help you design robust, testable, and maintainable apps.
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) : Stores UI-related data that isn't destroyed on UI changes.
    - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) : Data objects that notify views when the underlying database changes.
    - [Data Binding](https://developer.android.com/topic/libraries/architecture/livedata) : It helps in declaratively binding UI elements to in our layout to data sources of our app.
- [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android) : Hilt is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project.
- [Kotlin coroutines](https://developer.android.com/kotlin/coroutines) : Coroutine is a concurrency design pattern that you can use on Android to simplify code that executes asynchronously.
- [Glide](https://github.com/bumptech/glide) : Glide is a fast and efficient open source media management and image loading framework for Android that wraps media decoding, memory and disk caching, and resource pooling into a simple and easy to use interface.
- [File-Loader](https://github.com/kk121/File-Loader) : Android library for downloading, saving/caching and retrieving any type of files ( image, video, pdf, apk etc ) easily.
# Screens
![Screenshot_2022-04-24-05-33-37-76_9d437748342e6c62e57aac20ba2e3244](https://user-images.githubusercontent.com/39858830/164955517-6bde2ff2-e6c9-4458-8bfc-b18f6f839b72.jpg)
![Screenshot_2022-04-24-05-34-26-33_9d437748342e6c62e57aac20ba2e3244](https://user-images.githubusercontent.com/39858830/164955524-5d0a0cdc-7ff3-41ac-9fe3-ba04ecdbb886.jpg)
![Screenshot_2022-04-24-05-36-26-72_9d437748342e6c62e57aac20ba2e3244](https://user-images.githubusercontent.com/39858830/164955531-8cd3f58f-5820-4482-a2ca-2c61f59811f3.jpg)
