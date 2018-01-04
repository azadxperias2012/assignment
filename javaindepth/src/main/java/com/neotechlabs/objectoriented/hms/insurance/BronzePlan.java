package com.neotechlabs.objectoriented.hms.insurance;

public class BronzePlan extends HealthInsurancePlan {
    private static final double PREMIUM_PERCENTAGE = 0.05;

    public BronzePlan() {
        setCoverage(0.6);
    }

    @Override
    public double computeMonthlyPremium(double salary) {
        return (salary * PREMIUM_PERCENTAGE);
    }
}
