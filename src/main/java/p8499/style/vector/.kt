package p8499.style.vector

import com.mathworks.toolbox.javabuilder.MWNumericArray
import p8499.matlab.Axis
import java.lang.Math.PI

internal val Boolean.d: String get() = if (this) "1" else "0"
internal val Int.d: String get() = toString()
internal val Float.d: String get() = String.format("%.2f", this)
internal val Double.d: String get() = String.format("%.2f", this)
val Int.angle: Double get() = this * PI / 180
val origin = Point(0.0, 0.0)

val axis = Axis()
fun intersect(circle: Circle, straightLine: StraightLine): Array<Point> = axis.intersection(2, straightLine.p1.x, straightLine.p1.y, straightLine.p2.x, straightLine.p2.y, circle.center.x, circle.center.y, circle.radius)
        .map { it as MWNumericArray }.filter { !it.isEmpty }.map { Point(it.getDouble(1), it.getDouble(2)) }.toTypedArray()

fun distance(straightLine: StraightLine, point: Point): Double = axis.distance(1, straightLine.p1.x, straightLine.p1.y, straightLine.p2.x, straightLine.p2.y, point.x, point.y)
        .map { it as MWNumericArray }[0].double

fun projection(straightLine: StraightLine, point: Point): Point = axis.projection(1, straightLine.p1.x, straightLine.p1.y, straightLine.p2.x, straightLine.p2.y, point.x, point.y)
        .map { it as MWNumericArray }.map { Point(it.getDouble(1), it.getDouble(2)) }[0]

fun PathData.Circle(p: Point, sweep: Boolean = false) {
    val p0 = point
    Arc(p, sweep = sweep)
    Arc(p0, sweep = sweep)
    close()
}

fun PathData.circle(p: Point, sweep: Boolean = false) {
    arc(p, sweep = sweep)
    arc(-p, sweep = sweep)
    close()
}

fun PathData.Sector(orientation: Double, angle: Double, side: Double) {
    val startAngle = orientation - angle / 2
    val endAngle = orientation + angle / 2
    val start = point.moveOrientation(startAngle, side)
    val end = point.moveOrientation(endAngle, side)
    val p0 = point
    Line(start)
    Arc(end, side, angle > PI)
    Line(p0)
    close()
}

fun PathData.sector(orientation: Double, angle: Double, side: Double) {
    val startAngle = orientation - angle / 2
    val endAngle = orientation + angle / 2
    val start = origin.moveOrientation(startAngle, side)
    val end = origin.moveOrientation(endAngle, side)
    line(start)
    arc(end, side, angle > PI)
    line(-end)
    close()
}