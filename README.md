### Modularized Github App
Currently, Modularized Github App app displays the list of developers in Lagos. Also, it allows you to add and remove favorite developer's profile.

##### Library used:
- Room - For caching favorited developer profile
- Paging 2 - For infinite scrolling of developer's profiles
- Coroutine Flow - Used to emit day to the Ui
- RxJava 2 - To manage threads
- Retrofit - Used for network calls
- Gson - Used to deserialized data coming from the Github API
- OkHttp interceptors - Used to intercept network response
- Hilt - For dependency injections
- Glide - To load developer images
- Mockito - To mock/spy classes for Unit testing

`buildSrc` is used to manage dependencies in order to avoid dependency duplication, and at the same time leverage the power of Kotlin DSL.
`Modularized Github App` is currently under development and more tests and features will be added in the nearest feature. Please feel free to submit PRs.