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
fun intersect(circle: Circle, line: Line): Array<Point> = axis.intersection(2, line.p1.x, line.p1.y, line.p2.x, line.p2.y, circle.center.x, circle.center.y, circle.radius)
        .map { it as MWNumericArray }.filter { !it.isEmpty }.map { Point(it.getDouble(1), it.getDouble(2)) }.toTypedArray()

fun distance(line: Line, point: Point): Double = axis.distance(1, line.p1.x, line.p1.y, line.p2.x, line.p2.y, point.x, point.y)
        .map { it as MWNumericArray }[0].double

fun projection(line: Line, point: Point): Point = axis.projection(1, line.p1.x, line.p1.y, line.p2.x, line.p2.y, point.x, point.y)
        .map { it as MWNumericArray }.map { Point(it.getDouble(1), it.getDouble(2)) }[0]
