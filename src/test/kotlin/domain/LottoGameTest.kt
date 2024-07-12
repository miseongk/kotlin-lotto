package domain

import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class LottoGameTest : StringSpec({


    "로또 구입 금액이 0원 이하이면 예외가 발생한다" {
        val lottoGame = LottoGame()

        forAll(
            row(-1), row(-1000), row(0)
        ) { amount ->
            shouldThrow<IllegalArgumentException> {
                lottoGame.buyLotto(amount, AutoLottoMaker())
            }
        }
    }

    "로또 구입 금액이 1000원 단위가 아니면 예외가 발생한다" {
        val lottoGame = LottoGame()

        forAll(
            row(900), row(10), row(1)
        ) { amount ->
            shouldThrow<IllegalArgumentException> {
                lottoGame.buyLotto(amount, AutoLottoMaker())
            }
        }
    }

    "구입 금액에 맞는 로또를 산다" {
        val lottoGame = LottoGame()

        forAll(
            row(14000, 14), row(3000, 3), row(1000, 1)
        ) { amount, expected ->
            val lottos = lottoGame.buyLotto(amount, AutoLottoMaker())
            lottos.size shouldBe expected
        }
    }

    "로또를 매칭한다" {
        val lottoGame = LottoGame()

        val winningLotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val bonusNumber = 7
        val lottos = listOf(
            Lotto(listOf(1, 2, 3, 7, 8, 9)),
            Lotto(listOf(1, 2, 3, 7, 8, 9)),
            Lotto(listOf(1, 2, 3, 4, 5, 7))
        )
        lottoGame.matchLottos(winningLotto, bonusNumber, lottos)

        val statistics = lottoGame.getStatistics()
        assertSoftly {
            statistics[WinningType.THREE] shouldBe 2
            statistics[WinningType.FOUR] shouldBe 0
            statistics[WinningType.FIVE] shouldBe 0
            statistics[WinningType.FIVE_BONUS] shouldBe 1
            statistics[WinningType.SIX] shouldBe 0
        }
    }
})
