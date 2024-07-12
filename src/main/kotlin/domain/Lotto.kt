package domain

class Lotto(var numbers: List<Int>) {

    init {
        require(numbers.size == LOTTO_NUMBER_SIZE) {
            "[ERROR] 로또 번호는 총 ${LOTTO_NUMBER_SIZE}개여야 합니다."
        }
        numbers = numbers.sorted()
    }

    fun matchBall(winningLotto: Lotto): Int {
        return winningLotto.numbers.count { it in numbers }
    }

    fun isBonusBallMatched(bonusNumber: Int): Boolean {
        return bonusNumber in numbers
    }

    companion object {
        private const val LOTTO_NUMBER_SIZE = 6
    }
}
