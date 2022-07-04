apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.CORE))
    "implementation"(project(Modules.CORE_UI))

    "implementation"(project(Modules.REPOSITORY_LIST_DOMAIN))
    "implementation"(Gson.gson)
}