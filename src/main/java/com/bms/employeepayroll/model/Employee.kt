package com.bms.employeepayroll.model

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate

@Entity
data class Employee @JvmOverloads constructor(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",

    val firstName: String,
    val lastName: String,
    val dateOfBirth: LocalDate,
    val address: String,
    val email: String,
    val employmentStart: LocalDate,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "job_title_id")
    val jobTitle: JobTitle?,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "department_id")
    val department: Department?,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "gender_id")
    val gender: Gender?,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "city_id")
    val city: City?,

    @OneToMany(mappedBy = "employee")
    val workingHours: Set<WorkingHours>? = HashSet(),

    @OneToMany(mappedBy = "employee")
    val salaryPayments: Set<SalaryPayment>? = HashSet(),

    @OneToMany(mappedBy = "employee")
    val employmentTerms: Set<EmploymentTerm>? = HashSet(),

    @OneToMany(mappedBy = "employee")
    val jobHistories: Set<JobHistory>? = HashSet(),

    @OneToMany(mappedBy = "employee")
    val departmentHistories: Set<DepartmentHistory>? = HashSet()

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Employee

        if (id != other.id) return false
        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false
        if (dateOfBirth != other.dateOfBirth) return false
        if (address != other.address) return false
        if (email != other.email) return false
        if (employmentStart != other.employmentStart) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        result = 31 * result + dateOfBirth.hashCode()
        result = 31 * result + address.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + employmentStart.hashCode()
        return result
    }

    override fun toString(): String {
        return "Employee(id=$id, firstName='$firstName', lastName='$lastName', " +
                "dateOfBirth=$dateOfBirth, address='$address', email='$email', " +
                "employmentStart=$employmentStart, jobTitle=$jobTitle, " +
                "department=$department, gender=$gender, city=$city, " +
                "workingHours=$workingHours, salaryPayments=$salaryPayments, " +
                "employmentTerms=$employmentTerms, jobHistories=$jobHistories, " +
                "departmentHistories=$departmentHistories)"
    }


}