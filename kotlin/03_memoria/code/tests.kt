import org.junit.Test
import java.util.*
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Tests03 {

    @Test
    fun testIsOrdered() {
        assertTrue(isOrdered(intArrayOf(1,3,4,5)))
        assertFalse(isOrdered(intArrayOf(1,3,4,3)))
    }

    @Test
    fun testSum() {
        assertEquals(13,sum(intArrayOf(1,3,4,5)))
        assertEquals(0,sum(intArrayOf()))
    }

    @Test
    fun testInverted() {
        assertEquals(intArrayOf(4,3,2,1).contentToString(), inverted(intArrayOf(1,2,3,4)).contentToString())
    }

    @Test
    fun testNaturals() {
        assertEquals(intArrayOf(3,4,5,6).contentToString(), naturals(3,6).contentToString())
    }

    @Test
    fun testNaturalsMatrix() {
        assertEquals(arrayOf(intArrayOf(1,2,3,4), intArrayOf(5,6,7,8), intArrayOf(9,10,11,12)).contentDeepToString(),
            naturalsMatrix(3,4).contentDeepToString())
    }

    @Test
    fun testAllSameSize() {
        assertTrue(allSameSize(arrayOf(intArrayOf(1,2,3,4), intArrayOf(5,6,7,8), intArrayOf(9,10,11,12))))
        assertFalse(allSameSize(arrayOf(intArrayOf(1,2,3,4), intArrayOf(5,6,7,8), intArrayOf(9,10,11))))
    }

    @Test
    fun testTranspose() {
        assertEquals(arrayOf(intArrayOf(1,5,9), intArrayOf(2,6,10), intArrayOf(3,7,11), intArrayOf(4,8,12)).contentDeepToString(),
            transpose(arrayOf(intArrayOf(1,2,3,4), intArrayOf(5,6,7,8), intArrayOf(9,10,11,12))).contentDeepToString())
    }
}