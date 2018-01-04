package com.neotechlabs.objectoriented.hms.insurance;

public class GoldPlan extends HealthInsurancePlan {
    private static final double PREMIUM_PERCENTAGE = 0.07;

    public GoldPlan() {
        setCoverage(0.8);
    }

    @Override
    public double computeMonthlyPremium(double salary, int age, boolean smoking) {
        return PREMIUM_PERCENTAGE * salary +
                getOfferedBy().computeMonthlyPremium(this, age, smoking);
    }

    public double getAgePremium() {
        return 150.0;
    }

    public double getSmokePremium() {
        return 90.0;
    }
}
