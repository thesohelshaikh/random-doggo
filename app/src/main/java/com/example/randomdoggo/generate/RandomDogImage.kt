package com.example.randomdoggo.generate


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Locale

@Serializable
data class RandomDogImage(
    @SerialName("message")
    val message: String?,
    @SerialName("status")
    val status: String
) {
    companion object {
        fun extractBreed(url: String): String? {
            val parts = url.split("/")
            return if (parts.size > 4) {
                parts[4]
                    .split("-")
                    .joinToString(" ") { word ->
                        word.replaceFirstChar {
                            if (it.isLowerCase()) {
                                it.titlecase(Locale.ROOT)
                            } else {
                                it.toString()
                            }
                        }
                    }

            } else null
        }

        fun extractName(url: String): String? {
            val fileName = url.substringAfterLast("/")
            return fileName.substringBeforeLast(".")
        }
    }


}