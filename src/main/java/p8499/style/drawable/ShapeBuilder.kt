package p8499.style.drawable

import p8499.style.color.Argb
import p8499.style.color.Color
import p8499.style.dp

class ShapeBuilder(
        var shapeType: ShapeType = ShapeType.Rectangle,
        var paddingTop: String = 0.dp,
        var paddingBottom: String = 0.dp,
        var paddingLeft: String = 0.dp,
        var paddingRight: String = 0.dp,
        var backgroundColor: Color = Argb(0, 0, 0, 0),
        var borderWidth: String = 0.dp,
        var borderColor: Color = Argb(0, 0, 0, 0),
        var radiusTopLeft: String = 0.dp,
        var radiusTopRight: String = 0.dp,
        var radiusBottomLeft: String = 0.dp,
        var radiusBottomRight: String = 0.dp) {
    fun radius(dp: String) {
        radiusTopLeft = dp
        radiusTopRight = dp
        radiusBottomLeft = dp
        radiusBottomRight = dp
    }

    fun radiusTop(dp: String) {
        radiusTopLeft = dp
        radiusTopRight = dp
    }

    fun radiusBottom(dp: String) {
        radiusBottomLeft = dp
        radiusBottomRight = dp
    }

    fun radiusLeft(dp: String) {
        radiusTopLeft = dp
        radiusBottomLeft = dp
    }

    fun radiusRight(dp: String) {
        radiusTopRight = dp
        radiusBottomRight = dp
    }

    fun padding(dp: String) {
        paddingTop = dp
        paddingBottom = dp
        paddingLeft = dp
        paddingRight = dp
    }

    fun paddingVertical(dp: String) {
        paddingTop = dp
        paddingBottom = dp
    }

    fun paddingHorizontal(dp: String) {
        paddingLeft = dp
        paddingRight = dp
    }
    fun build():Shape= Shape(
            shapeType,
            paddingTop,
            paddingBottom,
            paddingLeft,
            paddingRight,
            backgroundColor,
            borderWidth,
            borderColor,
            radiusTopLeft,
            radiusTopRight,
            radiusBottomLeft,
            radiusBottomRight)
    companion object {
        fun from(shape:Shape):ShapeBuilder =ShapeBuilder(
                shape.shapeType,
                shape.paddingTop,
                shape.paddingBottom,
                shape.paddingLeft,
                shape.paddingRight,
                shape.backgroundColor,
                shape.borderWidth,
                shape.borderColor,
                shape.radiusTopLeft,
                shape.radiusTopRight,
                shape.radiusBottomLeft,
                shape.radiusBottomRight)
    }
}
