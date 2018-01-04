package com.neotechlabs.objectoriented.hms.insurance;

public class PlatinumPlan extends HealthInsurancePlan {
    private static final double PREMIUM_PERCENTAGE = 0.08;

    public PlatinumPlan() {
        setCoverage(0.9);
    }

    @Override
    public double computeMonthlyPremium(double salary, int age, boolean smoking) {
        return PREMIUM_PERCENTAGE * salary +
                getOfferedBy().computeMonthlyPremium(this, age, smoking);
    }

    public double getAgePremium() {
        return 200.0;
    }

    public double getSmokePremium() {
        return 100.0;
    }
}
