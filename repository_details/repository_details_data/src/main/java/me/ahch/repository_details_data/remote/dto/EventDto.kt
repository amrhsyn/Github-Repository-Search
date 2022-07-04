package me.ahch.repository_details_data.remote.dto


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class EventDto(
    @SerializedName("actor")
    val actor: Actor,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("payload")
    val payload: Payload,
    @SerializedName("public")
    val `public`: Boolean,
    @SerializedName("repo")
    val repo: Repo,
    @SerializedName("type")
    val type: String
) {
    @Keep
    data class Actor(
        @SerializedName("avatar_url")
        val avatarUrl: String,
        @SerializedName("display_login")
        val displayLogin: String,
        @SerializedName("gravatar_id")
        val gravatarId: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("login")
        val login: String,
        @SerializedName("url")
        val url: String
    )

    @Keep
    data class Payload(
        @SerializedName("pusher_type")
        val pusherType: String,
        @SerializedName("ref")
        val ref: String,
        @SerializedName("ref_type")
        val refType: String
    )

    @Keep
    data class Repo(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("url")
        val url: String
    )
}