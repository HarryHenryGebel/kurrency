import kotlin.math.min
import kotlin.math.pow

/**
 *
 */
class Kurrency(
    number: Number,
    val digits: Int = 2,
    val precision: Int = 3
) : Number() {
    private val multiplier = 10.0.pow(precision).toLong()
    private val outputMultiplier = 10.0.pow(digits).toLong()
    private val subunits = (number.toDouble() * multiplier).toLong()

    operator fun unaryPlus(): Kurrency {
        return this
    }

    operator fun unaryMinus(): Kurrency {
        return Kurrency(-subunits.toDouble() * multiplier, digits, precision)
    }

    // TODO special cases when both sides are Kurrency instances

    operator fun plus(number: Number): Kurrency {
        return Kurrency(this.toDouble() + number.toDouble(), digits, precision)
    }

    operator fun minus(number: Number): Kurrency {
        return Kurrency(this.toDouble() - number.toDouble(), digits, precision)
    }

    operator fun times(number: Number): Kurrency {
        return Kurrency(this.toDouble() * number.toDouble(), digits, precision)
    }

    operator fun div(number: Number): Kurrency {
        return Kurrency(this.toDouble() / number.toDouble(), digits, precision)
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Kurrency -> {
                val digits = min(this.digits, other.digits)
                val precision = min(this.precision, other.precision)
                Kurrency(this.toDouble(), digits, precision).subunits ==
                        Kurrency(other.toDouble(), digits, precision).subunits
            }
            is Number -> {
                Kurrency(this.toDouble(), digits, precision).subunits ==
                        Kurrency(other.toDouble(), digits, precision).subunits
            }
            else -> false
        }
    }

    operator fun compareTo(number: Number): Int {
        return (this - number).toInt()
    }

    override fun toByte(): Byte {
        return (subunits.toDouble() / outputMultiplier).toInt().toByte()
    }

    override fun toChar(): Char {
        return (subunits.toDouble() / outputMultiplier).toChar()
    }

    override fun toDouble(): Double {
        return (subunits.toDouble() / outputMultiplier)
    }

    override fun toFloat(): Float {
        return (subunits.toDouble() / outputMultiplier).toFloat()
    }

    override fun toInt(): Int {
        return (subunits.toDouble() / outputMultiplier).toInt()
    }

    override fun toLong(): Long {
        return (subunits.toDouble() / outputMultiplier).toLong()
    }

    override fun toShort(): Short {
        return (subunits.toDouble() / outputMultiplier).toInt().toShort()
    }
}
