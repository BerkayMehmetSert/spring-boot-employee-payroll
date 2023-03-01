package com.bms.employeepayroll.model

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate

@Entity
data class SalaryPayment @JvmOverloads constructor(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val grossSalary: Double,
    val netSalary: Double,
    val salaryPeriod: LocalDate,

    @OneToMany(mappedBy = "salaryPayment")
    val workingAdjustments: Set<WorkingAdjustment>? = HashSet(),

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "employee_id")
    val employee: Employee?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SalaryPayment

        if (id != other.id) return false
        if (grossSalary != other.grossSalary) return false
        if (netSalary != other.netSalary) return false
        if (salaryPeriod != other.salaryPeriod) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + grossSalary.hashCode()
        result = 31 * result + netSalary.hashCode()
        result = 31 * result + salaryPeriod.hashCode()
        return result
    }

    override fun toString(): String {
        return "SalaryPayment(id=$id, grossSalary=$grossSalary, netSalary=$netSalary, salaryPeriod=$salaryPeriod, workingAdjustments=$workingAdjustments)"
    }
}
