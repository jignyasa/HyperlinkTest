package com.ma.hyperlinktest.model

import com.google.gson.annotations.SerializedName

data class ImageItem(@SerializedName("url")
                     val path:String)