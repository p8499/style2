package p8499.style.vector

class PathData(var point: Pair<Float, Float> = 0f to 0f, private var oldPoint: Pair<Float, Float>? = null, val builder: StringBuilder = StringBuilder()) {
    private fun goto(x: Float, y: Float) {
        point = x to y
    }

    private fun move(x: Float, y: Float) {
        point = point.first + x to point.second + y
    }

    private fun backup() {
        oldPoint = point
    }

    private fun restore() {
        oldPoint?.also { M(it.first, it.second) }
        oldPoint = null
    }

    fun output(): String = builder.toString()

    fun M(x: Float, y: Float) {
        builder.append("M${x.d},${y.d}")
        goto(x, y)
    }

    fun m(x: Float, y: Float) {
        builder.append("m${x.d},${y.d}")
        move(x, y)
    }

    fun Z() {
        builder.append("Z")
    }

    fun z() {
        builder.append("z")
    }

    fun L(x: Float, y: Float) {
        builder.append("L${x.d},${y.d}")
    }

    fun l(x: Float, y: Float) {
        builder.append("l${x.d},${y.d}")
    }

    fun H(x: Float) {
        builder.append("H${x.d}")
    }

    fun h(x: Float) {
        builder.append("h${x.d}")
    }

    fun V(y: Float) {
        builder.append("V${y.d}")
    }

    fun v(y: Float) {
        builder.append("v${y.d}")
    }

    fun A(rx: Float, ry: Float, x_axis_rotation: Int, large_arc_flag: Boolean, sweep_flag: Boolean, x: Float, y: Float) {
        builder.append("A${rx.d},${ry.d},${x_axis_rotation.d},${large_arc_flag.d},${sweep_flag.d},${x.d},${y.d}")
    }

    fun a(rx: Float, ry: Float, x_axis_rotation: Int, large_arc_flag: Boolean, sweep_flag: Boolean, x: Float, y: Float) {
        builder.append("a${rx.d},${ry.d},${x_axis_rotation.d},${large_arc_flag.d},${sweep_flag.d},${x.d},${y.d}")
    }

    fun circle(x: Float, y: Float) {
        val distance = Math.sqrt(Math.pow((point.first).toDouble(), 2.toDouble()) + Math.pow((point.second).toDouble(), 2.toDouble())).toFloat()
        a(distance / 2, distance / 2, 0, false, false, x, y)
        a(distance / 2, distance / 2, 0, false, false, -x, -y)
        z()
    }

    fun ring(x: Float, y: Float, width: Float) {
        val distanceOutter = Math.sqrt(Math.pow((x).toDouble(), 2.toDouble()) + Math.pow((y).toDouble(), 2.toDouble())).toFloat()
        val distanceInner = distanceOutter - width * 2
        val innerStartX = x * width / distanceOutter
        val innerStartY = y * width / distanceOutter
        val innerEndX = x * distanceInner / distanceOutter
        val innerEndY = y * distanceInner / distanceOutter
        a(distanceOutter / 2, distanceOutter / 2, 0, false, false, x, y)
        a(distanceOutter / 2, distanceOutter / 2, 0, false, false, -x, -y)
        z()
        m(innerStartX, innerStartY)
        a(distanceInner / 2, distanceInner / 2, 0, false, true, innerEndX, innerEndY)
        a(distanceInner / 2, distanceInner / 2, 0, false, true, -innerEndX, -innerEndY)
        z()
    }

    fun sector(orientation: Int, angle: Int, side: Float) {
        val startAngle = (orientation - angle / 2) * Math.PI / 180
        val endAngle = (orientation + angle / 2) * Math.PI / 180
        val startX = (Math.cos(startAngle) * side).toFloat()
        val startY = (Math.sin(startAngle) * side).toFloat()
        val endX = (Math.cos(endAngle) * side).toFloat()
        val endY = (Math.sin(endAngle) * side).toFloat()
        l(startX, startY)
        a(side, -side, 0, angle > 180, false, endX - startX, endY - startY)
        z()
    }

    fun arc(orientation: Int, angle: Int, distance: Float, width: Float) {
        val startAngle = (orientation - angle / 2) * Math.PI / 180
        val endAngle = (orientation + angle / 2) * Math.PI / 180
        val innerStartX = (Math.cos(startAngle) * distance).toFloat()
        val innerStartY = (Math.sin(startAngle) * distance).toFloat()
        val innerEndX = (Math.cos(endAngle) * distance).toFloat()
        val innerEndY = (Math.sin(endAngle) * distance).toFloat()
        val outerStartX = (Math.cos(endAngle) * (distance + width)).toFloat()
        val outerStartY = (Math.sin(endAngle) * (distance + width)).toFloat()
        val outerEndX = (Math.cos(startAngle) * (distance + width)).toFloat()
        val outerEndY = (Math.sin(startAngle) * (distance + width)).toFloat()
        backup()
        m(innerStartX, innerStartY)
        a(distance, -distance, 0, angle > 180, false, innerEndX - innerStartX, innerEndY - innerStartY)
        l(outerStartX - innerEndX, outerStartY - innerEndY)
        a(distance + width, distance + width, 0, angle > 180, false, outerEndX - outerStartX, outerEndY - outerStartY)
        z()
        restore()
    }
}