package view

import domain.Lotto
import domain.WinningType
import kotlin.math.round

object OutputView {

    fun printLottoNumbers(lottos: List<Lotto>) {
        println("\n${lottos.size}개를 구매했습니다.")
        lottos.forEach {
            println(it.numbers.joinToString(separator = ", ", prefix = "[", postfix = "]"))
        }
    }

    fun printStatistics(statistics: Map<WinningType, Int>) {
        println("\n당첨 통계\n---")
        for (statistic in statistics) {
            val winningType = statistic.key
            val count = statistic.value
            println("${winningType.numberOfBall}개 일치 (${winningType.price}원) - ${count}개")
        }
    }

    fun printRateOfReturn(rateOfReturn: Double) {
        println("총 수익률은 ${round(rateOfReturn * 100) / 100}%입니다.")
    }
}
