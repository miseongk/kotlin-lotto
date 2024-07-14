package domain

import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class LottoTest : StringSpec({

    "로또 번호가 6개가 아니면 예외가 발생한다" {
        val numbers = listOf(1, 2, 3, 4, 5)

        shouldThrow<IllegalArgumentException> {
            Lotto(numbers)
        }
    }

    "로또 번호는 오름차순으로 정렬된다" {
        val numbers = listOf(3, 2, 1, 6, 4, 5)

        val lotto = Lotto(numbers)

        assertSoftly {
            lotto.numbers[0] shouldBe 1
            lotto.numbers[1] shouldBe 2
            lotto.numbers[2] shouldBe 3
            lotto.numbers[3] shouldBe 4
            lotto.numbers[4] shouldBe 5
            lotto.numbers[5] shouldBe 6
        }
    }

    "로또 번호를 매칭하여 매칭되는 볼의 개수를 반환한다" {
        val lotto = Lotto(listOf(4, 5, 6, 7, 8, 9))

        forAll(
            row(Lotto(listOf(11, 12, 13, 14, 15, 16)), 0),
            row(Lotto(listOf(1, 2, 3, 4, 5, 6)), 3),
        ) { winningLotto, expected ->
            val result = lotto.matchBall(winningLotto)
            result shouldBe expected
        }
    }

    "보너스 볼과 매칭되는 볼이 있으면 true, 없으면 false를 반환한다" {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))

        forAll(
            row(1, true),
            row(10, false),
        ) { bonusBallNumber, expected ->
            val result = lotto.isBonusBallMatched(bonusBallNumber)
            result shouldBe expected
        }
    }
})
