package com.example.tasktrackerapp.data_class

data class Task(
    var kodeTask: Int,
    val title: String,
    val desc: String,
    val priority: String,
    val status: String,
    val tgl: String
) {
    // Custom getter for kodeTask
    var customKodeTask: Int
        get() {
            // You can add custom logic here if needed
            return kodeTask
        }
        set(value) {
            // You can add custom logic here if needed
            // For example, checking if the new value is positive
            if (value > 0) {
                kodeTask = value
            } else {
                throw IllegalArgumentException("kodeTask must be a positive integer")
            }
        }

    // Custom getter and setter for title
    var customTitle: String = title
        get() {
            // You can add custom logic here if needed
            return field
        }
        set(value) {
            // You can add custom logic here if needed
            field = value
        }

    // Custom getter and setter for desc
    var customDesc: String = desc
        get() {
            // You can add custom logic here if needed
            return field
        }
        set(value) {
            // You can add custom logic here if needed
            field = value
        }

    // Custom getter and setter for priority
    var customPriority: String = priority
        get() {
            // You can add custom logic here if needed
            return field
        }
        set(value) {
            // You can add custom logic here if needed
            field = value
        }

    // Custom getter and setter for status
    var customStatus: String = status
        get() {
            // You can add custom logic here if needed
            return field
        }
        set(value) {
            // You can add custom logic here if needed
            field = value
        }

    // Custom getter and setter for tgl
    var customTgl: String = tgl
        get() {
            // You can add custom logic here if needed
            return field
        }
        set(value) {
            // You can add custom logic here if needed
            field = value
        }
}


