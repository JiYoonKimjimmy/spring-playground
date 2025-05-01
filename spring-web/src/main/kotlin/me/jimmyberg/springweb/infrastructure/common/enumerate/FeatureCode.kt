package me.jimmyberg.springweb.infrastructure.common.enumerate

enum class FeatureCode(
    val code: String,
    val message: String
) {

    UNKNOWN("9999", "Unknown Service"),

    V1_STUDENT_MANAGEMENT_SERVICE("1001", "V1 Student Management Service"),

}