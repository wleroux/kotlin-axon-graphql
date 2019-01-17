package sample.counter

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

data class CounterId @JsonCreator constructor(
        @JsonValue val value: String
) {
    companion object {
        @JvmStatic
        fun valueOf(value: String) = CounterId(value)
    }

    override fun toString() = value
}

