package com.neotechlabs.objectoriented.hms.insurance;

public abstract class HealthInsurancePlan {
    private double coverage;
    private InsuranceBrand offeredBy;

    public InsuranceBrand getOfferedBy() {
        return offeredBy;
    }

    public void setOfferedBy(InsuranceBrand offeredBy) {
        this.offeredBy = offeredBy;
    }

    public double getCoverage() {
        return coverage;
    }

    public void setCoverage(double coverage) {
        this.coverage = coverage;
    }

    public abstract double computeMonthlyPremium(double salary, int age, boolean smoking);

    public double getAgePremium() {
        return 0.0;
    }

    public double getSmokePremium() {
        return 0.0;
    }
}
