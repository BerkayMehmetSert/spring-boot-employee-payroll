package com.bms.employeepayroll.model

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator

@Entity
data class AdjustmentAmount @JvmOverloads constructor(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val amount: Double,
    val percentage: Double,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "adjustment_id")
    val adjustment: Adjustment?,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "salary_payment_id")
    val salaryPayment: SalaryPayment?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AdjustmentAmount

        if (id != other.id) return false
        if (amount != other.amount) return false
        if (percentage != other.percentage) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + amount.hashCode()
        result = 31 * result + percentage.hashCode()
        return result
    }

    override fun toString(): String {
        return "AdjustmentAmount(id=$id, amount=$amount, percentage=$percentage, adjustment=$adjustment, salaryPayment=$salaryPayment)"
    }
}
