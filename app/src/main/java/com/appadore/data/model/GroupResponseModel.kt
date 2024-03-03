package com.appadore.data.model

data class GroupResponseModel(
    val message: String,
    val result: GroupResult
)

data class GroupResult(
    val current_page: Int,
    val groups: List<Group>,
    val next_page: Boolean,
    val total: Int
)

data class Group(
    val bio: String?,
    val group_photo: String?,
    val id: Int?,
    val name: String?,
    val participant_count: Int?,
    val private: Boolean?,
    val unread_count: Int?,
    val user_status: String?
)
