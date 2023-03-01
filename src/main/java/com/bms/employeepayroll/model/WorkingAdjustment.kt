package com.bms.employeepayroll.model

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator

@Entity
data class WorkingAdjustment @JvmOverloads constructor(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val adjustmentAmount: Double,
    val adjustmentPercentage: Double,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "working_hours_id")
    val workingHours: WorkingHours?,

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

        other as WorkingAdjustment

        if (id != other.id) return false
        if (adjustmentAmount != other.adjustmentAmount) return false
        if (adjustmentPercentage != other.adjustmentPercentage) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + adjustmentAmount.hashCode()
        result = 31 * result + adjustmentPercentage.hashCode()
        return result
    }

    override fun toString(): String {
        return "WorkingAdjustment(id=$id, adjustmentAmount=$adjustmentAmount, adjustmentPercentage=$adjustmentPercentage, workingHours=$workingHours, adjustment=$adjustment, salaryPayment=$salaryPayment)"
    }
}
