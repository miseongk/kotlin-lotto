package domain

class CustomLottoMaker(val numbers: List<List<Int>>) : LottoMaker {

    override fun makeNumbers(numberOfTickets: Int): List<Lotto> {
        validateNumberOfLotto(numberOfTickets)
        return List(numberOfTickets) {
            Lotto(numbers[it])
        }
    }

    private fun validateNumberOfLotto(numberOfTickets: Int) {
        require(numberOfTickets == numbers.size) {
            "[ERROR] 구매한 로또의 수와 입력한 로또의 수가 일치하지 않습니다."
        }
    }
}
