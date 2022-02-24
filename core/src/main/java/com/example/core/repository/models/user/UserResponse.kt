package com.example.core.repository.models.user

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class UserResponse (
    @SerializedName("total_count")
    @Expose
    var totalCount: Int? = null,
    @SerializedName("incomplete_results")
    @Expose
    var incompleteResults: Boolean? = null,

    @SerializedName("items")
    @Expose
    var items: List<Item> = emptyList()
    )

