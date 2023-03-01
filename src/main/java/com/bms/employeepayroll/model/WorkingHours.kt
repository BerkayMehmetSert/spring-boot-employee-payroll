package com.bms.employeepayroll.model

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime

@Entity
data class WorkingHours @JvmOverloads constructor(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "employee_id")
    val employee: Employee?,

    @OneToMany(mappedBy = "workingHours")
    val workingAdjustments: Set<WorkingAdjustment>? = HashSet()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as WorkingHours

        if (id != other.id) return false
        if (startTime != other.startTime) return false
        if (endTime != other.endTime) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + startTime.hashCode()
        result = 31 * result + endTime.hashCode()
        return result
    }

    override fun toString(): String {
        return "WorkingHours(id=$id, startTime=$startTime, endTime=$endTime, employee=$employee, workingAdjustments=$workingAdjustments)"
    }
}