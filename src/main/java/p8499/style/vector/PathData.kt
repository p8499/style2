package p8499.style.vector

import java.lang.Math.PI

class PathData(var startPoint: Point = origin, var point: Point = origin, val builder: StringBuilder = StringBuilder()) {
    fun output(): String = builder.toString()

    fun M(x: Double, y: Double) {
        builder.append("M${x.d},${y.d}")
        startPoint = Point(x, y)
        point = Point(x, y)
    }

    fun m(x: Double, y: Double) {
        builder.append("m${x.d},${y.d}")
        startPoint += Point(x, y)
        point += Point(x, y)
    }

    fun Z() {
        builder.append("Z")
        point = startPoint
    }

    fun z() {
        builder.append("z")
        point = startPoint
    }

    fun L(x: Double, y: Double) {
        builder.append("L${x.d},${y.d}")
        point = Point(x, y)
    }

    fun l(x: Double, y: Double) {
        builder.append("l${x.d},${y.d}")
        point += Point(x, y)
    }

    fun H(x: Double) {
        builder.append("H${x.d}")
        point = Point(x, point.y)
    }

    fun h(x: Double) {
        builder.append("h${x.d}")
        point += Point(x, 0.0)
    }

    fun V(y: Double) {
        builder.append("V${y.d}")
        point = Point(point.x, y)
    }

    fun v(y: Double) {
        builder.append("v${y.d}")
        point += Point(0.0, y)
    }

    fun Q(x1: Double, y1: Double, x: Double, y: Double) {
        builder.append("Q${x1.d},${y1.d},${x.d},${y.d}")
        point = Point(x, y)
    }

    fun q(x1: Double, y1: Double, x: Double, y: Double) {
        builder.append("q${x1.d},${y1.d},${x.d},${y.d}")
        point += Point(x, y)
    }

    fun A(rx: Double, ry: Double, x_axis_rotation: Int, large_arc_flag: Boolean, sweep_flag: Boolean, x: Double, y: Double) {
        builder.append("A${rx.d},${ry.d},${x_axis_rotation.d},${large_arc_flag.d},${sweep_flag.d},${x.d},${y.d}")
        point = Point(x, y)
    }

    fun a(rx: Double, ry: Double, x_axis_rotation: Int, large_arc_flag: Boolean, sweep_flag: Boolean, x: Double, y: Double) {
        builder.append("a${rx.d},${ry.d},${x_axis_rotation.d},${large_arc_flag.d},${sweep_flag.d},${x.d},${y.d}")
        point += Point(x, y)
    }

    fun Move(p: Point) = M(p.x, p.y)
    fun move(p: Point) = m(p.x, p.y)
    fun Line(p: Point) = L(p.x, p.y)
    fun line(p: Point) = l(p.x, p.y)
    fun Quadratic(p1: Point, p: Point) = Q(p1.x, p1.y, p.x, p.y)
    fun quadratic(p1: Point, p: Point) = q(p1.x, p1.y, p.x, p.y)
    fun Arc(p: Point, radius: Double = p.distance(point) / 2, largeArg: Boolean = false, sweep: Boolean = false) = A(radius, radius, 0, largeArg, sweep, p.x, p.y)
    fun arc(p: Point, radius: Double = p.distance() / 2, largeArg: Boolean = false, sweep: Boolean = false) = a(radius, radius, 0, largeArg, sweep, p.x, p.y)
    fun Circle(p: Point, sweep: Boolean = false) {
        val p0 = point
        Arc(p, sweep = sweep)
        Arc(p0, sweep = sweep)
        z()
    }

    fun circle(p: Point, sweep: Boolean = false) {
        arc(p, sweep = sweep)
        arc(-p, sweep = sweep)
        z()
    }

    fun Sector(orientation: Double, angle: Double, side: Double) {
        val startAngle = orientation - angle / 2
        val endAngle = orientation + angle / 2
        val start = point.moveOrientation(startAngle, side)
        val end = point.moveOrientation(endAngle, side)
        val p0 = point
        Line(start)
        Arc(end, side, angle > PI)
        Line(p0)
        z()
    }

    fun sector(orientation: Double, angle: Double, side: Double) {
        val startAngle = orientation - angle / 2
        val endAngle = orientation + angle / 2
        val start = origin.moveOrientation(startAngle, side)
        val end = origin.moveOrientation(endAngle, side)
        line(start)
        arc(end, side, angle > PI)
        line(-end)
        z()
    }
}