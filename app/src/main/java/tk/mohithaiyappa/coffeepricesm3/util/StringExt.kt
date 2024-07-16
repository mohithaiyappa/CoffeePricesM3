package tk.mohithaiyappa.coffeepricesm3.util

fun String.initials(): String {
    val sb = StringBuilder()
    this.split(" ").forEach {
        sb.append(it.first())
    }
    return sb.toString()
}
