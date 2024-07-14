package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class WinningTypeTest : StringSpec({


    "볼 개수와 보너스 볼 일치 여부로 타입을 찾는다" {
        forAll(
            row(3, false, WinningType.THREE),
            row(3, true, WinningType.THREE),
            row(5, false, WinningType.FIVE),
            row(5, true, WinningType.FIVE_BONUS)
        ) { numberOfBall, bonusBall, expected ->
            val type = WinningType.findType(numberOfBall, bonusBall)
            type shouldBe expected
        }
    }

    "일치하는 타입이 없는 경우 예외가 발생한다" {
        forAll(
            row(2, false),
            row(2, true),
            row(7, false)
        ) { numberOfBall, bonusBall ->
            shouldThrow<NoSuchElementException> {
                WinningType.findType(numberOfBall, bonusBall)
            }
        }
    }
})
