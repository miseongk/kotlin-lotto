package domain

class Result {

    private val winningTypeCounts: MutableMap<WinningType, Int> = initWinningTypeCounts()

    private fun initWinningTypeCounts(): MutableMap<WinningType, Int> {
        val result: MutableMap<WinningType, Int> = mutableMapOf()
        for (value in WinningType.entries) {
            result[value] = DEFAULT_COUNT_VALUE
        }
        return result
    }

    fun addEachResult(numberOfMatchedBall: Int, isBonusBallMatched: Boolean) {
        try {
            val winningType = WinningType.findType(numberOfMatchedBall, isBonusBallMatched)
            winningTypeCounts[winningType] = winningTypeCounts.getOrDefault(winningType, DEFAULT_COUNT_VALUE) + 1
        } catch (e: NoSuchElementException) {
            return
        }
    }

    fun getStatistics(): Map<WinningType, Int> {
        return winningTypeCounts.toMap()
    }

    fun calculateRateOfReturn(purchaseAmount: Int): Double {
        val earned = winningTypeCounts.entries.sumOf { (type, count) -> type.price * count }
        return earned / purchaseAmount.toDouble() * 100
    }

    companion object {
        private const val DEFAULT_COUNT_VALUE = 0
    }
}
