package domain

import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
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
})
