package com.neotechlabs.objectoriented.hms.insurance;

public class SilverPlan extends HealthInsurancePlan {
    private static final double PREMIUM_PERCENTAGE = 0.06;

    public SilverPlan() {
        setCoverage(0.7);
    }

    @Override
    public double computeMonthlyPremium(double salary) {
        return (salary * PREMIUM_PERCENTAGE);
    }
}
