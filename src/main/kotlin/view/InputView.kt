package view

object InputView {

    fun readAmount(): Int {
        println("구입금액을 입력해 주세요.")
        val input = readLine()
        validateInt(input)
        return input.toInt()
    }

    private fun validateInt(input: String) {
        input.toIntOrNull() ?: throw IllegalArgumentException("[ERROR] 숫자가 아닙니다.")
    }

    fun readWinningNumbers(): List<Int> {
        println("\n당첨 번호를 입력해 주세요.")
        val input = readLine()
        return input.split(",")
            .map { it.trim() }
            .onEach { validateInt(it) }
            .map { it.toInt() }
    }

    fun readBonusNumber(): Int {
        println("\n보너스 번호를 입력해 주세요.")
        val input = readLine()
        validateInt(input)
        return input.toInt()
    }

    private fun readLine() = readlnOrNull() ?: throw IllegalArgumentException("[ERROR] 입력값이 없습니다.")
}
