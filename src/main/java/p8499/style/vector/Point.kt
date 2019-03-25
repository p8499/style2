package p8499.style.vector

import kotlin.math.pow

data class Point(val x: Double, val y: Double) {
    operator fun plus(p: Point): Point = Point(x + p.x, y + p.y)
    operator fun unaryMinus(): Point = Point(-x, -y)
    fun distance(p: Point): Double = Math.sqrt((p.x - x).pow(2) + (p.y - y).pow(2))
    fun distance(): Double = Math.sqrt(x.pow(2) + y.pow(2))
    fun moveRef(p2: Point, prop: Double): Point = Point(x + (p2.x - x) * prop, y + (p2.y - y) * prop)
    fun moveXY(dx: Double, dy: Double): Point = Point(x + dx, y + dy)
    fun moveOrientation(orientation: Double, distance: Double): Point = Point(x + distance * Math.cos(orientation), y + distance * Math.sin(orientation))
    fun moveOrientationX(orientation: Double, dx: Double): Point = Point(x + dx, y + dx * Math.tan(orientation))
    fun moveOrientationY(orientation: Double, dy: Double): Point = Point(x + dy / Math.tan(orientation), y + dy)
    fun up(distance: Double): Point = moveXY(0.0, -distance)
    fun down(distance: Double): Point = moveXY(0.0, distance)
    fun left(distance: Double): Point = moveXY(-distance, 0.0)
    fun right(distance: Double): Point = moveXY(distance, 0.0)
    fun circle(radius: Double): Circle = Circle(this, radius)
    fun line(p2: Point): StraightLine = StraightLine(this, p2)
}