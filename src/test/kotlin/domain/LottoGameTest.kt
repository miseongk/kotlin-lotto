package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class LottoGameTest : StringSpec({

    val lottoGame = LottoGame()

    "로또 구입 금액이 0원 이하이면 예외가 발생한다" {
        forAll(
            row(-1), row(-1000), row(0)
        ) { amount ->
            shouldThrow<IllegalArgumentException> {
                lottoGame.buyLotto(amount, AutoLottoMaker())
            }
        }
    }

    "로또 구입 금액이 1000원 단위가 아니면 예외가 발생한다" {
        forAll(
            row(900), row(10), row(1)
        ) { amount ->
            shouldThrow<IllegalArgumentException> {
                lottoGame.buyLotto(amount, AutoLottoMaker())
            }
        }
    }

    "구입 금액에 맞는 로또를 산다" {
        forAll(
            row(14000, 14), row(3000, 3), row(1000, 1)
        ) { amount, expected ->
            val lottos = lottoGame.buyLotto(amount, AutoLottoMaker())
            lottos.size shouldBe expected
        }
    }
})
