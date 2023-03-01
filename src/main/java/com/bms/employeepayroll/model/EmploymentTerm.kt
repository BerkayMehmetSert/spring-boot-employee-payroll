package com.bms.employeepayroll.model

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate

@Entity
data class EmploymentTerm @JvmOverloads constructor(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",

    val agreedSalary: Double,
    val startDate: LocalDate,
    val endDate: LocalDate?,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "employee_id")
    val employee: Employee?

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EmploymentTerm

        if (id != other.id) return false
        if (agreedSalary != other.agreedSalary) return false
        if (startDate != other.startDate) return false
        if (endDate != other.endDate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + agreedSalary.hashCode()
        result = 31 * result + startDate.hashCode()
        result = 31 * result + endDate.hashCode()
        return result
    }

    override fun toString(): String {
        return "EmploymentTerm(id=$id, agreedSalary=$agreedSalary, startDate=$startDate, " +
                "endDate=$endDate, employee=$employee)"
    }

}
