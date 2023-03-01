package com.bms.employeepayroll.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import org.hibernate.annotations.GenericGenerator

@Entity
data class Department @JvmOverloads constructor(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",

    val name: String,

    @OneToMany(mappedBy = "department")
    val employees: Set<Employee>? = HashSet(),

    @OneToMany(mappedBy = "department")
    val departmentHistories: Set<DepartmentHistory> = HashSet()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Department

        if (id != other.id) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + name.hashCode()
        return result
    }

    override fun toString(): String {
        return "Department(id=$id, name='$name', employees=$employees, departmentHistories=$departmentHistories)"
    }
}
