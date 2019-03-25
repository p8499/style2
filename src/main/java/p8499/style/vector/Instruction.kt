package p8499.style.vector

internal sealed class Instruction {
    abstract val name: String
    abstract fun scale(proportion: Double)
    class M0(var point: Point) : Instruction() {
        override val name get() = "M"
        override fun toString(): String = "M${point.x.d},${point.y.d}"
        override fun scale(proportion: Double) {
            point = Point(point.x * proportion, point.y * proportion)
        }
    }

    class M1(var point: Point) : Instruction() {
        override val name: String get() = "m"
        override fun toString(): String = "m${point.x.d},${point.y.d}"
        override fun scale(proportion: Double) {
            point = Point(point.x * proportion, point.y * proportion)
        }
    }

    class Z0() : Instruction() {
        override val name: String get() = "Z"
        override fun toString(): String = "Z"
        override fun scale(proportion: Double) {}
    }

    class Z1() : Instruction() {
        override val name: String get() = "z"
        override fun toString(): String = "z"
        override fun scale(proportion: Double) {}
    }

    class L0(var point: Point) : Instruction() {
        override val name: String get() = "L"
        override fun toString(): String = "L${point.x.d},${point.y.d}"
        override fun scale(proportion: Double) {
            point = Point(point.x * proportion, point.y * proportion)
        }
    }

    class L1(var point: Point) : Instruction() {
        override val name: String get() = "l"
        override fun toString(): String = "l${point.x.d},${point.y.d}"
        override fun scale(proportion: Double) {
            point = Point(point.x * proportion, point.y * proportion)
        }
    }

    class H0(var x: Double) : Instruction() {
        override val name: String get() = "H"
        override fun toString(): String = "H${x.d}"
        override fun scale(proportion: Double) {
            x *= proportion
        }
    }

    class H1(var x: Double) : Instruction() {
        override val name: String get() = "h"
        override fun toString(): String = "h${x.d}"
        override fun scale(proportion: Double) {
            x *= proportion
        }
    }

    class V0(var y: Double) : Instruction() {
        override val name: String get() = "V"
        override fun toString(): String = "V${y.d}"
        override fun scale(proportion: Double) {
            y *= proportion
        }
    }

    class V1(var y: Double) : Instruction() {
        override val name: String get() = "v"
        override fun toString(): String = "v${y.d}"
        override fun scale(proportion: Double) {
            y *= proportion
        }
    }

    class Q0(var point1: Point, var point: Point) : Instruction() {
        override val name: String get() = "Q"
        override fun toString(): String = "Q${point1.x.d},${point1.y.d},${point.x.d},${point.y.d}"
        override fun scale(proportion: Double) {
            point1 = Point(point1.x * proportion, point1.y * proportion)
            point = Point(point.x * proportion, point.y * proportion)
        }
    }

    class Q1(var point1: Point, var point: Point) : Instruction() {
        override val name: String get() = "q"
        override fun toString(): String = "q${point1.x.d},${point1.y.d},${point.x.d},${point.y.d}"
        override fun scale(proportion: Double) {
            point1 = Point(point1.x * proportion, point1.y * proportion)
            point = Point(point.x * proportion, point.y * proportion)
        }
    }

    class A0(var rx: Double, var ry: Double, var x_axis_rotation: Int, var large_arc_flag: Boolean, var sweep_flag: Boolean, var point: Point) : Instruction() {
        override val name: String get() = "A"
        override fun toString(): String = "A${rx.d},${ry.d},${x_axis_rotation.d},${large_arc_flag.d},${sweep_flag.d},${point.x.d},${point.y.d}"
        override fun scale(proportion: Double) {
            rx *= proportion
            ry *= proportion
            point = Point(point.x * proportion, point.y * proportion)
        }
    }

    class A1(var rx: Double, var ry: Double, var x_axis_rotation: Int, var large_arc_flag: Boolean, var sweep_flag: Boolean, var point: Point) : Instruction() {
        override val name: String get() = "a"
        override fun toString(): String = "a${rx.d},${ry.d},${x_axis_rotation.d},${large_arc_flag.d},${sweep_flag.d},${point.x.d},${point.y.d}"
        override fun scale(proportion: Double) {
            rx *= proportion
            ry *= proportion
            point = Point(point.x * proportion, point.y * proportion)
        }
    }
}

