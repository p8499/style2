package p8499.style.color

data class Hsl(val h: Float, val s: Float, val l: Float) : Cloneable, Color {
    override fun toString(): String = argb().toString()

    override public fun clone(): Hsl = Hsl(h, s, l)
    fun lighter(ratio: Float): Hsl = Hsl(h, s, l + (255 - l) * ratio)
    fun darker(ratio: Float): Hsl = Hsl(h, s, l - l * ratio)
    fun argb(): Argb {
        val h2 = h / 255f
        val s2 = s / 255f
        val l2 = l / 255f
        val r: Float
        val g: Float
        val b: Float
        if (s2 == 0f) {
            b = l2
            g = b
            r = g
        } else {
            val q = if (l2 < 0.5f) l2 * (1f + s2) else l2 + s2 - l2 * s2
            val p = 2f * l2 - q
            r = hue2rgb(p, q, h2 + 1f / 3f)
            g = hue2rgb(p, q, h2)
            b = hue2rgb(p, q, h2 - 1f / 3f)
        }
        return Argb(0xff, (r * 255).toInt(), (g * 255).toInt(), (b * 255).toInt())
    }

    private fun hue2rgb(p: Float, q: Float, t: Float): Float {
        var t2 = t
        if (t2 < 0f) t2 += 1f
        if (t2 > 1f) t2 -= 1f
        if (t2 < 1f / 6f) return p + (q - p) * 6f * t2
        if (t2 < 1f / 2f) return q
        return if (t2 < 2f / 3f) p + (q - p) * (2f / 3f - t2) * 6f else p
    }
}