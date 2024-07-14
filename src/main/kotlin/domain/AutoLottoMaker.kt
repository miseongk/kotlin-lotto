package domain

import kotlin.random.Random

class AutoLottoMaker : LottoMaker {

    override fun makeNumbers(numberOfTickets: Int): List<Lotto> {
        return List(numberOfTickets) {
            makeLotto()
        }
    }

    private fun makeLotto(): Lotto {
        val numbers: MutableSet<Int> = mutableSetOf()
        while (numbers.size < NUMBER_OF_BALL) {
            val randomNumber = Random.nextInt(START_NUMBER, END_NUMBER + 1)
            numbers.add(randomNumber)
        }
        return Lotto(numbers.toList())
    }

    companion object {
        private const val NUMBER_OF_BALL = 6
        private const val START_NUMBER = 1
        private const val END_NUMBER = 45
    }
}
