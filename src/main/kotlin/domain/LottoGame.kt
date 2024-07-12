package domain

class LottoGame {

    private val result: Result = Result()

    fun buyLotto(amount: Int, lottoMaker: LottoMaker): List<Lotto> {
        validateAmount(amount)
        val numberOfTickets = amount / AMOUNT_UNIT
        return lottoMaker.makeNumbers(numberOfTickets)
    }

    private fun validateAmount(amount: Int) {
        require(amount > MIN_AMOUNT) {
            "[ERROR] 금액은 ${MIN_AMOUNT}원 보다 커야합니다."
        }
        require(amount % AMOUNT_UNIT == 0) {
            "[ERROR] 금액은 ${AMOUNT_UNIT}원 단위여야 합니다."
        }
    }

    fun matchLottos(winningLotto: Lotto, bonusNumber: Int, lottos: List<Lotto>) {
        lottos.forEach {
            val numberOfMatchedBall = it.matchBall(winningLotto)
            val isBonusBallMatched = it.isBonusBallMatched(bonusNumber)
            result.addEachResult(numberOfMatchedBall, isBonusBallMatched)
        }
    }

    fun getStatistics(): Map<WinningType, Int> {
        return result.getStatistics()
    }

    companion object {
        private const val MIN_AMOUNT = 0
        private const val AMOUNT_UNIT = 1000
    }
}
