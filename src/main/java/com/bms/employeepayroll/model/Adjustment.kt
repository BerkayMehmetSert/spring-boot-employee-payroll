package com.bms.employeepayroll.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import org.hibernate.annotations.GenericGenerator

@Entity
data class Adjustment @JvmOverloads constructor(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val name: String,
    val percentage: Double,
    val isWorkingHoursAdjustment: Boolean,
    val isOtherAdjustment: Boolean,

    @OneToMany(mappedBy = "adjustment")
    val workingAdjustments: Set<WorkingAdjustment>? = HashSet(),

    @OneToMany(mappedBy = "adjustment")
    val adjustmentAmounts: Set<AdjustmentAmount>? = HashSet()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Adjustment

        if (id != other.id) return false
        if (name != other.name) return false
        if (percentage != other.percentage) return false
        if (isWorkingHoursAdjustment != other.isWorkingHoursAdjustment) return false
        if (isOtherAdjustment != other.isOtherAdjustment) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + name.hashCode()
        result = 31 * result + percentage.hashCode()
        result = 31 * result + isWorkingHoursAdjustment.hashCode()
        result = 31 * result + isOtherAdjustment.hashCode()
        return result
    }

    override fun toString(): String {
        return "Adjustment(id=$id, name='$name', percentage=$percentage, " +
                "isWorkingHoursAdjustment=$isWorkingHoursAdjustment, " +
                "isOtherAdjustment=$isOtherAdjustment, workingAdjustments=$workingAdjustments, " +
                "adjustmentAmounts=$adjustmentAmounts)"
    }
}
