package com.example.productlist.util.extension


fun String?.fromHtml(): String {
    val text =
        this?.let {
            if (it != "null") {
                it.replace("<br/>", "\n")
            } else {
                ""
            }
        }.toString()
    return text
}

