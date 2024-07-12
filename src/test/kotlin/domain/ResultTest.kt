package domain

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ResultTest : StringSpec({

    "로또 당첨 결과를 추가한다" {
        val result = Result()

        result.addEachResult(3, false)
        result.addEachResult(5, true)
        result.addEachResult(1, false)

        val statistics = result.getStatistics()
        assertSoftly {
            statistics[WinningType.THREE] shouldBe 1
            statistics[WinningType.FIVE_BONUS] shouldBe 1
        }
    }
})
