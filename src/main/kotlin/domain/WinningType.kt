package domain

enum class WinningType(
    val numberOfBall: Int,
    val bonusBall: Boolean,
    val price: Long
) {

    THREE(3, false, 5000),
    FOUR(4, false, 50000),
    FIVE(5, false, 1500000),
    FIVE_BONUS(5, true, 30000000),
    SIX(6, false, 2000000000),
    ;

    companion object {

        private const val ALLOWED_BONUS_BALL = 5

        fun findType(numberOfBall: Int, bonusBall: Boolean): WinningType {
            return entries.firstOrNull {
                it.numberOfBall == numberOfBall
                        && it.bonusBall == if (it.numberOfBall == ALLOWED_BONUS_BALL) bonusBall else false
            } ?: throw NoSuchElementException("타입을 찾을 수 없습니다.")
        }
    }
}
