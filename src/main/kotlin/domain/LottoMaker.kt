package domain

interface LottoMaker {

    fun makeNumbers(numberOfTickets: Int): List<Lotto>
}
