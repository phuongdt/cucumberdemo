Feature: Shopping cart on an e-commerce website

  Scenario: Register - shopping - logout - login
    When I open website
    Then I register
    Then I select products
    Then I log out
    Then I log in
    Then I log out
