package com.neotechlabs.objectoriented.hms.insurance;

public class PlatinumPlan extends HealthInsurancePlan {
    private static final double PREMIUM_PERCENTAGE = 0.08;

    public PlatinumPlan() {
        setCoverage(0.9);
    }

    @Override
    public double computeMonthlyPremium(double salary) {
        return (salary * PREMIUM_PERCENTAGE);
    }
}
