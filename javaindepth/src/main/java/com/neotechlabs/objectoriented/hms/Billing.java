package com.neotechlabs.objectoriented.hms;

import com.neotechlabs.objectoriented.hms.insurance.GoldPlan;
import com.neotechlabs.objectoriented.hms.insurance.HealthInsurancePlan;
import com.neotechlabs.objectoriented.hms.insurance.PlatinumPlan;
import com.neotechlabs.objectoriented.hms.insurance.SilverPlan;

public class Billing {

    public static double[] computePaymentAmount(Patient patient, double amount) {
        double[] payments = new double[2];

        HealthInsurancePlan patientInsurancePlan = patient.getInsurancePlan();

        // your logic
        if (patientInsurancePlan == null) {
            payments[0] = 0.0;
            payments[1] = amount - 20.0;
        } else {
            double insurancePart = amount * patientInsurancePlan.getCoverage();
            double patientPart = amount - insurancePart;
            if (patientInsurancePlan instanceof PlatinumPlan) {
                payments[0] = insurancePart;
                payments[1] = patientPart - 50.0;
            } else if (patientInsurancePlan instanceof GoldPlan) {
                payments[0] = insurancePart;
                payments[1] = patientPart - 40.0;
            } else if (patientInsurancePlan instanceof SilverPlan) {
                payments[0] = insurancePart;
                payments[1] = patientPart - 30.0;
            } else {
                payments[0] = insurancePart;
                payments[1] = patientPart - 25.0;
            }
        }

        return payments;
    }

}
