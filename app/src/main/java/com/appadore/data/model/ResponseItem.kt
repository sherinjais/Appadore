package com.appadore.data.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "groupTable")
data class ResponseItem(
        @PrimaryKey var id: Int? = null,
        var bio: String? = null,
        @ColumnInfo(name = "group_photo") var groupPhoto: String? = null,
        var name: String? = null,
        @ColumnInfo(name = "participant_count") var participantCount: Int? = null,
        var private: Boolean? = null,
        @ColumnInfo(name = "unread_count") var unreadCount: Int? = null,
        @ColumnInfo(name = "user_status") var userStatus: String? = null
) {
        // Empty constructor required by Room
        constructor() : this(null, null, null, null, null, null, null, null)
//        // Constructor from JSON
//        constructor(
//                id: Int,
//                bio: String,
//                groupPhoto: String,
//                name: String,
//                participantCount: Int,
//                private: Boolean,
//                unreadCount: Int,
//                userStatus: String
//        ) : this(id, bio, groupPhoto, name, participantCount, private, unreadCount, userStatus)
//
//        var body: String? = null
//        @SerializedName("id") var ids: Int? = null
//        @ColumnInfo(name = "title") var title: String? = null
//        @SerializedName("userId") var userId: Int? = null
}
