package com.bms.employeepayroll.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import org.hibernate.annotations.GenericGenerator

@Entity
data class JobTitle @JvmOverloads constructor(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",

    val title: String,

    @OneToMany(mappedBy = "jobTitle")
    val employees: Set<Employee>? = HashSet(),

    @OneToMany(mappedBy = "jobTitle")
    val jobHistories: Set<JobHistory>? = HashSet()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as JobTitle

        if (id != other.id) return false
        if (title != other.title) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + title.hashCode()
        return result
    }

    override fun toString(): String {
        return "JobTitle(id=$id, title='$title', employees=$employees, jobHistories=$jobHistories)"
    }
}
