apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.REPOSITORY_LIST_DOMAIN))
    "implementation"(project(Modules.CORE))

    "implementation"(Retrofit.okHttp)
    "implementation"(Retrofit.retrofit)
    "implementation"(Retrofit.okHttpLoggingInterceptor)
    "implementation"(Retrofit.gsonConvertor)
}