package com.bms.employeepayroll.model

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate

@Entity
data class JobHistory @JvmOverloads constructor(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",

    val startDate: LocalDate,
    val endDate: LocalDate?,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "employee_id")
    val employee: Employee?,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "job_title_id")
    val jobTitle: JobTitle?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as JobHistory

        if (id != other.id) return false
        if (startDate != other.startDate) return false
        if (endDate != other.endDate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + startDate.hashCode()
        result = 31 * result + endDate.hashCode()
        return result
    }

    override fun toString(): String {
        return "JobHistory(id=$id, startDate=$startDate, endDate=$endDate, employee=$employee, jobTitle=$jobTitle)"
    }
}
