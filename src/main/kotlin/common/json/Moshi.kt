package common.json

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

fun moshi(): Moshi = Moshi.Builder()
    .addLast(KotlinJsonAdapterFactory())
    .build()