import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun commissionCalculatorForMir() {
        val type: String = "Мир"
        val amount: Double = 1000.0
        val previous: Double = 0.0

        val result = commissionCalculator(type, previous, amount)

        assertEquals(0, result)
    }

    @Test
    fun calculatorMinCommissionForVisa() {
        val type: String = "Visa"
        val amount: Double = 1000.0
        val previous: Double = 0.0

        val result = commissionCalculator(type, previous, amount)

        assertEquals(35, result)
    }

    @Test
    fun calculatorCommissionForVisa() {
        val type: String = "Visa"
        val amount: Double = 35000.0
        val previous: Double = 0.0

        val result = commissionCalculator(type, previous, amount)

        assertEquals(262, result)
    }

    @Test
    fun calculatorCommissionForMastercardLimitHasNotBeenExceeded() {
        val type: String = "Mastercard"
        val amount: Double = 0.0
        val previous: Double = 74000.0

        val result = commissionCalculator(type, previous, amount)

        assertEquals(0, result)
    }

    @Test
    fun calculatorCommissionForMastercardLimitHasBeenExceeded() {
        val type: String = "Mastercard"
        val amount: Double = 20000.0
        val previous: Double = 76000.0

        val result = commissionCalculator(type, previous, amount)

        assertEquals(140, result)
    }

    @Test
    fun calculatorCommissionForMastercardWithSmallTransaction() {
        val type: String = "Mastercard"
        val amount: Double = 20000.0
        val previous: Double = 60000.0

        val result = commissionCalculator(type, previous, amount)

        assertEquals(50, result)
    }

    @Test
    fun checkWrongType() {
        val type: String = "UnionPay"
        val amount: Double = 150000.0
        val previous: Double = 0.0

        val result = commissionCalculator(type, previous, amount)

        assertEquals(ERROR_TYPE, result)
    }

    @Test
    fun checkWrongDailyLimit() {
        val type: String = "Visa"
        val amount: Double = 200000.0
        val previous: Double = 0.0

        val result = commissionCalculator(type, previous, amount)

        assertEquals(ERROR_LIMIT, result)
    }

    @Test
    fun checkWrongMonthlyLimit() {
        val type: String = "Visa"
        val amount: Double = 100000.0
        val previous: Double = 600000.0

        val result = commissionCalculator(type, previous, amount)

        assertEquals(ERROR_LIMIT, result)
    }

}