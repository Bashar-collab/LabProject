package com.example.laboratory.models;

public enum TestResultStatus {

    PENDING,               // The test has been ordered but not yet processed.

    IN_PROGRESS,           // The test is currently being conducted or processed by the lab.

    AWAITING_VALIDATION,    // The test results are generated but awaiting review and validation by a healthcare provider.

    VALIDATED,             // The results have been reviewed and confirmed by a healthcare provider as accurate.

    READY,                 // The results have been validated and are now available for the patient or healthcare provider to view.

    CANCELED,              // The test was ordered but later canceled before processing (e.g., due to patient request or administrative issues).

    FAILED,                // The test could not be completed due to technical or sample-related issues (e.g., equipment failure or insufficient sample).

    INCONCLUSIVE,          // The test was processed, but the results are not definitive and further testing may be needed.

    REPORTED,              // The test results have been officially reported or communicated to the patient or healthcare provider.

    REQUIRES_FOLLOWUP,     // The test results indicate that further tests or a follow-up appointment with a healthcare provider is necessary.

    EXPIRED                // The test results have expired due to delays in processing or the data is no longer valid for clinical use.
}

