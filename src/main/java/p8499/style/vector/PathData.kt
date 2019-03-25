package p8499.style.vector

class PathData() {
    internal var startPoint: Point = origin
    internal var point: Point = origin
    internal val instructions: MutableList<Instruction> = mutableListOf()
    override fun toString(): String = StringBuilder().also { builder -> instructions.forEach { builder.append(it) } }.toString()
    fun scale(proportion: Double) = instructions.forEach { it.scale(proportion) }
    fun Move(point: Point) = instructions.plusAssign(Instruction.M0(point))
    fun move(point: Point) = instructions.plusAssign(Instruction.M1(point))
    fun Close() = instructions.plusAssign(Instruction.Z0())
    fun close() = instructions.plusAssign(Instruction.Z1())
    fun Line(point: Point) = instructions.plusAssign(Instruction.L0(point))
    fun line(point: Point) = instructions.plusAssign(Instruction.L1(point))
    fun Horizontal(x: Double) = instructions.plusAssign(Instruction.H0(x))
    fun horizontal(x: Double) = instructions.plusAssign(Instruction.H1(x))
    fun Vertical(y: Double) = instructions.plusAssign(Instruction.V0(y))
    fun vertical(y: Double) = instructions.plusAssign(Instruction.V1(y))
    fun Quadratic(point1: Point, point: Point) = instructions.plusAssign(Instruction.Q0(point1, point))
    fun quadratic(point1: Point, point: Point) = instructions.plusAssign(Instruction.Q1(point1, point))
    fun Arc(rx: Double, ry: Double, x_axis_rotation: Int, large_arc_flag: Boolean, sweep_flag: Boolean, point: Point) = instructions.plusAssign(Instruction.A0(rx, ry, x_axis_rotation, large_arc_flag, sweep_flag, point))
    fun arc(rx: Double, ry: Double, x_axis_rotation: Int, large_arc_flag: Boolean, sweep_flag: Boolean, point: Point) = instructions.plusAssign(Instruction.A1(rx, ry, x_axis_rotation, large_arc_flag, sweep_flag, point))
    fun Arc(point: Point, radius: Double = point.distance(point) / 2, largeArg: Boolean = false, sweep: Boolean = false) = Arc(radius, radius, 0, largeArg, sweep, point)
    fun arc(point: Point, radius: Double = point.distance() / 2, largeArg: Boolean = false, sweep: Boolean = false) = arc(radius, radius, 0, largeArg, sweep, point)
}