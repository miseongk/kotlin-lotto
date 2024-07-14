package domain

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CustomLottoMakerTest : StringSpec({

    "로또 번호를 수동으로 입력하여 로또를 반환받는다" {
        val customLottoMaker: LottoMaker =
            CustomLottoMaker(
                listOf(
                    listOf(1, 2, 3, 4, 5, 6),
                    listOf(11, 12, 13, 14, 15, 16)
                )
            )

        val lottos = customLottoMaker.makeNumbers(2)

        assertSoftly {
            lottos.size shouldBe 2
            lottos[0].numbers[0] shouldBe 1
            lottos[0].numbers[1] shouldBe 2
            lottos[1].numbers[0] shouldBe 11
            lottos[1].numbers[1] shouldBe 12
        }
    }
})
