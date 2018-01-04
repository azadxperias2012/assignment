package com.neotechlabs.objectoriented.hms.insurance;

public class GoldPlan extends HealthInsurancePlan {
    private static final double PREMIUM_PERCENTAGE = 0.07;

    public GoldPlan() {
        setCoverage(0.8);
    }

    @Override
    public double computeMonthlyPremium(double salary) {
        return (salary * PREMIUM_PERCENTAGE);
    }
}
