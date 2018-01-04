package com.neotechlabs.objectoriented.hms.insurance;

public class SilverPlan extends HealthInsurancePlan {
    private static final double PREMIUM_PERCENTAGE = 0.06;

    public SilverPlan() {
        setCoverage(0.7);
    }

    @Override
    public double computeMonthlyPremium(double salary, int age, boolean smoking) {
        return PREMIUM_PERCENTAGE * salary +
                getOfferedBy().computeMonthlyPremium(this, age, smoking);
    }

    public double getAgePremium() {
        return 100.0;
    }

    public double getSmokePremium() {
        return 80.0;
    }
}
