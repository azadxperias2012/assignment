package com.neotechlabs.objectoriented.hms.insurance;

public class BlueCrossBlueShield implements InsuranceBrand {
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double computeMonthlyPremium(HealthInsurancePlan insurancePlan,
                                        int age, boolean smoking) {
        double premium = 0.0;
        if(age > 55) {
            premium += insurancePlan.getAgePremium();
        }
        if(smoking) {
            premium += insurancePlan.getSmokePremium();
        }
        return premium;
    }
}
