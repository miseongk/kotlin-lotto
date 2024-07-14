import domain.AutoLottoMaker
import domain.Lotto
import domain.LottoGame
import view.InputView
import view.OutputView

fun main(args: Array<String>) {
    val lottoGame = LottoGame()

    val amount = InputView.readAmount()
    val lottos = lottoGame.buyLotto(amount, AutoLottoMaker())
    OutputView.printLottoNumbers(lottos)

    val winningNumbers = InputView.readWinningNumbers()
    val bonusNumber = InputView.readBonusNumber()
    lottoGame.matchLottos(Lotto(winningNumbers), bonusNumber, lottos)

    OutputView.printStatistics(lottoGame.getStatistics())
    OutputView.printRateOfReturn(lottoGame.getRateOfReturn(amount))
}
