package com.inspirationdriven.uhomework.misc

const val CAAS_URL = "https://cataas.com"

fun resolveCatUrl(id: String) = "$CAAS_URL/cat?id=$id"
