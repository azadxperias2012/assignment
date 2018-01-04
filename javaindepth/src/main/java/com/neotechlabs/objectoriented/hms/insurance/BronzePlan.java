package com.neotechlabs.objectoriented.hms.insurance;

public class BronzePlan extends HealthInsurancePlan {
    private static final double PREMIUM_PERCENTAGE = 0.05;

    public BronzePlan() {
        setCoverage(0.6);
    }

    @Override
    public double computeMonthlyPremium(double salary, int age, boolean smoking) {
        return PREMIUM_PERCENTAGE * salary +
                getOfferedBy().computeMonthlyPremium(this, age, smoking);
    }

    public double getAgePremium() {
        return 50.0;
    }

    public double getSmokePremium() {
        return 70.0;
    }
}
