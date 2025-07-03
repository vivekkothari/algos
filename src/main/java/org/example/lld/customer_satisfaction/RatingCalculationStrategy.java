package org.example.lld.customer_satisfaction;

interface RatingCalculationStrategy {

  double calculate(RatingAggregate aggregate);

  class RunningAverage implements RatingCalculationStrategy {

    @Override
    public double calculate(RatingAggregate aggregate) {
      if (aggregate.getNumRatingsReceived() == 0) return 0;
      return (aggregate.getTotalRatingsReceived() * 1.0) / aggregate.getNumRatingsReceived();
    }
  }
}
