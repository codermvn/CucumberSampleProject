Feature: Validate OBR Split and OBR Management features in Next Gen PLP
  
  As a system user i want to see list of OBRs based on status

  @Name-Test01
  Scenario Outline: Verify OBR Split functionality
    Given OBR created through service with given <Markets> and I am on Login Page
    And I login with <Username> and <Password>
    When I click on OBRList button
    Then I should see ProfitLine/Price-OBRList page
    When I select open in status dropdown
    And I enter TigerID
    And I click on OBRList search button
    Then Standard OBRs should be displayed
    And OBR Details Screen should open and we reject it
    Examples: 
      | Username | Password   | Markets                                         |
      | U125769  | Test@12345 | VIE-SIN,FRA-THR,ZRH-JNB,PAR-SHA,VIW-LON,DEL-MIL |
      | U125769  | Test@12345 | IN-US |
      
