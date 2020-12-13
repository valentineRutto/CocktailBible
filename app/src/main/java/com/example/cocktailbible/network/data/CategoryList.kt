package com.example.cocktailbible.network.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


class CategoryList {

    @SerializedName("drinks")
    @Expose
    var category: List<Category>? = null

    @Parcelize
    class Category : Parcelable {

        @SerializedName("strCategory")
        @Expose
        var strCategory: String? = null
    }

}


