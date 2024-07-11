package domain

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class AutoLottoMakerTest : StringSpec({

    "각 로또는 6개의 랜덤 숫자를 가지고 있다" {
        val autoLottoMaker: LottoMaker = AutoLottoMaker()

        val lottos = autoLottoMaker.makeNumbers(2)

        assertSoftly {
            lottos.size shouldBe 2
            lottos[0].numbers.size shouldBe 6
            lottos[1].numbers.size shouldBe 6
        }
    }
})
