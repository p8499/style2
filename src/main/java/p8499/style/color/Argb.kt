package p8499.style.color

class Argb(val a: Int, val r: Int, val g: Int, val b: Int) : Cloneable, Color {
    constructor(r: Int, g: Int, b: Int) : this(0xff, r, g, b)

    override fun toString(): String {
        val sb = StringBuffer("#")
        val a = Integer.toHexString(a and 0xff)
        val r = Integer.toHexString(r and 0xff)
        val g = Integer.toHexString(g and 0xff)
        val b = Integer.toHexString(b and 0xff)
        sb.append(if (a.length < 2) "0" + a else a)
        sb.append(if (r.length < 2) "0" + r else r)
        sb.append(if (g.length < 2) "0" + g else g)
        sb.append(if (b.length < 2) "0" + b else b)
        return sb.toString()
    }

    override public fun clone(): Argb = Argb(a, r, g, b)

    fun hsl(): Hsl {
        val r = r / 255f
        val g = g / 255f
        val b = b / 255f
        val max = Math.max(r, Math.max(g, b))
        val min = Math.min(r, Math.min(g, b))
        var h: Float
        val s: Float
        val l = (max + min) / 2f

        if (max == min) {
            s = 0f
            h = s
        } else {
            val d = max - min
            s = if (l > 0.5f) d / (2f - max - min) else d / (max + min)
            h = when (max) {
                r -> (g - b) / d + (if (g < b) 6f else 0f)
                g -> (b - r) / d + 2f
                else -> (r - g) / d + 4f
            }
            h /= 6f
        }
        return Hsl(h * 255f, s * 255f, l * 255f)
    }
}
