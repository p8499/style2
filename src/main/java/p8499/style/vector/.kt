package p8499.style.vector

val Boolean.d: String get() = if (this) "1" else "0"
val Int.d: String get() = toString()
val Float.d: String get() = String.format("%.2f", this)
val Double.d: String get() = String.format("%.2f", this)