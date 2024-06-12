Feature: Customer Checkout Flow

  @sauceLabDemo


  Scenario: Verify user is able to select the random items abd complete the checkout process successfully
    Given User is on Sauce Labs demo website
    When User selects 3 random items
    And User proceeds to checkout
    And User fills shipping information
    And User completes the checkout process
    Then Order should be confirmed successfully