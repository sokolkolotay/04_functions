const val ERROR_TYPE = -1
const val ERROR_LIMIT = -2


fun main() {
    println("\nЗадача №2 Разная комиссия")
    println(commissionCalculator("Мир", 0.0, 10000.0))
    println(commissionCalculator("Мир", 0.0, 500.0))
    println(commissionCalculator("Mastercard", 0.0, 150000.0))
    println(commissionCalculator("Mastercard", 0.0, 75000.0))
    println(commissionCalculator("Mastercard", 75000.0, 1.0))
    println(commissionCalculator("Mastercard", 70000.0, 5100.0))
    println(commissionCalculator("Visa", 0.0, 100.0))
    println(commissionCalculator("Visa", 0.0, 10000.0))
    println(commissionCalculator("Visa", 0.0, 5000.0))
    println(commissionCalculator("Visa", 0.0, 100000.0))
    println(commissionCalculator("VKPay", 0.0, 100000.0))
    println(commissionCalculator("Mastercard", 0.0, 200000.0))
    println(commissionCalculator("Mastercard", 700000.0, 0.0))
}

fun commissionCalculator(
    cardType: String = "Мир", //тип карты (по умолчанию Мир)
    amountThisMonth: Double = 0.0, //сумма предыдущих переводов в этом месяце (по умолчанию 0 рублей)
    amount: Double // сумма совершаемого перевода
): Int {
    //введем лимиты
    val dailyLimit = 150_000 // лимит в сутки
    val monthlyLimit = 600_000 // лимит в месяц
    val mastercardLimit = 75_000 // лимит по карте MasterCard
    val minCommissionVisa = 35 // минимальная комиссия по карте Visa

    //блокирование операции при превышении дневного лимита
    if (amount > dailyLimit) {
        return ERROR_LIMIT
    }
    //блокирование операции при превышении лимита за месяц
    if (amountThisMonth + amount > monthlyLimit) {
        return ERROR_LIMIT
    }

    //в зависимости от типа карты вычислим сумму комиссии
    when (cardType) {
//        За переводы с карты Mastercard комиссия не взимается,
//        пока не превышен месячный лимит в 75 000 руб.
//        Если лимит превышен, комиссия составит 0,6% + 20 руб.
        "Mastercard" -> {
            if (amountThisMonth < mastercardLimit) {
                val amountRemains = mastercardLimit - amountThisMonth
                if (amount <= amountRemains) {
                    return 0
                } else {
                    val amountCalculated = amount - amountRemains
                    val commission = amountCalculated * 0.006 + 20
                    return commission.toInt()
                }
            } else {
                val commission = amount * 0.006 + 20
                return commission.toInt()
            }
        }
//        За переводы с карты Visa комиссия составит 0,75%, минимальная сумма комиссии 35 руб.
        "Visa" -> {
            val comission = amount * 0.0075
            return if (comission < 35) {
                minCommissionVisa
            } else {
                return comission.toInt()
            }
        }
//        За переводы с карты Мир комиссия не взимается.
        "Мир" -> return 0
        else -> {
            return ERROR_TYPE
        }
    }
}
