package com.neotechlabs.objectoriented.hms;

import com.neotechlabs.objectoriented.hms.insurance.HealthInsurancePlan;

public class Patient extends User {
    private long patientId;

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }
}
