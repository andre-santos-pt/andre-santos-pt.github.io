import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class Tests02 {

    @Test
    fun testFactorial() {
        assertEquals(120, factorialRec(5))
        assertEquals(120, factorialTail(5))
        assertEquals(120, factorial(5))
    }

    @Test
    fun testAbs() {
        assertEquals(2, abs(-2) )
        assertEquals(2, abs(2) )
    }

    @Test
    fun testApproxEqual() {
        assertTrue(approxEqual(1.000000001, 1.00000002 ))
    }

    @Test
    fun testSqrt() {
        assertTrue(approxEqual(3.0, sqrt(9.0)))
    }
}